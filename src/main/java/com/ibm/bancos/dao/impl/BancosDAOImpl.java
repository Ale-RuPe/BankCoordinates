package com.ibm.bancos.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibm.bancos.client.BancosCoordenadasClient;
import com.ibm.bancos.dao.BancosDAO;
import com.ibm.bancos.entity.Banco;
import com.ibm.bancos.model.BancoModel;
import com.ibm.bancos.repository.BancoRepository;
import com.ibm.bancos.utils.Location;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BancosDAOImpl implements BancosDAO{
	@Autowired
	BancoRepository repository;
	
	@Autowired
	Location locate;

	@Autowired
	BancosCoordenadasClient feignClient;
	
	@HystrixCommand(fallbackMethod = "findByCoordinatesFallback")
	@Override
	public List<BancoModel> findByCoordinates(Double lat, Double lon) {
		log.info("FindingCoordinates dao with params: {}, {}",lat,lon);
		
		List<Banco> bancos = repository.findAll();
		List<BancoModel> s = bancos.stream()
				.filter(banco -> locate.isNear( locate.getDistance(lat, lon, banco.getGeometry().getCoordinates() ) ) )
				.map(banco -> locate.createResponse(banco))
				.collect( Collectors.toList() );
		
		log.info("Coordinates found: {}", s.size());
		return s;
	}
	
	public List<BancoModel> findByCoordinatesFallback(Double lat, Double lon) {
		log.info("Fallback method with params {},{}", lat, lon);
		
		List<Banco> bancos = feignClient.retrieveCoordinatesBanks(lon, lat);
		List<BancoModel> response = bancos.stream()
				.map(b ->locate.createResponse(b))
				.collect(Collectors.toList());
		
		log.info("Retrieving fallback response {}",response.size());
		return response;
	}

}

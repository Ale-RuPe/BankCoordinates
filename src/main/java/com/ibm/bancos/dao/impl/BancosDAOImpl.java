package com.ibm.bancos.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.bancos.dao.BancosDAO;
import com.ibm.bancos.entity.Banco;
import com.ibm.bancos.model.BancoModel;
import com.ibm.bancos.repository.BancoRepository;
import com.ibm.bancos.utils.Location;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BancosDAOImpl implements BancosDAO{
	@Autowired
	BancoRepository repository;
	
	@Autowired
	Location locate;
	@Override
	
	public List<BancoModel> findByCoordinates(Double lat, Double lon) {
		List<Banco> bancos = repository.findAll();
		
		List<BancoModel> s = bancos.stream()
				.filter(banco -> locate.isNear( locate.getDistance(lat, lon, banco.getGeometry().getCoordinates() ) ) )
				.map(banco -> locate.createResponse(banco))
				.collect( Collectors.toList() );
		
		log.info("Retrieves?: {}", s.size());
		return s;
	}
	
	

}

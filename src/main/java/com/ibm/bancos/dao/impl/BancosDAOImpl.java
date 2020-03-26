package com.ibm.bancos.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.bancos.dao.BancosDAO;
import com.ibm.bancos.entity.Banco;
import com.ibm.bancos.model.RetrieveSucursalesResponse;
import com.ibm.bancos.repository.BancoRepository;
import com.ibm.bancos.utils.Location;

@Component
public class BancosDAOImpl implements BancosDAO{
	@Autowired
	BancoRepository repository;
	
	@Autowired
	Location locate;
	@Override
	
	public RetrieveSucursalesResponse findByCoordinates(String lat, String lon) {
		List<Banco> bancos = repository.findAll();
		
		RetrieveSucursalesResponse response = new RetrieveSucursalesResponse();
		response.setSucuarsales( bancos.stream()
				.filter(banco -> locate.isNear( locate.getDistance(lat, lon, banco.getGeometry().getCoordinates() ) ) )
				.map(banco -> locate.createResponse(banco, lat, lon))
				.collect( Collectors.toList() ) );
		return response;
	}
	
	

}

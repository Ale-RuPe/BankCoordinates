package com.ibm.bancos.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.bancos.dao.BancosDAO;
import com.ibm.bancos.model.BancoModel;
import com.ibm.bancos.model.exceptions.NotFoundException;
import com.ibm.bancos.properties.Properties;
import com.ibm.bancos.service.BancosCoordenadasService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BancosCoordenadasServiceImpl implements BancosCoordenadasService{
	@Autowired
	BancosDAO dao;
	
	@Autowired
	Properties prop;
	
	@Override
	public List<BancoModel> findBancos(Double latitud, Double longitud) {
		log.info("service findBancos {},{}",latitud, longitud);
		List<BancoModel> response = dao.findByCoordinates(latitud, longitud);
		if(response.isEmpty()) {
			throw new NotFoundException(prop.getNotFoundMsg(), prop.getLocationServiceMsg(), prop.getControllerUri());
		}
		log.info("Retrieving response");
		return response;
	}

}

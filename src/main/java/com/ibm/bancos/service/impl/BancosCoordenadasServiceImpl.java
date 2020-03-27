package com.ibm.bancos.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.bancos.dao.BancosDAO;
import com.ibm.bancos.model.BancoModel;
import com.ibm.bancos.service.BancosCoordenadasService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BancosCoordenadasServiceImpl implements BancosCoordenadasService{
	@Autowired
	BancosDAO dao;
	
	@Override
	public List<BancoModel> findBancos(Double latitud, Double longitud) {
		log.info("service findBancos {},{}",latitud, longitud);
		return dao.findByCoordinates(latitud, longitud);
	}

}

package com.ibm.bancos.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.bancos.dao.BancosDAO;
import com.ibm.bancos.model.RetrieveSucursalesResponse;
import com.ibm.bancos.service.BancosCoordenadasService;

@Service
public class BancosCoordenadasServiceImpl implements BancosCoordenadasService{
	@Autowired
	BancosDAO dao;
	@Override
	public RetrieveSucursalesResponse findBancos(String latitud, String longitud) {
		
		return dao.findByCoordinates(latitud, longitud);
	}

}

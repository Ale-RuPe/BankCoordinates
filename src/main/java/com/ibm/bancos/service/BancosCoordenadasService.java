package com.ibm.bancos.service;


import com.ibm.bancos.model.RetrieveSucursalesResponse;

public interface BancosCoordenadasService {
	RetrieveSucursalesResponse findBancos(String latitud, String longitud);
}

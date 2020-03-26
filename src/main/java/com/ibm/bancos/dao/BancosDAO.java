package com.ibm.bancos.dao;

import com.ibm.bancos.model.RetrieveSucursalesResponse;


public interface BancosDAO {
	RetrieveSucursalesResponse findByCoordinates(String latitud, String longitud);
}

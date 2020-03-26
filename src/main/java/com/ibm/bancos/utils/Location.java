package com.ibm.bancos.utils;


import com.ibm.bancos.entity.Banco;
import com.ibm.bancos.model.RetrieveSucursal;

public interface Location {
	public boolean isNear(Double distancia);
	
	public RetrieveSucursal createResponse(Banco banco, String lat, String lon);
	
	public Double getDistance(String lat1, String lon1, Double[] coordinates);
	
	public Double toRadians(Double value);
}

package com.ibm.bancos.utils;


import com.ibm.bancos.entity.Banco;
import com.ibm.bancos.model.BancoModel;

public interface Location {
	public boolean isNear(Double distancia);
	
	public BancoModel createResponse(Banco banco);
	
	public Double getDistance(Double lat1, Double lon1, Double[] coordinates);
	
	public Double toRadians(Double value);
}

package com.ibm.bancos.utils.impl;


import org.springframework.stereotype.Component;

import com.ibm.bancos.entity.Banco;
import com.ibm.bancos.model.RetrieveSucursal;
import com.ibm.bancos.utils.Location;

@Component
public class LocationImpl implements Location{

	@Override
	public boolean isNear(Double distancia) {
		return distancia < 3 ? true : false;
	}

	@Override
	public RetrieveSucursal createResponse(Banco banco, String lat, String lon) {
		RetrieveSucursal response = new RetrieveSucursal();
		
		response.setDireccion(banco.getPropiedades().getDireccion());
		response.setEstado(banco.getPropiedades().getEstado());
		response.setHoraApertura(banco.getPropiedades().getHora_apertura());
		response.setHoraCierre(banco.getPropiedades().getHora_cierre());
		response.setNombre(banco.getPropiedades().getNombre());
		response.setTelefono(banco.getPropiedades().getTelefono());
		response.setTipoSucursal(banco.getPropiedades().getTipo_sucursal());
		
		return response;
	}

	@Override
	public Double getDistance(String lat1, String lon1, Double[] coordinates) {
		
		final int R = 6371; // Radious of the earth in KM
		
		Double lattitude1 = Double.parseDouble(lat1);
		Double longitude1 = Double.parseDouble(lon1);		
		Double lattitude2 = coordinates[1];
		Double longitude2 = coordinates[0];
		
		Double latDistance = toRadians(lattitude2-lattitude1);
		Double lonDistance = toRadians(longitude2-longitude1);
				
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + 
		Math.cos(toRadians(lattitude1)) * Math.cos(toRadians(lattitude2)) * 
		Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		Double distance = R * c;
		
		return distance;
	}

	@Override
	public Double toRadians(Double value) {
		return value * Math.PI / 180;
	}

}

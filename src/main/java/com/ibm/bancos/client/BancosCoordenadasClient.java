package com.ibm.bancos.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.bancos.entity.Banco;

@FeignClient(name = "${client.coordenadas.name}",  url = "${client.coordenadas.url}", fallback =BancosCoordenadasClientFallback.class)
public interface BancosCoordenadasClient {
	
	@GetMapping("${client.coordenadas.uri}")
	public List<Banco> retrieveCoordinatesBanks(
			@RequestParam Double xCoord,
		    @RequestParam Double yCoord);
}

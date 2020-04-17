package com.ibm.bancos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.bancos.model.BancoModel;
import com.ibm.bancos.model.exceptions.BadRequestException;
import com.ibm.bancos.service.BancosCoordenadasService;
import com.ibm.bancos.validator.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BancosCoordenadasController {
	@Autowired
	BancosCoordenadasService service;
	
	@Autowired
	private Validator validator;
	
	@GetMapping("${controller.uri}")
	public ResponseEntity<List<BancoModel>> getBancos(@RequestHeader HttpHeaders httpHeaders,
			@RequestParam Double gpsCoordX, @RequestParam Double gpsCoordY) throws BadRequestException{
		
		log.info("Headers {}", httpHeaders.toString());
		validator.validateHeaders(httpHeaders.toSingleValueMap());
		
		log.info("Request values {},{}",gpsCoordX,gpsCoordY);
		validator.validateParams(gpsCoordX, gpsCoordY);
		
		List<BancoModel> response = service.findBancos(gpsCoordX, gpsCoordY);
		log.info("Response {}",response.size());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}

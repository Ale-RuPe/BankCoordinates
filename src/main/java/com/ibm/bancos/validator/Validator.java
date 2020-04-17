package com.ibm.bancos.validator;

import java.util.Map;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ibm.bancos.model.exceptions.BadRequestException;
import com.ibm.bancos.properties.HeaderProperties;
import com.ibm.bancos.properties.Properties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Validator {
	
	private static final Predicate<String> emptyPredicate = StringUtils::isEmpty;
	private static final Predicate<Double> latitudPredicate = d-> !(d >= -90 && d <= 90);
	private static final Predicate<Double> longitudPredicate = d-> !(d >= -180 && d <= 180);
	
	@Autowired
	private HeaderProperties headerProperties;
	
	@Autowired
	private Properties properties;
	
	public void validateHeaders(Map<String, String> headers) throws BadRequestException {
		log.info("Validating Headers");
		
		validateHeader(emptyPredicate.test(headers.get(headerProperties.getAcceptHeader())),
		        headerProperties.getAcceptHeader());
		
		validateHeader(emptyPredicate.test(headers.get(headerProperties.getUuidHeader())),
		        headerProperties.getUuidHeader());
		
		validateHeader(emptyPredicate.test(headers.get(headerProperties.getCountryCodeHeader())),
		        headerProperties.getCountryCodeHeader());
		
		validateHeader(emptyPredicate.test(headers.get(headerProperties.getChannelidHeader())),
		        headerProperties.getChannelidHeader());
		
		validateHeader(emptyPredicate.test(headers.get(headerProperties.getBusinessCodeHeader())),
		        headerProperties.getBusinessCodeHeader());
	}
	
	private void validateHeader(boolean isHeader, String header) {
		if (isHeader) {
			log.info("Invalid header {}", header);
			throw new BadRequestException(headerProperties.getInvalidHeaderMsg(), 
					header, properties.getControllerUri());
	    }
	}
	
	public void validateParams(Double coordX, Double coordY) {
		validateCoordinate(latitudPredicate.test(coordX), coordX);
		validateCoordinate(longitudPredicate.test(coordY), coordY);
	}
	
	private void validateCoordinate(boolean isInvalid, Double coord) {
		if(isInvalid) {
			log.info("Invalid Request Param {}", coord);
			throw new BadRequestException(properties.getInvalidRequestParam(),
					properties.getControllerUri(), coord.toString());
		}
	}
}

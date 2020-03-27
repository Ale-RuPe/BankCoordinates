package com.ibm.bancos.service;


import java.util.List;

import com.ibm.bancos.model.BancoModel;

public interface BancosCoordenadasService {
	List<BancoModel> findBancos(Double latitud, Double longitud);
}

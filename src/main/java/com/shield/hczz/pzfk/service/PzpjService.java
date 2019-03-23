package com.shield.hczz.pzfk.service;

import java.util.Map;

public interface PzpjService {

	//根据pzid查配帧全部
	Map<String, String> selectPz(String pzId);

}

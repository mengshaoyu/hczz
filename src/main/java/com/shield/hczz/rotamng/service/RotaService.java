package com.shield.hczz.rotamng.service;

import java.util.Map;

import com.shield.hczz.rotamng.qvo.RotaVO;

public interface RotaService {

    Map<String, Object> getList(RotaVO vo, String page, String rows);

}

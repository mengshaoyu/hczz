package com.shield.hczz.base.persistence;

import java.util.List;
import java.util.Map;

import com.shield.hczz.rotamng.qvo.RotaVO;

public interface RotaMapper {

    List<RotaVO> getList(Map<String, Object> map, Integer page, Integer rows);

    int getCount(Map<String, Object> map);

}
package com.shield.hczz.pztb.service;

import java.util.Map;

import com.shield.frame.base.domain.User;
import com.shield.hczz.pztb.dto.HctbDTO;

public interface PztbService {

    Map<String, Object> save(HctbDTO hctb, String newaj, User usr);

    Map<String, Object> queryCaseById(String ajid);

    Map<String, Object> loadHctb(String pzid);

}

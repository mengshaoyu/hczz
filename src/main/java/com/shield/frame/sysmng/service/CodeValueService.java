package com.shield.frame.sysmng.service;

import java.util.HashMap;
import java.util.List;

import com.shield.frame.sysmng.dto.CodeValueDTO;

public interface CodeValueService {

    List<CodeValueDTO> getCodeValueByTypeId(String typeId);

    List<HashMap<String, Object>> getCodeValueMap(String typeId);

}

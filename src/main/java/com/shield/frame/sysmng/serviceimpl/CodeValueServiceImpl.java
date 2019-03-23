package com.shield.frame.sysmng.serviceimpl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.persistence.CodeValueMapper;
import com.shield.frame.base.persistence.impl.CodeValueImpl;
import com.shield.frame.sysmng.dto.CodeValueDTO;
import com.shield.frame.sysmng.service.CodeValueService;

@Service
public class CodeValueServiceImpl implements CodeValueService {

    @Autowired
    private CodeValueImpl codeValueImpl;

    @Autowired
    private CodeValueMapper codeValueMapper;

    public List<CodeValueDTO> getCodeValueByTypeId(String typeId) {
        List<CodeValueDTO> list = codeValueImpl.getListByTypeId(typeId);

        return list;
    }

    @Override
    public List<HashMap<String, Object>> getCodeValueMap(String typeId) {

        return this.codeValueMapper.getCodeValueMap(typeId);
    }

}

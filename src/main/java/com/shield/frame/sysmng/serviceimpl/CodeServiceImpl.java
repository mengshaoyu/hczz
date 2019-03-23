package com.shield.frame.sysmng.serviceimpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shield.frame.base.domain.Code;
import com.shield.frame.base.persistence.CodeMapper;
import com.shield.frame.base.persistence.impl.CodeImpl;
import com.shield.frame.base.persistence.impl.CodeValueImpl;
import com.shield.frame.sysmng.dto.CodeDTO;
import com.shield.frame.sysmng.dto.CodeValueDTO;
import com.shield.frame.sysmng.service.CodeService;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeImpl codeImpl;

    @Autowired
    private CodeMapper codeMapper;

    @Autowired
    private CodeValueImpl codeValueImpl;

    public List<CodeDTO> getCodeList() {
        List<CodeDTO> list = codeImpl.getCodeList();
        return list;
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateCodeandCodevalue(String typeId, CodeDTO codeDTO,
        List<CodeValueDTO> codeValuelist, List<CodeValueDTO> delList) {
        // codeValueImpl.delByTypeId(new BigDecimal(typeId));
        if (delList != null) {
            for (int j = 0; j < delList.size(); j++) {
                CodeValueDTO delDto = delList.get(j);
                codeValueImpl.delByDto(delDto);
            }
        }
        if (codeValuelist != null) {
            for (int i = 0; i < codeValuelist.size(); i++) {
                CodeValueDTO dto = codeValuelist.get(i);
                codeValueImpl.add(dto);
            }
        }
        return (int) codeImpl.updateByPK(codeDTO);
    }

    public int delByPK(BigDecimal typeId) {
        return codeImpl.delByPK(typeId);
    }

    public int delCodeandCodeValueByTypeId(String[] ids) {
        int sum = 0;
        for (int i = 0; i < ids.length; i++) {
            String typeId = ids[i];
            codeValueImpl.delByTypeId(new BigDecimal(typeId));
            sum = codeImpl.delByPK(new BigDecimal(typeId));
        }
        return sum;

    }

    public List<CodeDTO> getPageCodeList(HashMap<String, Object> map, int curPage, int pageSize) {
        return codeImpl.getPageCodeList(map, curPage, pageSize);
    }

    public int getCount() {
        return codeMapper.getCount();
    }

    @Override
    public Code getCode(String typeId) {
        Code code = codeImpl.getByPK(new BigDecimal(typeId));
        return code;
    }
}
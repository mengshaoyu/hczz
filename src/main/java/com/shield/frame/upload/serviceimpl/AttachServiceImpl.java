package com.shield.frame.upload.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shield.frame.base.persistence.impl.AttachImpl;
import com.shield.frame.upload.dto.AttachDTO;
import com.shield.frame.upload.service.AttachService;

@Service
public class AttachServiceImpl implements AttachService {

    @Autowired
    private AttachImpl attachImpl;

    public int save(List<AttachDTO> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                AttachDTO attachDTO = (AttachDTO) list.get(i);
                attachImpl.add(attachDTO);
            }
            return 1;
        }
        return 0;
    }

}

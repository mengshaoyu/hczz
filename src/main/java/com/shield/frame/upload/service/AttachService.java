package com.shield.frame.upload.service;

import java.util.List;

import com.shield.frame.upload.dto.AttachDTO;

public interface AttachService {

    int save(List<AttachDTO> list);

}

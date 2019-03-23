package com.shield.hczz.base.persistence;

import java.util.List;

import com.shield.hczz.base.domain.PzCommLang;

public interface PzCommLangMapper {
    
    int deleteLang(PzCommLang record);

    int insertLang(PzCommLang record);

    List<PzCommLang> selectLang(PzCommLang record);

    int updateLang(PzCommLang record);

    int countLang(PzCommLang record);
}
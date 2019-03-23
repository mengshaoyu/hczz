package com.shield.hczz.pzsp.dto;

import java.util.ArrayList;
import java.util.List;

import com.shield.hczz.base.domain.PzCommLang;

public class CommLangDTO {
    
    private List<PzCommLang> pcls = new ArrayList<>();

    public List<PzCommLang> getPcls() {
        return pcls;
    }

    public void setPcls(List<PzCommLang> pcls) {
        this.pcls = pcls;
    }

}

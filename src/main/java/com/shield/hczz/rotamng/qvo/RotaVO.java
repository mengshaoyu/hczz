package com.shield.hczz.rotamng.qvo;

import com.shield.hczz.base.domain.Rota;

public class RotaVO extends Rota {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String assignerName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAssignerName() {
        return assignerName;
    }

    public void setAssignerName(String assignerName) {
        this.assignerName = assignerName;
    }

}

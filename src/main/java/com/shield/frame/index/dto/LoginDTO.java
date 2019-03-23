package com.shield.frame.index.dto;

import org.hibernate.validator.constraints.NotBlank;

public class LoginDTO {

    /** 用户名 */
    @NotBlank
    private String account;

    /** 密码 */
    @NotBlank
    private String passwd;

    /** 是否记住密码 */
    private String rememberMe;

    /** 是否跳转到指定页面 */
    private String pageTo;

    public String getPageTo() {
        return pageTo;
    }

    public void setPageTo(String pageTo) {
        this.pageTo = pageTo;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }
}

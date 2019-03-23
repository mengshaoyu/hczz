package com.shield.frame.base.oscache.param;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shield.frame.base.oscache.adapter.CacheAdapter;

public class CommonParam {

    public static final String MULTI_VALUE_SEP = ",";

    private static final Log log = LogFactory.getLog(CommonParam.class);

    private String domain;

    private String function;

    private String pkName;

    private String pkValue;

    public CommonParam() {
    }

    public CommonParam(String domain, String function, String pkName, String pkValue) {
        this.domain = domain;
        this.function = function;
        this.pkName = pkName;
        this.pkValue = pkValue;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public String getPkValue() {
        return pkValue;
    }

    public void setPkValue(String pkValue) {
        this.pkValue = pkValue;
    }

    public boolean validationKey() {
        boolean result = false;
        if (domain == null || domain.length() == 0) {
            log.debug("CommonParam:validation:domain is null, operate cancelled.");
            return result;
        }
        if (function == null || function.length() == 0) {
            log.debug("CommonParam:validation:function is null, operate cancelled.");
            return result;
        }
        if (pkName == null || pkName.length() == 0) {
            log.debug("CommonParam:validation:pkName is null, operate cancelled.");
            return result;
        }
        if (pkValue == null || pkValue.length() == 0) {
            log.debug("CommonParam:validation:pkValue is null, operate cancelled.");
            return result;
        }
        if (pkName.split(MULTI_VALUE_SEP).length != pkValue.split(MULTI_VALUE_SEP).length) {
            log.debug("CommonParam:validation:pkValue isn't match to pkName, operate cancelled.");
            return result;
        }
        if (domain.indexOf(CacheAdapter.KV_SEP) != -1) {
            log.debug("CommonParam:validation:domain format is wrong, operate cancelled.");
            return result;
        }
        if (function.indexOf(CacheAdapter.KV_SEP) != -1) {
            log.debug("CommonParam:validation:function format is wrong, operate cancelled.");
            return result;
        }
        if (pkName.indexOf(CacheAdapter.KV_SEP) != -1) {
            log.debug("CommonParam:validation:pkName format is wrong, operate cancelled.");
            return result;
        }
        if (pkValue.indexOf(CacheAdapter.KV_SEP) != -1) {
            log.debug("CommonParam:validation:pkValue format is wrong, operate cancelled.");
            return result;
        }
        result = true;
        return result;
    }

    public boolean validationGroup() {
        boolean result = false;
        if (domain == null || domain.length() == 0) {
            log.debug("CommonParam:validation:domain is null, operate cancelled.");
            return result;
        }
        if (function == null || function.length() == 0) {
            log.debug("CommonParam:validation:function is null, operate cancelled.");
            return result;
        }
        if (domain.indexOf(CacheAdapter.KV_SEP) != -1) {
            log.debug("CommonParam:validation:domain format is wrong, operate cancelled.");
            return result;
        }
        if (function.indexOf(CacheAdapter.KV_SEP) != -1) {
            log.debug("CommonParam:validation:function format is wrong, operate cancelled.");
            return result;
        }
        result = true;
        return result;
    }

    public String generateGroupName() {
        return domain + CacheAdapter.KV_SEP + function;
    }

    public String generateKey() {
        return generateGroupName() + CacheAdapter.KV_SEP + pkName + CacheAdapter.KV_SEP + pkValue;
    }
}

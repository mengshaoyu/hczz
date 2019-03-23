package com.shield.frame.base.oscache.engine;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.shield.frame.base.oscache.adapter.CacheAdapter;
import com.shield.frame.base.oscache.adapter.StdCacheAdapter;
import com.shield.frame.base.oscache.param.CommonParam;

public class StdCacheEngine implements CacheEngine {

    private static Logger log = (Logger) LoggerFactory.getLogger(StdCacheEngine.class);

    private static StdCacheEngine __instance__ = new StdCacheEngine();

    @SuppressWarnings("unused")
    private static String engineName;

    public static StdCacheEngine getInstance() {
        return __instance__;
    }

    public static CacheAdapter getCacheAdapter() {
        return StdCacheAdapter.getInstance();
    }

    public static void setEngineName(String engineName) {
        try {
            if (engineName == null || engineName.length() == 0)
                return;
            InetAddress localhost = InetAddress.getLocalHost();
            StdCacheEngine.engineName = localhost.getHostName() + "-" + engineName + "("
                + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ")";
        }
        catch (UnknownHostException e) {
            log.debug("StdCacheEngine:UnknownHostException", e);
        }
    }

    public static String getEngineName() {
        return engineName;
    }

    public void flushAll(CommonParam commParam) {
        log.warn("===========    *** ALL DATA *** will be removed in this cache.   ===========");
        getCacheAdapter().flushAll();
    }

    public void flushEntry(CommonParam commParam) {
        if (commParam.validationKey()) {
            getCacheAdapter().flushEntry(commParam.generateKey());
            log.debug(commParam.generateKey());
        }
    }

    public void flushGroup(CommonParam commParam) {
        if (commParam.validationGroup()) {
            getCacheAdapter().flushGroup(commParam.generateGroupName());
            log.debug(commParam.generateGroupName());
        }
    }

    public void flushGroups(CommonParam[] commParam) {
        for (CommonParam commonParam : commParam) {
            if (commonParam.validationGroup()) {
                getCacheAdapter().flushGroup(commonParam.generateGroupName());
                log.debug(commonParam.generateGroupName());
            }
        }
    }

    public Object get(CommonParam commParam) {
        if (commParam.validationKey()) {
            Object object = getCacheAdapter().get(commParam.generateKey());
            //the following codes is commented by yuxing, because TypeUtil.typeToString has some performance leak.
            //log.debug(TypeUtil.typeToString("StdCacheEngine", object));
            return object;
        }
        return null;
    }

    public void put(CommonParam commParam, Object value) {
        if (commParam.validationKey()) {
            getCacheAdapter().put(commParam.generateKey(), value);
            //the following codes is commented by yuxing, because TypeUtil.typeToString has some performance leak.
            //log.debug(TypeUtil.typeToString("StdCacheEngine", value));
        }
    }

    public void remove(CommonParam commParam) {
        if (commParam.validationKey()) {
            getCacheAdapter().remove(commParam.generateKey());
            log.debug(commParam.generateKey());
        }
    }
}

package com.shield.frame.base.oscache.engine;

import com.shield.frame.base.oscache.param.CommonParam;

public interface CacheEngine {

    Object get(CommonParam commParam);

    void put(CommonParam commParam, Object value);

    void remove(CommonParam commParam);

    void flushAll(CommonParam commParam);

    void flushEntry(CommonParam commParam);

    void flushGroup(CommonParam commParam);

    void flushGroups(CommonParam commParam[]);

}

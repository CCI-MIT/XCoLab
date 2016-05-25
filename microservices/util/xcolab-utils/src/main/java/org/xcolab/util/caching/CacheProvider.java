package org.xcolab.util.caching;

import java.util.concurrent.Future;

public interface CacheProvider {
    Object get(String key);
    Future<Boolean> add(String key, int exp, Object o);
    boolean isActive();
}

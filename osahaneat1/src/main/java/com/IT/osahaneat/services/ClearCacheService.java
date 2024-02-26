package com.IT.osahaneat.services;

import com.IT.osahaneat.services.imp.ClearCacheServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class ClearCacheService implements ClearCacheServiceImp {
    @Autowired
    CacheManager cacheManager;

    @Override
    public Boolean clearCache(String nameCache) {
        if(cacheManager.getCache(nameCache)!=null){
            cacheManager.getCache(nameCache).clear();
            return true;
        }
        return false;
    }
}

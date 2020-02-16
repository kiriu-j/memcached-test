package com.special.none.service;

import com.special.none.vo.CachedContentsVo;

public interface MemcachedTestService {
    CachedContentsVo get(String cachedId);
    CachedContentsVo set(String cachedContents);
    CachedContentsVo set(String cachedId, String cachedContents);
}

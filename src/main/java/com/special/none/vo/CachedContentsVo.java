package com.special.none.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CachedContentsVo {
    String cachedId;
    String contents;
}

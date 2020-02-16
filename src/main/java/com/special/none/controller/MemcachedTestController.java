package com.special.none.controller;

import com.special.none.service.MemcachedTestService;
import com.special.none.vo.CachedContentsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/")
public class MemcachedTestController {

    @Autowired
    MemcachedTestService memcachedService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    String hello() {
        return "hello world";
    }

    @RequestMapping(path = "/get/{cachedId}", method = RequestMethod.GET)
    CachedContentsVo get(@PathVariable("cachedId") Optional<String> cachedId) {
        if (!cachedId.isPresent()) {
            return CachedContentsVo.builder().build();
        }
        return memcachedService.get(cachedId.get());
    }

    @RequestMapping(path = "/set/{contents}")
    CachedContentsVo set(@RequestParam(name = "cachedId", required = false) Optional<String> cachedId,
                         @PathVariable("contents") Optional<String> contents) {
        if (contents.isPresent()) {
            if (cachedId.isPresent()) {
                return memcachedService.set(cachedId.get(), contents.get());
            } else {
                return memcachedService.set(contents.get());
            }
        } else {
            return memcachedService.set(UUID.randomUUID().toString());
        }
    }
}

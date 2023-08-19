package com.rudy.ryanto.core.parameter.services;

import com.rudy.ryanto.core.parameter.entity.GeneralParameter;
import com.rudy.ryanto.core.parameter.repository.GeneralParamRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GeneralParamService {

    @Autowired
    private GeneralParamRepository generalParamRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    public Boolean loadAllParameter() {
        log.info("Load all parameter to cache!");
        Boolean isLoad = Boolean.FALSE;
        try {
            var result = generalParamRepository.findAll();
            if (!result.isEmpty())
                result.forEach(this::storeToCache);
            isLoad = Boolean.TRUE;
        } catch (Exception e) {
            log.error("failed to load param, caused : ", e.getMessage());
            isLoad = Boolean.FALSE;
        }

        return isLoad;
    }

    public Boolean loadSpecificParam(@NonNull String key,@NonNull String feature){
        Boolean isLoag = Boolean.FALSE;
        try {
            var result = generalParamRepository.findByKeyAndFeatureName(key,feature);
            if(!result.isEmpty()){
                result.forEach(this::storeToCache);
                isLoag = Boolean.TRUE;
            }
        }catch (Exception e ){
            log.error("failed to load specific param, caused : ",e.getCause());
            isLoag = Boolean.FALSE;
        }
        return isLoag;
    }

    @SuppressWarnings("all")
    private void storeToCache(GeneralParameter i) {
        redisTemplate.opsForHash().put(i.getKey(), i.getFeatureName(), i.getValues());
    }
}

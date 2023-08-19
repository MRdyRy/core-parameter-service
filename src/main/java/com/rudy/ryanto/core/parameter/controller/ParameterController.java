package com.rudy.ryanto.core.parameter.controller;

import com.rudy.ryanto.core.parameter.domain.LoadReq;
import com.rudy.ryanto.core.parameter.services.GeneralParamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Slf4j
public class ParameterController {

    private static final String ENDPOINT_LOAD_ALL = "/load/all";
    private static final String ENDPOINT_LOAD_SPECIFIC= "/load/specific";

    @Autowired
    private GeneralParamService generalParamService;

    @PostMapping(value = ENDPOINT_LOAD_ALL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean doLoadAllParameter(){
        log.info("v1/"+ENDPOINT_LOAD_ALL);
        return generalParamService.loadAllParameter();
    }

    @PostMapping(value = ENDPOINT_LOAD_SPECIFIC, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean doLoadSpecificParameter(@RequestBody LoadReq req){
        log.info("v1/"+ENDPOINT_LOAD_SPECIFIC);
        return generalParamService.loadSpecificParam(req.getKey(),req.getFeature());
    }

}

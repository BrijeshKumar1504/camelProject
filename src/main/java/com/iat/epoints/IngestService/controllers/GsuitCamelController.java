package com.iat.epoints.IngestService.controllers;


import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GsuitCamelController {

    @Autowired
    ProducerTemplate producerTemplate;





    @RequestMapping(value = "/")
    public void startCamel() {





        //producerTemplate.sendBody("direct:firstRoute", "Calling via Spring boot controller Route1");
        producerTemplate.sendBody("http://localhost:8080/gsuite/org_name/pullusers", "Successfully done");

    }

}

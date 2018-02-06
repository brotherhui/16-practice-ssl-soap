package com.brotherhui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

@RestController
class ClientController {

	private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);
	
    @Autowired
    private RestOperations restTemplate;
    

    @RequestMapping("/door")
    public String door() {
    	String url = "https://localhost:8443/ws";
    	String request ="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:demo=\"http://www.hifreud.com/ws/demo\">"
    			+"<soapenv:Header/>"
    			+"<soapenv:Body>"
    			+"<demo:UserRequest>"
    			+"<demo:userId>1</demo:userId>"
    	        +"</demo:UserRequest>"
    			+"</soapenv:Body>"
    			+"</soapenv:Envelope>";

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "text/xml;charset=utf-8");
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(request, requestHeaders);
        return restTemplate.exchange(url , HttpMethod.POST, requestEntity, String.class).getBody();

    }
}
package com.brotherhui;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import com.brotherhui.wsimport.UserRequest;
import com.brotherhui.wsimport.UserRequest_Type;  


@RestController
class ClientController2 {

	private static final Logger log = LoggerFactory.getLogger(ClientApplication.class);
	
    @Autowired
    private RestOperations restTemplate;
    
    @Value("${ssltest.trust-store-password}")
    private String trustStorePassword;
    
    @Value("${ssltest.trust-store}")
    private Resource trustStore;
    
	@Value("${ssltest.key-store-password}")
	private String keyStorePassword;
	
	@Value("${ssltest.key-password}")
	private String keyPassword;
	
	@Value("${ssltest.key-store}")
	private Resource keyStore; 
	
	@PostConstruct
	private void init(){
		try {
			System.setProperty("javax.net.ssl.trustStore", trustStore.getURL().getFile());
			System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
			System.setProperty("javax.net.ssl.keyStore", keyStore.getURL().getPath());
			System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
		
			javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
					new javax.net.ssl.HostnameVerifier() {

						public boolean verify(String hostname,
								javax.net.ssl.SSLSession sslSession) {
							if (hostname.equals("localhost")) {
								return true;
							}
							return false;
						}
					});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    

    @RequestMapping("/door2")
    public String door() throws MalformedURLException {
        
        String address = "https://localhost:8443/ws/users.wsdl?wsdl";  
        URL url = new URL(address);
        QName qname = new QName("http://www.hifreud.com/webservice", "UserRequestService");  
        Service service = Service.create(url,qname);  
        UserRequest tMyservice = service.getPort(UserRequest.class);  
        UserRequest_Type userRequest = new UserRequest_Type();
        userRequest.setUserId("1");
        return tMyservice.user(userRequest).getUsername();  

    }
}
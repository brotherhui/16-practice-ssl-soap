package com.ac.newit.endpoint;

import java.util.Date;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ac.newit.bean.UserRequest;
import com.ac.newit.bean.UserResponse;


/**
 * @author Freud
 */
@Endpoint
public class UserEndpoint {

	@PayloadRoot(namespace = "http://www.hifreud.com/ws/demo", localPart = "UserRequest")
	@ResponsePayload
	public UserResponse findUserById(@RequestPayload UserRequest request) throws Exception {

		System.out.println(request.getUserId());

		UserResponse response = new UserResponse();
		response.setUsername("Freud");
		response.setGender("Male");
		response.setLocation("Dalian");
		response.setBirthday(new Date());

		return response;
	}

}
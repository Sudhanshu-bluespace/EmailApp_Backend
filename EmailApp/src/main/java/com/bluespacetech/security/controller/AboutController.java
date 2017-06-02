package com.bluespacetech.security.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/about")
@PropertySource("classpath:application.properties")
public class AboutController {

	
	@Value("${app.version}")
	private String appVersion;
	
	@Value("${app.releaseDate}")
	private String releaseDate;
	
	
	@GetMapping(value="/getAppVersion")
	public void getApplicationVersion(HttpServletResponse response) throws IOException
	{
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getOutputStream().println("\"response\":{\"appVersion\":\""+appVersion+"\",\"releaseDate\":\""+releaseDate+"\"}");
	}
}

package com.tp.webservice.tp_web_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.tp.webservice.tp_web_service.ManageException",
		"com.tp.webservice.tp_web_service.publisher",
		"com.tp.webservice.tp_web_service.webservice",
		"com.tp.webservice.tp_web_service.model",
		"com.tp.webservice.tp_web_service.util",
})
public class TpWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpWebServiceApplication.class, args);
	}

}

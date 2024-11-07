package com.tp.webservice.tp_web_service.services;

import javax.jws.WebMethod;

public interface ServiceWebSoap {
	@WebMethod
	public int add(int a, int b);
}

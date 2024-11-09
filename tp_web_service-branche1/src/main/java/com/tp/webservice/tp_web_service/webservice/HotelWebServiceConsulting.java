package com.tp.webservice.tp_web_service.webservice;

import com.tp.webservice.tp_web_service.ManageException.ChambreNotFoundException;
import com.tp.webservice.tp_web_service.ManageException.LoginException;
import com.tp.webservice.tp_web_service.ManageException.VilleNotFoundException;
import com.tp.webservice.tp_web_service.model.Login;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.time.LocalDate;
import java.util.List;

@WebService
public interface HotelWebServiceConsulting {
    @WebMethod
    public List consulterLesOffres(Login login, String ville, LocalDate dateDebutReservation, LocalDate dateFinReservation, Integer nbPersonne) throws LoginException, VilleNotFoundException, ChambreNotFoundException;
}

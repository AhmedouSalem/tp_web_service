package com.tp.webservice.tp_web_service.webservice;

import com.tp.webservice.tp_web_service.ManageException.ChambreNotFoundException;
import com.tp.webservice.tp_web_service.ManageException.LoginException;
import com.tp.webservice.tp_web_service.ManageException.VilleNotFoundException;
import com.tp.webservice.tp_web_service.model.Agence;
import com.tp.webservice.tp_web_service.model.Hotel;
import com.tp.webservice.tp_web_service.model.Login;
import com.tp.webservice.tp_web_service.model.Offre;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.List;

@WebService
public interface HotelWebServiceConsulting {
    @WebMethod
    public List<Offre> consulterLesOffres(String identifiant, String password, String ville, XMLGregorianCalendar dateDebutReservation, XMLGregorianCalendar dateFinReservation, Integer nbPersonne)
            throws LoginException, VilleNotFoundException, ChambreNotFoundException;
    public int calculNbHotel();
}

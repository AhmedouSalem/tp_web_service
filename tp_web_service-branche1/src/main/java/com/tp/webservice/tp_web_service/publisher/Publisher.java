package com.tp.webservice.tp_web_service.publisher;

import com.tp.webservice.tp_web_service.webservice.HotelWebServiceConsultingImplement;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class Publisher implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.err.println("Attempting to publish HotelWebSzerviceConsulting...");
        Endpoint.publish("http://localhost:8080/hotelWebServiceConsulting", new HotelWebServiceConsultingImplement());
        //System.err.println("MathWebService published at http://localhost:8080/mathwebservice");
        //Endpoint.publish("http://localhost:8080/mathcalculatorwebservice", new MathWebServiceCalculatorImpl());
    }
}

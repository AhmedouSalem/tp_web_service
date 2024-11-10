package com.tp.webservice.tp_web_service.util;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.GregorianCalendar;

public class DateUtils {

    // Convertir XMLGregorianCalendar en LocalDate
    public static LocalDate convertToLocalDate(XMLGregorianCalendar xmlGregorianCalendar) {
        // Convertir XMLGregorianCalendar en GregorianCalendar
        GregorianCalendar calendar = xmlGregorianCalendar.toGregorianCalendar();

        // Convertir GregorianCalendar en LocalDate
        return calendar.toZonedDateTime().toLocalDate();
    }
}

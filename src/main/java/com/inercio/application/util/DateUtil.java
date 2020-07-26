package com.inercio.application.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class DateUtil {

    private DateUtil() {
    }

    public static Date toDate(LocalDateTime localDate) {
        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

}

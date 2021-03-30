package com.br.elton.maven.client.utils;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class Utils {

    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public LocalDate convertStringToLocalDate(String dateFormatString) throws ParseException {
        LocalDate date = LocalDate.parse(dateFormatString, format);
        return date;
    }

    public String convertLocalDateToString(LocalDate date) throws ParseException {
        String dateFormatString = format.format(date);
        return dateFormatString;
    }

}

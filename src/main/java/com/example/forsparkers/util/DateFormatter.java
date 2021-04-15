package com.example.forsparkers.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-d'T'HH:mm:ssXXX");

    public static String dateToString(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static Date stringToDate(String string) throws ParseException {
        return DATE_FORMAT.parse(string);
    }
}

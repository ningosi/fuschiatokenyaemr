package org.openmrs.module.fuschiatokenyaemr.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by codehub on 23/04/15.
 */
public class CoreUtils {

    public final static  SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    public static Date getBirthDate(String date1, String age) throws Exception{

        Calendar calendar = Calendar.getInstance();
        Date date = null;
        if (!(date1.isEmpty())) {
            date = formatter.parse(date1);
            int years = Math.round(Integer.parseInt(age));
            calendar.setTime(date);
            calendar.add(Calendar.YEAR,  -years);
            date = calendar.getTime();
        }
        return date;
    }

    public static Date getDateFromString(String date) throws ParseException {

        return formatter.parse(date);
    }

    public static String  formatDate(Date date) {
        return formatter.format(date);
    }

    public static Date addDate(Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -years);
        return calendar.getTime();

    }

    public static  Boolean integersOnly(String k) {

        String regex = "\\d+";
        boolean match = false;

        if((k.matches(regex))) {
            match = true;
        }

        return  match;
    }
}

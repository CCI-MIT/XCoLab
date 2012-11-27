package com.ext.portlet.plans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

    /**
     * @param args
     * @throws ParseException 
     */
    public static void main(String[] args) throws ParseException {
        System.out.println((new Date()).toString());
        SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date d = df.parse((new Date()).toString());
        d.toString();
        

    }

}

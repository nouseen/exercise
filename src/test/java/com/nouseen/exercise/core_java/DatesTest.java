package com.nouseen.exercise.core_java;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by nouseen on 2017/12/24.
 */
public class DatesTest {

    @Test
    public void testDate(){
        Locale.setDefault(Locale.CHINA);
        GregorianCalendar gregorianCalendar = new GregorianCalendar(2017, Calendar.DECEMBER, 24, 00, 48, 25);

        Date time = gregorianCalendar.getTime();
        System.out.println(time);
        gregorianCalendar.getFirstDayOfWeek();
    }

    @Test
    public void testNow(){
        Locale.setDefault(Locale.CHINA);

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        System.out.println(gregorianCalendar.getTime());

    }
}
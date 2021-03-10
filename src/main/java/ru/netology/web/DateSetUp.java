package ru.netology.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateSetUp {

    private SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");

    public String getCurrentDate() {


        Date date = new Date();

        String currentDate = (formatter.format(date));

        System.out.println(currentDate);

        return currentDate;
    }

    public String GetDatePlusThreeDays()  {

        String currentDate = getCurrentDate();
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(formatter.parse(currentDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DATE, 22);

        String dt = formatter.format(calendar.getTime());

        System.out.println(dt);

        return dt;
    }
}

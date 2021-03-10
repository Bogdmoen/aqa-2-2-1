package ru.netology.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateSetUp {

    private SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");

    public LocalDate getCurrentDate() {

        return LocalDate.now();
    }

    public String GetDatePlusThreeDays()  {

        LocalDate datePlusThreeDays = getCurrentDate().plusDays(3);

        return formatter.format(datePlusThreeDays);
    }
}

package ru.netology.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateSetUp {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LocalDate getCurrentDate() {

        return LocalDate.now();
    }

    public String GetDatePlusThreeDays()  {

        LocalDate datePlusThreeDays = getCurrentDate().plusDays(3);

        System.out.println(datePlusThreeDays);

        String date = formatter.format(datePlusThreeDays);
        System.out.println(date);

        return "test";
    }
}

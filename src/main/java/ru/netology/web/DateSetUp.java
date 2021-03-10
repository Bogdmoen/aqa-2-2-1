package ru.netology.web;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateSetUp {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getCurrentDate() {

        return formatter.format(LocalDate.now());
    }

    public String getDatePlusThreeDays()  {

        LocalDate datePlusThreeDays = LocalDate.now().plusDays(3);

        return formatter.format(datePlusThreeDays);
    }
    public String getDateMinusThreeDays()  {

        LocalDate dateMinusThreeDays = LocalDate.now().minusDays(3);

        return formatter.format(dateMinusThreeDays);
    }
}

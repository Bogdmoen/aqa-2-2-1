package ru.netology.web;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class DateSetUpTest {
    DateSetUp dateSetUp = new DateSetUp();

    @Test
    public void shouldGetDate() {
        dateSetUp.getCurrentDate();
        dateSetUp.GetDatePlusThreeDays();
    }
}
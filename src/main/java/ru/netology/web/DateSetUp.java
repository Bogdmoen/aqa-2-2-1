package ru.netology.web;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateSetUp {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern(("dd"));
    private DateTimeFormatter formatterMonth = DateTimeFormatter.ofPattern("MM");
    private DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy");
    private final String left = "//td[text()='";
    private final String right = "']";
    private final LocalDate currentDate = LocalDate.now();

    public String getCurrentDate() {
        return formatter.format(LocalDate.now());
    }

    public String getDatePlusDays(int day) {
        LocalDate datePlusThreeDays = currentDate.plusDays(day);
        return formatter.format(datePlusThreeDays);
    }

    public String getDateMinusDays(int day)  {
        LocalDate date = currentDate.minusDays(day);
        return formatter.format(date);
    }

    public String getCalendarDayPlusDay (int increment) {
        String day = formatterDay.format(currentDate.plusDays(increment));
        return left + day + right;
    }

    public String getCurrentCalendarDay () {
        String day = formatterDay.format(currentDate);
        return left + day + right;
    }

    public String getNextMonthDate() {
        return formatter.format(currentDate.plusDays(3).plusMonths(1));
    }

    public String getNextMonthCalendarDay() {
        String day = formatterDay.format(currentDate.plusDays(3).plusMonths(1));
        return left + day + right;
    }

    public String getNextYearDate() {
        return formatter.format(currentDate.plusDays(3).plusYears(1));
    }

    public String getNextYearCalendarDay() {
        String day = formatterDay.format(currentDate.plusDays(3).plusYears(1));
        return left + day + right;
    }

    public boolean isNextMonth() {
        int monthThree = Integer.parseInt(formatterMonth.format(currentDate.plusDays(3)));
        int monthWeek = Integer.parseInt(formatterMonth.format(currentDate.plusDays(7)));
        return monthThree != monthWeek;
    }
}

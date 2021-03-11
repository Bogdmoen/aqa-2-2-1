package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class CityDatePickerTest {
    DateSetUp dateSetUp = new DateSetUp();
    Condition disabledDay = cssClass("calendar__day_type_off"); // параметр для будних дней
    Condition disabledDayOff = cssClass("calendar__day_type_weekend-off"); // прараметр для выходных
    Condition disabledAnyDay = or("disabled", disabledDay, disabledDayOff);


    @Test
    public void shouldHaveCitySuggest() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Петро");
        $(".input__popup").shouldHave(Condition.cssClass("popup_visible"));
        $("[data-test-id='name'] .input__control").click();
        $(".input__popup").shouldNotHave(Condition.cssClass("popup_visible"));

    }

    @Test
    public void shouldClickCitySuggest() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").sendKeys("Петро");
        $(byXpath("//span[text()='Петрозаводск']")).should(Condition.exist);
        $(byXpath("//span[text()='Петропавловск-Камчатский']")).should(Condition.exist);
        $("[data-test-id=city] .input__control").sendKeys("заводск");
        $(byXpath("//span[text()='Петрозаводск']")).should(Condition.exist);
        $(byXpath("//span[text()='Петропавловск-Камчатский']")).shouldNot(Condition.exist);
        $(byXpath("//span[text()='Петрозаводск']")).click();
        $("[data-test-id='city'] .input__control").shouldHave(Condition.attribute("value", "Петрозаводск"));
    }

    @Test
    public void shouldPickDateOnPicker() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[data-test-id='date'] .input__control").click();
        if(dateSetUp.isNextMonth()) {
            $(".calendar__arrow_direction_right[data-step= '1']").click();
        }
        $(By.xpath(dateSetUp.getCalendarDayPlusDay(7))).click();
        $("[data-test-id='name'] .input__control").setValue("Иван");
        $("[data-test-id='phone'] .input__control").setValue("+79995554400");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id='notification'].notification_visible .notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + dateSetUp.getDatePlusDays(7)), Duration.ofSeconds(15));

    }
        @Test
        public void pickerDateShouldBeDisabled () {
            open("http://localhost:9999");
            $("[data-test-id='date'] .input__control").click();
            $(By.xpath(dateSetUp.getCurrentCalendarDay())).shouldBe(disabledAnyDay);
        }

        @Test
        public void shouldChangeMonthInPicker () {
            open("http://localhost:9999");
            $("[data-test-id='date'] .input__control").click();
            $(".calendar__arrow_direction_left[data-step= '-1']").shouldHave(Condition.attribute("data-disabled", "true"));
            $(".calendar__arrow_direction_right[data-step= '1']").click();
            $(".calendar__arrow_direction_left[data-step= '-1']").shouldHave(Condition.attribute("data-disabled", "false"));
            $(byXpath(dateSetUp.getNextMonthCalendarDay())).click();
            $("[data-test-id='date'] .input__control").shouldHave(Condition.attribute("value", dateSetUp.getNextMonthDate()));

        }

        @Test
        public void shouldChangeYearInPicker () {
            open("http://localhost:9999");
            $("[data-test-id='date'] .input__control").click();
            $(".calendar__arrow_direction_left[data-step= '-12']").shouldHave(Condition.attribute("data-disabled", "true"));
            $(".calendar__arrow_direction_right[data-step= '12']").click();
            $(".calendar__arrow_direction_left[data-step= '-12']").shouldHave(Condition.attribute("data-disabled", "false"));
            $(byXpath(dateSetUp.getNextYearCalendarDay())).click();
            $("[data-test-id='date'] .input__control").shouldHave(Condition.attribute("value", dateSetUp.getNextYearDate()));
        }

}


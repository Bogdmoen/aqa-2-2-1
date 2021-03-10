package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    DateSetUp dateSetUp = new DateSetUp();

    @Test
    public void shouldGetCardForm (){
        open("http://localhost:9999");
        $("h2.heading").shouldHave(Condition.text("Карта с доставкой"));
        $("[data-test-id=city] .input__control").shouldHave(Condition.attribute("placeholder", "Город"));
        $("[data-test-id=phone] .input__top").shouldHave(Condition.exactText("Мобильный телефон"));
    }

    @Test
    public void shouldGetSuccessForm() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue("15032021");
        $("[data-test-id='name'] .input__control").setValue("Иван");
        $("[data-test-id='phone'] .input__control").setValue("+79995554400");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id='notification'].notification_visible .notification__content").shouldHave(Condition.text("Встреча успешно забронирована на "), Duration.ofSeconds(15));
    }
}

package ru.netology.web;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;

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
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(dateSetUp.getDatePlusDays(3));
        $("[data-test-id='name'] .input__control").setValue("Иван");
        $("[data-test-id='phone'] .input__control").setValue("+79995554400");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button").click();
        $("[data-test-id='notification'].notification_visible .notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + dateSetUp.getDatePlusDays(3)), Duration.ofSeconds(15));
    }

    @Test
    public void shouldHaveEmptyCityAlert() {
        open("http://localhost:9999");
        $(".button").click();
        $("[data-test-id=city] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldHaveWrongCityAlert() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Toronto");
        $(".button").click();
        $("[data-test-id=city] .input__sub").shouldHave(Condition.exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void shouldHaveEmptyDateAlert() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $(".button").click();
        $("[data-test-id='date'] .input__sub").shouldHave(Condition.exactText("Неверно введена дата"));
    }

    @Test
    public void shouldHaveWrongDateAlert() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(dateSetUp.getCurrentDate());
        $(".button").click();
        $("[data-test-id='date'] .input__sub").shouldHave(Condition.exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    public void shouldHaveEmptyNameAlert() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(dateSetUp.getDatePlusDays(3));
        $(".button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldHaveWrongNameAlert() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(dateSetUp.getDatePlusDays(3));
        $("[data-test-id='name'] .input__control").setValue("Ivan");
        $(".button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldHaveEmptyPhoneAlert() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(dateSetUp.getDatePlusDays(3));
        $("[data-test-id='name'] .input__control").setValue("Иван");
        $(".button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldHaveWrongPhoneAlert() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(dateSetUp.getDatePlusDays(3));
        $("[data-test-id='name'] .input__control").setValue("Иван");
        $("[data-test-id='phone'] .input__control").setValue("54565");
        $(".button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldHaveUncheckedCheckboxText() {
        open("http://localhost:9999");
        $("[data-test-id=city] .input__control").setValue("Москва");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id='date'] .input__control").doubleClick().sendKeys(dateSetUp.getDatePlusDays(3));
        $("[data-test-id='name'] .input__control").setValue("Иван");
        $("[data-test-id='phone'] .input__control").setValue("+79995654400");
        $(".button").click();
        $("[data-test-id='agreement']").shouldHave(Condition.cssClass("input_invalid"));
    }



}

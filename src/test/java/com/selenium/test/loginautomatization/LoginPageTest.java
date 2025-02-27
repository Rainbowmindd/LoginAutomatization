package com.selenium.test.loginautomatization;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import java.time.Duration;
import java.time.Instant;


public class LoginPageTest {
    LoginPage loginPage = new LoginPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://www.saucedemo.com/");
        loginPage = new LoginPage(); //refresh object
    }

    @Test
    public void loginTest() {
        loginPage.username.sendKeys("standard_user");
        loginPage.password.sendKeys("secret_sauce");
        loginPage.loginButton.click();

        $("div.inventory_container").shouldBe(visible);

        assertEquals("Swag Labs",Selenide.title());
    }

    @Test
    public void invalidLoginTest() {
        loginPage.username.sendKeys("error_user");
        loginPage.password.sendKeys("password");
        loginPage.loginButton.click();

        $(".error-message-container").shouldBe(visible).shouldHave(attribute("innerText","Epic sadface: Username and password do not match any user in this service"));
    }

    @Test
    public void lockedOutUserLoginTest() {
        loginPage.username.sendKeys("locked_out_user");
        loginPage.password.sendKeys("secret_sauce");
        loginPage.loginButton.click();

        $(".error-message-container").shouldBe(visible).shouldHave(attribute("innerText","Epic sadface: Sorry, this user has been locked out."));
    }

    //glitch user test:
    @Test
    public void glitchUserLoginTest() {
        loginPage.username.sendKeys("performance_glitch_user");
        loginPage.password.sendKeys("secret_sauce");

        Instant startTime = Instant.now();

        loginPage.loginButton.click();

        $("div.inventory_container").shouldBe(visible,Duration.ofSeconds(4));

        Instant endTime= Instant.now();
        long loadTime = Duration.between(startTime,endTime).toMillis();

        if(loadTime>4000){
            System.out.println("Glitch detected");
        } else{
            System.out.println("Glitch not detected");
        }

    }

}

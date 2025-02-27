package com.selenium.test.loginautomatization;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

//page_url="https://www.saucedemo.com/"
public class LoginPage {
   public SelenideElement username = $x("//input[@id='user-name']");
   public SelenideElement password = $x("//input[@id='password']");
   public SelenideElement loginButton = $x("//input[@id='login-button']");
}

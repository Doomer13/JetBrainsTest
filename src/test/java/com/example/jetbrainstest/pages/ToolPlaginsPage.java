package com.example.jetbrainstest.pages;

import org.junit.jupiter.api.DisplayName;
import org.junit.platform.commons.logging.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.logging.Logger;
import static java.lang.Thread.sleep;

public class ToolPlaginsPage {

    WebDriver driver;

    @FindBy(xpath = "//span[contains(text(),'Плагины для .NET-инструментов')]")
    private WebElement toolsPluginsPage;
    @FindBy(xpath = "//button[@data-jetbrains-cookies-banner-action='CLOSE']")
    private WebElement closeCookiesButton;
    @FindBy(xpath = "//button[@class=\"_mainMenuItem__action_1gpjikx_19\"]")
    private WebElement pickMunu;


    public Boolean checkToolsPlaginsIsClickable(){
        System.out.println("Проверка активности кнопки");
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        closeCookiesButton.click();
        pickMunu.click();
        return toolsPluginsPage.isEnabled();
    };

    public ToolPlaginsPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}

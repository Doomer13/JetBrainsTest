package com.example.jetbrainstest.pages;

import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ToolPlaginsPage {

    WebDriver driver;

    @FindBy(xpath = "//span[contains(text(),'Tools Plugins')]")
    private WebElement toolsPluginsPage;

    public Boolean checkToolsPlaginsIsClickable(){
        System.out.println("Проверка активности кнопки");
        return toolsPluginsPage.isEnabled();
    };

    public ToolPlaginsPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}

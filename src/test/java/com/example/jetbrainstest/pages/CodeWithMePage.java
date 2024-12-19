package com.example.jetbrainstest.pages;

import com.example.jetbrainstest.AllureLogger;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

public class CodeWithMePage {

    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(CodeWithMePage.class));
    WebDriver driver;

    @FindBy(xpath = "//span[@data-test = 'menu-second-title-box-title']")
    private WebElement CodeWithMeButton;

    @FindBy(xpath = "//a[1][@target = '_self']")
    private WebElement LocalSolutions;

    @Step("Проверка кликабельности кнопки <Code With Me>")
    public Boolean CodeWithMeClickable(){
        LOG.infoWithScreenshot("Проверка доступности кнопки <Code With Me>");
        driver.get("https://www.jetbrains.com/ru-ru/code-with-me/");
        return CodeWithMeButton.isEnabled();
    };

    @Step("Проверка кликабельности кнопки <Локальные решени>")
    public Boolean LocalSolutionsClickable(){
        LOG.infoWithScreenshot("Проверка доступности кнопки <Локальные решения>");
        driver.get("https://www.jetbrains.com/ru-ru/code-with-me/");
        return CodeWithMeButton.isEnabled();
    };

    public String CodeWithMeCheckUrl(){
        driver.get("https://www.jetbrains.com/ru-ru/code-with-me/on-prem/");
        CodeWithMeButton.click();
        LOG.infoWithScreenshot("Проверка URL страницы, на которую попадаем после нажатия кнопки <Code With Me> ");
        return driver.getCurrentUrl();
    }

    public CodeWithMePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}

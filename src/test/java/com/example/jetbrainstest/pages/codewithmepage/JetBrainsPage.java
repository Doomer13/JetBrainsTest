package com.example.jetbrainstest.pages.codewithmepage;

import com.example.jetbrainstest.AllureLogger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class JetBrainsPage {

    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(SupportPage.class));
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

    @FindBy(xpath = "//*[@aria-label='Developer Tools: Open submenu' ]")
    private WebElement toDevelopment;
    @FindBy(xpath = "//div/a/span/span[contains(text(), 'Code With Me')]")
    private WebElement buttonCodeWithMe;

    @FindBy(xpath = "//*[@class=\"_wt-icon_0xycl _m_tjvq4i _icon_b46as5_570\"]")
    private WebElement languageButton;
    @FindBy(xpath = "//span[@class= 'wt-list-item__content'  and contains(text(), 'Русский')]")
    private WebElement russianLanguageButton;
    @FindBy(xpath = "//*[@class=\"wt-button wt-button_mode_contrast wt-button_size_s wt-button_theme_dark wt-button_align-icon_left\"]")
    private WebElement buttonСontinue;

    @FindBy(xpath = "//button[@aria-label='Open search']")
    private WebElement searchButton;
    @FindBy(xpath = "//input[@data-hj-whitelist='true']")
    private WebElement searchField;




    public String CodeWithMeCheckUrl() {

        toDevelopment.click();
        buttonCodeWithMe.click();
        LOG.infoWithScreenshot("Фото URL страницы, после нажатия кнопки <Code With Me> ");
        return driver.getCurrentUrl();
    }

    public String CheckUrlAfterСhangeLanguage() {
        languageButton.click();
        russianLanguageButton.click();
        buttonСontinue.click();
        LOG.infoWithScreenshot("Фото URL страницы, после смены языка ");
        return driver.getCurrentUrl();
    }

    public String jumpInSearchWebLeafUrl() {
        searchButton.click();
        searchField.click();
        searchField.sendKeys("Code With Me");
        searchField.sendKeys(Keys.ENTER);
        String value = searchField.getAttribute("value");
        LOG.infoWithScreenshot("Фото URL страницы, после перехода на новую страницу ");
        return driver.getCurrentUrl();
    }

    public String jumpInSearchWebLeafAndInputValue() {

        CodeWithMePage cookies =new CodeWithMePage(driver);
        searchButton.click();
        searchField.click();
        searchField.sendKeys("Code With Me");
        searchField.sendKeys(Keys.ENTER);
        cookies.closeCookiesBunner();
        String value = searchField.getAttribute("value");
        LOG.infoWithScreenshot("Фото URL страницы, после перехода на новую страницу ");
        return value;
    }
    public JetBrainsPage(WebDriver driver) {
        this.driver =driver;
        PageFactory.initElements(driver, this);
    }

}

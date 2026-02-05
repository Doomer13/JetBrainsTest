package com.example.jetbrainstest.pages.codewithmepage;

import com.example.jetbrainstest.AllureLogger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class JetBrainsPage {

    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(SupportPage.class));
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

    @FindBy(xpath = "//*[@aria-label='Developer Tools: Open submenu' ]")
    private WebElement toDevelopment;
    @FindBy(xpath = "//a[@class='_mainSubmenuSuggestion__link_1g610ye_1']")
    private WebElement buttonFindTools;
    @FindBy(xpath = "//a[@href='/code-with-me/']")
    private List<WebElement> buttonCodeWithMe;
    @FindBy(xpath = "//*[@data-test='language-picker']")
    private WebElement languageButton;
    @FindBy(xpath = "//span[@class= 'wt-list-item__content'  and contains(text(), 'Русский')]")
    private WebElement russianLanguageButton;
    @FindBy(xpath = "//*[@class='wt-col-inline']")
    private WebElement buttonСontinue;
    @FindBy(xpath = "//button[@aria-label='Open search']")
    private WebElement searchButton;
    @FindBy(xpath = "//input[@data-hj-whitelist='true']")
    private WebElement searchField;

    public String codeWithMeCheckUrl() {
        toDevelopment.click();
        buttonFindTools.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", buttonCodeWithMe.get(1));
        LOG.infoWithScreenshot("Фото URL страницы, после нажатия кнопки <Code With Me> ");
        return driver.getCurrentUrl();
    }

    public String checkUrlAfterСhangeLanguage() {
        languageButton.click();
        russianLanguageButton.click();
        buttonСontinue.click();
        LOG.infoWithScreenshot("Фото URL страницы, после смены языка ");
        return driver.getCurrentUrl();
    }

    public String jumpInSearchWebLeafUrl() {
        searchButton.click();
        searchField.click();
        searchField.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(3000);  // Ждем 3 секунды
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Восстанавливаем прерванный статус
            // Можно добавить log.error("Ожидание прервано", e);
        }

        return driver.getCurrentUrl();
    }

    public String jumpInSearchWebLeafAndInputValue() {
        CodeWithMePage cookies = new CodeWithMePage(driver);
        searchButton.click();
        searchField.click();

        Actions actions = new Actions(driver);
        actions.sendKeys(searchField, "Code With Me")
                .sendKeys(Keys.ENTER)
                .build()
                .perform();

        String value = searchField.getAttribute("value");
        LOG.infoWithScreenshot("Фото URL страницы, после перехода на новую страницу ");
        return value;
    }

    public JetBrainsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

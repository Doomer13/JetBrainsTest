package com.example.jetbrainstest.pages.codewithmepage;

import com.example.jetbrainstest.AllureLogger;
import com.example.jetbrainstest.MyWait;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

public class SupportPage {

    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(SupportPage.class));
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
    MyWait myWait = new MyWait(5);

    @FindBy(xpath = "//span[@class='_content_1ye8qjj_131']")
    private List<WebElement> country;
    @FindBy(xpath = "//*[@data-target= 'trigger']")
    private WebElement list;
    @FindBy(xpath = "//button[@class='_option_mp550j_68']")
    private WebElement buttonWriteToUs;
    @FindBy(xpath = "//div[@class='_label_6gddzg_396']")
    private List<WebElement> notNecessarilyList;
    @FindBy(xpath = "//button[@data-test = 'footer-country-button']")
    private WebElement getCountry;
    @FindBy(xpath = "//div[@class='_iconsWrapper_1m1udjp_143']")
    private List<WebElement> expandList;
    @FindBy(xpath = "//button[@data-test = 'footer-popup-confirm-country']")
    private WebElement sumbitCountry;
    @FindBy(xpath = "//button[@data-jetbrains-cookies-banner-action='CLOSE']")
    private WebElement closeCookiesButton;
    @FindBy(xpath = "//*[@class ='jetbrains-cookies-banner-4__body']")
    private WebElement cookiesBunner;

    public String pressCountry(String countryFromList) {
        getCountry.click();
        expandList.get(1).click();
        List<WebElement> listCountry = country;

        for (WebElement element : listCountry) {
            if (element.getText().trim().equals(countryFromList)) {
                // 🔧 ИСПРАВЛЕННАЯ ЛОГИКА с защитой от перекрытия
                robustClick(element);
                sumbitCountry.click();
                break;
            }
        }
        return getCountry.getText();
    }

    private void robustClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", element);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
        }
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }


    public String pressCountryRobust(String countryFromList) {
        getCountry.click();
        expandList.get(1).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> country.size() > 0);
        String xpath = String.format("//span[@class='_content_1ye8qjj_131' and contains(text(), '%s')]",
                countryFromList);
        WebElement targetCountry = driver.findElement(By.xpath(xpath));
        robustClick(targetCountry);

        myWait.clickable(targetCountry);
        sumbitCountry.click();
        return getCountry.getText();
    }

    public void listCountry() {
        list.click();
        List<WebElement> listСountry = country;
        for (int i = 0; i < listСountry.size(); i++) {
            listСountry.get(i).getText();
            System.out.println(listСountry.get(i).getText());
        }
        System.out.println(listСountry.get(1));
    }

    public boolean сheckRussiaInListCountry() {
        list.click();
        List<WebElement> listСountry = country;
        String nameRussia = "Россия";
        for (int i = 0; i < listСountry.size(); i++) {

            if (listСountry.get(i).getText().equals(nameRussia)) {
                return true;
            }
        }
        return false;
    }

    public void сheckCountryInListCountry(String nameCountry) {
        list.click();
        List<WebElement> listСountry = country;
        int num = 0;

        for (int i = 0; i < listСountry.size(); i++) {

            if (listСountry.get(i).getText().equals(nameCountry)) {
                System.out.println(nameCountry + " Есть");
                num = 1;
            }
        }
        if (num != 1) {
            System.out.println(nameCountry + " НЕТ");
        }
    }

    public String pressCountryStreem(String countryFromList) {
        getCountry.click();
        expandList.get(1).click();

        Optional<WebElement> countryElement = country.stream()
                .filter(e -> e.getText().equals(countryFromList))
                .findFirst();

        countryElement.ifPresent(element -> {
            element.click();
            sumbitCountry.click();
        });
        return getCountry.getText();
    }

    public int countCountry() {
        list.click();
        return country.size();
    }

    public void closeCookiesBunner() {
        LOG.infoWithScreenshot("Закрываем Cookies Баннер");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class ='jetbrains-cookies-banner-4__body']")));
        closeCookiesButton.click();
    }

    public SupportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}

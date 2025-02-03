package com.example.jetbrainstest.pages.codewithmepage;

import com.example.jetbrainstest.AllureLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SupportPage {

    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(SupportPage.class));
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));

    @FindBy(xpath = "//span[@class='_content_4qziqi_40']")
    private List<WebElement> country;
    @FindBy(xpath = "//*[1][@data-target= 'trigger']")
    private WebElement list;

    @FindBy(xpath = "//button[@class='_option_mp550j_68']")
    private WebElement buttonWriteToUs;
    @FindBy(xpath = "//div[@class='_label_6gddzg_396']")
    private List<WebElement> notNecessarilyList;

    @FindBy(xpath = "//button[@data-test = 'footer-country-button']")
    private WebElement getCountry;
    @FindBy(xpath = "/html/body/div[5]/div/div/div[2]/div/div/div/div/div[2]/svg/path")
    private WebElement expandList;
    @FindBy(xpath = "//button[@data-test = 'footer-popup-confirm-country']")
    private WebElement sumbitCountry;


    // списки стран

    public String pressCountry(String countruFromList){
        getCountry.click();
        closeCookiesButton.click();
        expandList.click();
        List<WebElement> listСountry = country;
        for (int i = 0; i < listСountry.size(); i++) {
           if (listСountry.get(i).getText().equals(countruFromList)){
               listСountry.get(i).click();
               sumbitCountry.click();
           }

        }

        return getCountry.getText();
    }

    public void listCountry() {
        list.click();
        List<WebElement> listСountry = country ;
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
        if (num !=1 ) {
            System.out.println(nameCountry + " НЕТ");
        }
    }

    public void streamCountryMethod(){
        list.click();
        List<WebElement> listСountry = driver.findElements(By.xpath("//span[@class='_content_4qziqi_40']"));
        Stream<WebElement> stream = listСountry.stream();
        stream.forEach(WebElement::getText);
        stream.forEach(System.out::println);

    }

    public int countCountry() {
        list.click();
        List<WebElement> listСountry = country ;
       return listСountry.size();
    }

//всякое
    public int notNecessarilyPage (){
        buttonWriteToUs.click();
        String one;
        String two;
        String phone = "Телефон (необязательно)";
        String company = "Компания (необязательно)";
        one = notNecessarilyList.get(0).getText();
        two = notNecessarilyList.get(1).getText();
        int a =0;
        if (one.equals(phone) && two.equals(company)){
        a = 1;
        }
    return a;
    }





    //конец

    @FindBy(xpath = "//button[@data-jetbrains-cookies-banner-action='CLOSE']")
    private WebElement closeCookiesButton;

    @FindBy(xpath = "//*[@class ='jetbrains-cookies-banner-4__body']")
    private WebElement cookiesBunner;

    public void closeCookiesBunner() {
        LOG.infoWithScreenshot("Закрываем Cookies Баннер");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class ='jetbrains-cookies-banner-4__body']")));
        closeCookiesButton.click();
    }


    public SupportPage(WebDriver driver) {
        this.driver =driver;
        PageFactory.initElements(driver, this);
    }

}

package com.example.jetbrainstest.pages;

import com.example.jetbrainstest.AllureLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

public class CodeWithMePage {

    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(CodeWithMePage.class));
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));



    @FindBy(xpath = "//button[@data-jetbrains-cookies-banner-action='CLOSE']")
    private WebElement closeCookiesButton;

    @FindBy(xpath = "//*[@class ='jetbrains-cookies-banner-4__body']")
    private WebElement cookiesBunner;


    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//span[contains(text(), 'Submit')]")
    private WebElement emailSubmit;

    @FindBy(xpath = "//div[@class='_errorMessage_10bo8mm_596']")
    private WebElement messageWrongEmail;

    @FindBy(xpath = "//p[1][contains(@class, 'wt-offset-top-24')]")
    private WebElement messageTrueEmail;

    //.............................................Видео селекторы
    @FindBy(css = "#player a[href^=\"https://www.youtube.com/watch\"]")
    private WebElement videoTitleYouTube;

    @FindBy(xpath = "//button[@data-test='button']")
    private WebElement watchVideoButton;

    @FindBy(xpath = "//img[@srcset]")
    private WebElement imgVideoButton;

    @FindBy(xpath = "//iframe[@class='wt-youtube-player__player']")
    private WebElement frame;

    @FindBy(xpath = "//*[1][@controlslist ='nodownload']")
    private WebElement videoPlayer;

    //..........................................................................


    @FindBy(xpath = "//span[@data-test = 'menu-second-title-box-title']")
    private WebElement CodeWithMeButton;

    @FindBy(xpath = "//a[1][@target = '_self']")
    private WebElement LocalSolutions;

    @FindBy(xpath = "//button[@class=\"_mainMenuItem__action_1gpjikx_19\"]")
    private WebElement pressMunu;

    @FindBy(xpath = "//span[contains(text(),'Плагины для .NET-инструментов')]")
    private WebElement toolsPluginsPage;

    //.................................Списки


    @FindBy(xpath = "//span[@class='_content_4qziqi_40']")
    private List <WebElement> country;

    @FindBy(xpath = "//*[1][@data-target= 'trigger']")
    private WebElement list;

    //..................проверка 4рех активности кнопок

    @FindBy(xpath = "//*[@xmlns ='http://www.w3.org/2000/svg']")
    private   List <WebElement>  manifestedButtons1;
    @FindBy(xpath = "//*[@xmlns ='http://www.w3.org/2000/svg'] [not(@width='963')]")
    private List<WebElement> manifestedButtons2;


    //          отображается ли элемент на странице после нажатие по кнопке.

    @FindBy(xpath = "//div[@class ='animation-img img-1 wt-display-sm-none']" )
    private WebElement buttonOne;
    @FindBy(xpath = "//h2[contains(text(), 'Collaborate with other developers in a remote-first world')]")
    private WebElement tranceToTextOne;

    @FindBy(xpath = "//div[@class ='animation-img img-2 wt-display-sm-none']")
    private WebElement buttonTwo;
    @FindBy(xpath = "//h2[contains(text(), 'How to get started')]")
    private WebElement tranceToTextTwo;

    @FindBy(xpath = "//div[@class ='animation-img img-3 wt-display-sm-none']")
    private WebElement buttonThree;
    @FindBy(xpath = "//h2[contains(text(), 'Get a full-featured experience for everyone')]")
    private WebElement tranceToTextThree;

    @FindBy(xpath = "//div[@class ='animation-img img-4 wt-display-sm-none']")
    private WebElement buttonFour;
    @FindBy(xpath = "//h2[contains(text(), 'Strike the perfect balance between powerful collaboration and strong security')]")
    private WebElement tranceToTextFour;



    ////////////////////////////////////////////
public boolean buttonsCheckActivity(int index){
    LOG.info("Проверяем активность четырех кнопок");
    //manifestedButtons1.remove(0);
    return manifestedButtons2.get(index).isEnabled();
}


//отображается ли элемент на странице.

public boolean isВisplayedTextAfterClick(){
    try {
        sleep(5000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    closeCookiesButton.click();
    buttonTwo.click();
return tranceToTextTwo.isDisplayed();
}



// E mail
    public void enterEmail(String email) {
        LOG.info("Ввод email");
        emailInput.sendKeys(email);
        emailSubmit.click();

    }

    public String getAnswerTrueEmail(String email) {

        LOG.info("E-mail Валиден");
        return messageTrueEmail.getText();
    }

    public String getMessageWrongEmail(String email) {
        enterEmail(email);
        LOG.info("E-mail НЕ валиден");
        return messageWrongEmail.getText();
    }


/////...............ВИДЕО

    public void videoImg() {
        LOG.info("Возпроизведение видео по картинке");
        imgVideoButton.click();
        driver.switchTo().frame(frame);
    }

    public void videoButton() {
        LOG.info(" Возпроизведение видео по кнопке <Whatch>");
        watchVideoButton.click();
        driver.switchTo().frame(frame);
    }

    public String getNameOfVideo() {
        LOG.infoWithScreenshot("Получение названия видео");
        return videoTitleYouTube.getText();
    }

    public double videoPlayerPlayinable() throws Exception{

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String source = (String) jsExecutor.executeScript("return arguments[0].currentSrc;", videoPlayer);

        LOG.infoWithScreenshot("Воспроизводим видео");
        jsExecutor.executeScript("arguments[0].play();", videoPlayer);
        Thread.sleep(2000); // Ждем 5 секунд

        LOG.infoWithScreenshot("Пауза для видео");
        jsExecutor.executeScript("arguments[0].pause();", videoPlayer);

        LOG.infoWithScreenshot("Получаем длительность видео на текущий момент");
        double currentTime = (Double) jsExecutor.executeScript("return arguments[0].currentTime;", videoPlayer);
        double floorValue = Math.floor(currentTime);

        return floorValue;
    }

    public String videoPlayerSource() throws Exception{


        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        LOG.infoWithScreenshot("Получаем источник видео");
        String sours = (String) jsExecutor.executeScript("return arguments[0].currentSrc;", videoPlayer);
        return sours;
    }


    //............................................


    public Boolean CodeWithMeClickable() {
        LOG.infoWithScreenshot("Проверка доступности кнопки <Code With Me>");
        driver.get("https://www.jetbrains.com/code-with-me/");
        return CodeWithMeButton.isEnabled();
    }

    public String CodeWithMeCheckUrl() {
        driver.get("https://www.jetbrains.com/code-with-me/on-prem/");
        CodeWithMeButton.click();
        LOG.infoWithScreenshot("Проверка URL страницы, после нажатия кнопки <Code With Me> ");
        return driver.getCurrentUrl();
    }

    public Boolean LocalSolutionsClickable() {
        LOG.infoWithScreenshot("Проверка доступности кнопки <Локальные решения>");
        driver.get("https://www.jetbrains.com/code-with-me/");
        return CodeWithMeButton.isEnabled();
    }


    public void closeCookiesBunner() {
        LOG.infoWithScreenshot("Закрываем Cookies Баннер");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class ='jetbrains-cookies-banner-4__body']")));
        closeCookiesButton.click();
    }


    public void PressMenu() {
        LOG.infoWithScreenshot("Нажимаем кнопку  <Разработчикам>");
        pressMunu.click();
    }

    public void toolsPlaginsButton() {
        LOG.infoWithScreenshot("Нажимаем кнопку  <Плагины для .NET-инструментов>");
        toolsPluginsPage.click();
    }

    //................................Списки

    public void listCountry() {
        list.click();
        List<WebElement> listСountry = country ;
        for (int i = 0; i < listСountry.size(); i++) {
            listСountry.get(i).getText();
            System.out.println(listСountry.get(i).getText());
        }
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



    // КОНЕЦ
    public CodeWithMePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}






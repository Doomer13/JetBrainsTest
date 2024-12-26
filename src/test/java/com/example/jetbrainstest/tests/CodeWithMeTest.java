package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.MyExtension;
import com.example.jetbrainstest.pages.CodeWithMePage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
public class CodeWithMeTest extends BaseTest {

    private CodeWithMePage codeWithMePage;

    @BeforeEach
    @Override
    @Step("переход в Тест страницы <Code With Me>")
    public void setUp() {
        super.setUp();
        getDriver().get("https://www.jetbrains.com/code-with-me/");

        //codeWithMePage.closeCookiesBunner();

        codeWithMePage = new CodeWithMePage(getDriver());

    }

    @Test
    @DisplayName("Ввод пустого email")
    public void enterEmptyEmail() {
        String warningAnswer = codeWithMePage.getMessageWrongEmail("");
        assertEquals(warningAnswer, "This field is required", "E-mail не введен вовсе");
        System.out.println(warningAnswer);
    }

    @Test
    @DisplayName("Ввод правельного email")
    public void enterTrueEmail() throws InterruptedException {
        //codeWithMePage.CloseCookiesBunner();
        String email = "phobus@gmail.com";
        codeWithMePage.enterEmail(email);
        String trueAnswer = codeWithMePage.getAnswerTrueEmail(email);
        assertEquals(trueAnswer, "Thanks for your request!", "E-mail введен правельно");
        System.out.println(trueAnswer);
    }


    @ParameterizedTest(name = "#{index} -  email набран НЕ Правельно {0}")
    @CsvSource({"phobusgmailcom", "phobusgmail.com", "phobus@gmailcom", "phobus.gmai@lcom"})
    @DisplayName("Ввод НЕ правельного email")
    public void enterWrongEmail(String email) {
        String warningAnswer = codeWithMePage.getMessageWrongEmail(email);
        assertEquals(warningAnswer, "E-mail address is not correct", "E-mail введен правельно");
        System.out.println(warningAnswer);
    }

//--------------------------ВИдео

    @Test
    @DisplayName("Возпроизведение нужного видео по картинке")
    public void playerCheckImg() {
        codeWithMePage.videoImg();
        String titleVideo = codeWithMePage.getNameOfVideo();
        assertEquals(titleVideo, "Introducing Code With Me - Collaborative Coding", "Не то видео");
    }

    @Test
    @DisplayName("Возпроизведение нужного видео по Кнопке")
    public void playerCheckButton() {
        codeWithMePage.videoButton();
        String titleVideo = codeWithMePage.getNameOfVideo();
        assertEquals(titleVideo, "Introducing Code With Me - Collaborative Coding", "Не то видео");
    }

    @Test
    @DisplayName("Проверяем bсточник видео")
    public void VideoPlayerSource() throws Exception {
        String source = codeWithMePage.videoPlayerSource();
        assertEquals("https://resources.jetbrains.com/storage/products/code-with-me/video/overview/1_intro.mp4#t=0.1", source);
    }

    @Test
    @DisplayName("Проверяем Возпроизводимость первого по счету видео Плээер")
    public void testHTML5VideoPlayer() throws Exception {
        double duration = codeWithMePage.videoPlayerPlayinable();
        assertEquals(2, duration);
        System.out.println(duration + " Видео возпроизводилось");
    }


    //.............................
    @Test
    @DisplayName("Проверка кликабельности <Code With Me>, а также URL")
    public void buttonCheck() {
        Assertions.assertTrue(codeWithMePage.CodeWithMeClickable());
        Assertions.assertEquals(codeWithMePage.CodeWithMeCheckUrl(), "https://www.jetbrains.com/code-with-me/", "URL не равен ожидаемому");
    }

    @Test
    @DisplayName("Проверка доступности кнопки <Локальные решения>")
    public void buttonCheck2() {
        Assertions.assertTrue(codeWithMePage.LocalSolutionsClickable());

    }

    @Test
    @DisplayName("Попытка закрыть всплывающий Банерр Cookies")
    public void buttonCheck3() {
        getDriver().get("https://www.jetbrains.com/code-with-me/");
        codeWithMePage.closeCookiesBunner();
        //codeWithMePage.PressMenu(); Демонстрация ошибки для скриншота
        //codeWithMePage.ToolsPlaginsButton();

    }

    //.................Списки

    @Test
    @DisplayName("Потверждение отсутвия в списке поддержки пнукта <Россия>")
    public void СonfirmationOfabsence() {
        getDriver().get("https://www.jetbrains.com/ru-ru/support/sales/#email-sales");

        //codeWithMePage.listCountry();
        //Assertions.assertFalse(codeWithMePage.сheckRussiaInListCountry(), "России там быть НЕ должно");
        codeWithMePage.сheckCountryInListCountry("Украина");
        //codeWithMePage.streamCountryMethod();
    }

// Активности Кнопок

    @ParameterizedTest()
    @CsvSource({"0", "1", "2", "3"})
    @DisplayName("Проверяем активности кнопок")
    public void ButtonCheckActivityTest(int a) {
        Assertions.assertTrue(codeWithMePage.buttonsCheckActivity(a));
    }

    //перемещение по странице и отображение кнопок
    @Test
    @DisplayName("перемещение по странице и отображение кнопок КНОПКА 2")
    public void isВisplayedTextAfterClickTest() {

        Assertions.assertTrue(codeWithMePage.isВisplayedTextAfterClick());

    }
}

//перемещение по странице и отображение кнопок
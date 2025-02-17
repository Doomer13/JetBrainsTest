package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.MyExtension;
import com.example.jetbrainstest.pages.codewithmepage.CodeWithMePage;
import com.example.jetbrainstest.pages.codewithmepage.JetBrainsPage;
import com.example.jetbrainstest.pages.codewithmepage.SupportPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
public class CodeWithMeTest extends BaseTest {

    private CodeWithMePage codeWithMePage;
    private SupportPage supportPage;
    private JetBrainsPage jetBrainsPage;

    @BeforeEach
    @Override
    @Step("переход в Тест страницы <Code With Me>")
    public void setUp() {
        super.setUp();
        getDriver().get("https://www.jetbrains.com/code-with-me/");
        //codeWithMePage.closeCookiesBunner();
        codeWithMePage = new CodeWithMePage(getDriver());
        supportPage =new SupportPage(getDriver());
        jetBrainsPage =new JetBrainsPage(getDriver());

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
        getDriver().get("https://www.jetbrains.com/code-with-me/");
        String warningAnswer = codeWithMePage.getMessageWrongEmail(email);
        assertEquals(warningAnswer, "E-mail address is not correct", "E-mail введен правельно");
        System.out.println(warningAnswer);
    }

    //Вbдео
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
    @DisplayName("Проверяем источник видео")
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


    //
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



    // Страница Support
    @Nested
    class NestedTestBlock1 extends BaseTest {
        @BeforeEach
        void setUp1() {
            super.setUp();
            codeWithMePage = new CodeWithMePage(getDriver());
            getDriver().get("https://www.jetbrains.com/ru-ru/support/sales/#email-sales");
        }

        @Test
        @DisplayName("Потверждение отсутвия в списке поддержки пункта <Россия>")
        public void СonfirmationOfabsence() {
            supportPage.listCountry();
            //Assertions.assertFalse(supportPage.сheckRussiaInListCountry(), "России там быть НЕ должно");
            //supportPage.сheckCountryInListCountry("Украина");
            //supportPage.streamCountryMethod();
        }

        @Test
        @DisplayName("Проверка общего количесва стран в списвке")
        public void countingListCountry() {
            System.out.println(supportPage.countCountry());
            Assertions.assertEquals(237, supportPage.countCountry(), "Количество стран не равно 237");
        }

        @RepeatedTest(3)
        @DisplayName("Проверка смены полей в форме заполнения в зависимости от выбора Мы вам напишем/ мы вам перезвоним")
        public void chosenTest() {
            getDriver().get("https://www.jetbrains.com/ru-ru/support/sales/#request-call");
            Assertions.assertEquals(1, supportPage.notNecessarilyPage(), "Поля не верны");
        }


        @Test
        @DisplayName("Выбор страны/региона и проверка отображения текста в соответствующем поле")
        public void displayCountryField() {
            getDriver().get("https://www.jetbrains.com/ru-ru/support/sales/#request-call");
            assertEquals("Нидерланды", supportPage.pressCountry("Нидерланды"), "Страна не отображенна");

        }
    }

    // Активности Кнопок
    @ParameterizedTest()
    @CsvSource({"0", "1", "2", "3"})
    @DisplayName("Проверяем активности больших кнопок кнопок")
    public void ButtonBigTopCheckActivityTest(int a) {
        Assertions.assertTrue(codeWithMePage.buttonsCheckActivity(a));
    }

    @ParameterizedTest()
    @CsvSource({"0", "1", "2", "3","4" ,"5" ,"6" ,"7" })
    @DisplayName("Проверяем активности маленьких кнопок -пиктограмм")
    public void ButtonSmallDownCheckActivityTest(int a) {
        Assertions.assertTrue(codeWithMePage.buttonsCheckActivity2(a));
    }

    //перемещение по странице и отображение кнопок
    @Test
    @DisplayName("перемещение по странице и отображение кнопок КНОПКА 2")
    public void isВisplayedTextAfterClickTest1() {
        Assertions.assertTrue(codeWithMePage.isВisplayedTextAfterClick1());
    }
    @Test
    @DisplayName("перемещение по странице и отображение кнопок КНОПКА 2")
    public void isВisplayedTextAfterClickTest2() {
        Assertions.assertTrue(codeWithMePage.isВisplayedTextAfterClick2());
    }
    @Test
    @DisplayName("перемещение по странице и отображение кнопок КНОПКА 2")
    public void isВisplayedTextAfterClickTest3() {
        Assertions.assertTrue(codeWithMePage.isВisplayedTextAfterClick3());
    }
    @Test
    @DisplayName("перемещение по странице и отображение кнопок КНОПКА 2")
    public void isВisplayedTextAfterClickTest4() {
        Assertions.assertTrue(codeWithMePage.isВisplayedTextAfterClick4());
    }





    @Test
    @DisplayName("Переход на страницу веб -страницу Code With Me")
    public void jumpInCodeWithMeWebSpace() {
        getDriver().get("https://www.jetbrains.com/");
        codeWithMePage.closeCookiesBunner();
        Assertions.assertEquals(jetBrainsPage.CodeWithMeCheckUrl(), "https://www.jetbrains.com/code-with-me/",
                "URL не равен ожидаемому");

    }

        @Test
        @DisplayName("Смена Языка")
        public void changelanguageTest() {
            getDriver().get("https://www.jetbrains.com/");
            codeWithMePage.closeCookiesBunner();
            Assertions.assertEquals("https://www.jetbrains.com/ru-ru/", jetBrainsPage.CheckUrlAfterСhangeLanguage(),
                    "URL не равен ожидаемому");

        }

        @Test
        @DisplayName("Переход на страницу поиска и проверка URL адресса")
        public void jumpInSearchWebLeafUrlTest() {
            getDriver().get("https://www.jetbrains.com/");
            Assertions.assertEquals("https://www.jetbrains.com/?s=full", jetBrainsPage.jumpInSearchWebLeafUrl(),
                    "URL не равен ожидаемому");

        }

        @Test
        @DisplayName("Переход на страницу поиска и проверка формы заполнения")
        public void jumpInSearchWebLeafTest() {
            getDriver().get("https://www.jetbrains.com/");
            Assertions.assertEquals("Code With Me", jetBrainsPage.jumpInSearchWebLeafAndInputValue(),
                    "НЕТ");

        }

    @Test
    @DisplayName("Попытка закрыть всплывающий Банерр Cookies")
    public void buttonCheck3() {
        codeWithMePage.closeCookiesBunner();
        //codeWithMePage.PressMenu(); Демонстрация ошибки для скриншота
        //codeWithMePage.ToolsPlaginsButton();

    }


        @Test
        void emailAnswerTest() {
            codeWithMePage.emalIput();
            assertEquals("The JetBrains team", codeWithMePage.waitAnswerEmail());
        }
}



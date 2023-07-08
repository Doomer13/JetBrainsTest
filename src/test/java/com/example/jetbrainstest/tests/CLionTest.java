package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.MyExtension;
import com.example.jetbrainstest.pages.CLionPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MyExtension.class)
public class CLionTest extends BaseTest {
    private CLionPage cLionPage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        getDriver().get("https://www.jetbrains.com/clion/");
        cLionPage = new CLionPage(getDriver());
    }

    @Test
    @DisplayName("Кнопка скачивания активна")
    public void downloadButtonCheck() {
        assertTrue(cLionPage.checkIfDownloadButtonIsClickable(), "Кнопка скачивания не активна");
    }

    @Test
    @DisplayName("Кнопка 'what's new' активна")
    public void whatIsNewButtonCheck() {
        assertTrue(cLionPage.checkIfwhatIsNewButtonClickable(), "Кнопка 'what's new' не активна");
    }

    @Test
    @DisplayName("Воспроизведено видео соответсвующее программе CLion")
    public void playerCheck() {
        String titleVideo = cLionPage.getNameOfVideo();
        assertEquals(titleVideo, "CLion Quick Tour", "Воспроизводится другое видео");
    }

    @ParameterizedTest(name = "#{index} - активность кнопки №{0}")
    @CsvSource({"0", "1", "2", "3"})
    @DisplayName("Активны кнопки из блока Follow Us")
    public void followButtonsCheck(int numberButton) {
        List<Boolean> listOfButtonStatuses = cLionPage.checkIfFollowButtonsAreClickable();
        Boolean StatusOfOneButton = listOfButtonStatuses.get(numberButton);
        assertTrue(StatusOfOneButton, String.format("Кнопка №%d неактивна", numberButton));
    }

    @Test
    @DisplayName("Возможность повторного ввода валидного email спустя 2 минуты")
    public void enterTwoTimesValidEmail() throws InterruptedException {
        String email = "qwer@google.com";
        cLionPage.enterEmail(email);

        // следующие две строки можно заменить местами (одну закомментировать, другую раскомментировать)
        TimeUnit.MINUTES.sleep(2);
        // getDriver().manage().deleteAllCookies(); // раскомментировать в случае полного нежелания ожидать 2 минуты

        getDriver().navigate().refresh();
        String answerAfterEnteringEmail = cLionPage.enterValidEmailAndGetAnswerAboutSuccess(email);
        assertEquals(answerAfterEnteringEmail, "Thanks for your request!", "Ooopps, something is wrong (I didn't get the right answer)");
    }

    @Test
    @DisplayName("Ввод пустого email")
    public void enterEmptyEmail() {
        String warningAnswer = cLionPage.enterInvalidEmailAndGetWarning("");
        assertEquals(warningAnswer, "This field is required.", "Текст сообщения некорректен");
    }

    // Тесты на ввод невалидного email с разными положениями символа @
    // Другие проверки email не стал перечислять, чтобы не захломлять. Указал только три в качестве примера.
    @ParameterizedTest(name = "#{index} - проверка на НЕ валидность email {0}")
    @CsvSource({"sldkfj", "slkf@", "qwer@lsakjf"})
    @DisplayName("Ввод невалидного email")
    public void enterEmailWithoutAt(String email) {
        String warningAnswer = cLionPage.enterInvalidEmailAndGetWarning(email);
        assertEquals(warningAnswer, "Please enter a valid email address.", "Текст сообщения некорректен");
    }

    @RepeatedTest(5) // в некоторых случаях страница не переключается (причина не выяснена)
    @DisplayName("Смена языка страницы на русский")
    public void changeLanguageOfPage() throws InterruptedException {
        String language = "Русский";
        cLionPage.changeLanguage(language);
        String urlPage = getDriver().getCurrentUrl();
        assertEquals(urlPage, "https://www.jetbrains.com/ru-ru/clion/", "Открыта страница не с русским языком");
    }

    @Test
    @DisplayName("В разделе 'Code analysis on the fly' есть три фото")
    public void checkScreenshotFromCodeAnalysisSection() {
        Integer countOfScreenshot = cLionPage.getCountOfScreenshotsInCodeAnalysisSection();
        assertEquals(countOfScreenshot, 3, "Колиечество скриншотов для в разделе 'Code analysis on the fly' не равно 3");
    }

}

package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.pages.CodeWithMePage;
import com.example.jetbrainstest.pages.PyCharmPage;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CodeWithMeTest extends BaseTest {

    private CodeWithMePage codeWithMePage;

    @BeforeEach
    @Override
    @Step ("переход в Тест страницы <Code With Me>")
    public void setUp(){
        super.setUp();
        getDriver().get("https://www.jetbrains.com/ru-ru/code-with-me/");
        codeWithMePage = new CodeWithMePage(getDriver());
    }
    @Test
    @DisplayName("Проверка доступности кнопки <Code With Me>")
    public void buttonCheck(){
        Assertions.assertTrue(codeWithMePage.CodeWithMeClickable());
    }

    @Test
    @DisplayName("Проверка доступности кнопки <Kокальные решения>")
    public void buttonCheck2(){
        Assertions.assertTrue(codeWithMePage.LocalSolutionsClickable());
    }

    @Test
    @DisplayName("Проверка URL после нажатия кнопки PyCharm")
    public void checkUrlAfterClickPyCharmButton(){
        codeWithMePage = new CodeWithMePage(getDriver());
        Assertions.assertEquals(codeWithMePage.CodeWithMeCheckUrl(),"https://www.jetbrains.com/ru-ru/code-with-me/","URL не равен ожидаемому");
    }

}

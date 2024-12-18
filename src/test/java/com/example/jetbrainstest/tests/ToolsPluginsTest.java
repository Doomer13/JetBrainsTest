package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.pages.ToolPlaginsPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import javax.xml.xpath.XPath;

public class ToolsPluginsTest extends BaseTest {

    private ToolsPluginsTest toolsPluginsTest;

    @BeforeEach
    @Override
    public void setUp(){
        super.setUp();
        getDriver().get("https://www.jetbrains.com/");

    }
    @Test
    @DisplayName("Проверка кнопки на активность")
    public void buttonCheck(){

        Assertions.assertTrue(toolPlaginsPage.checkToolsPlaginsIsClickable());
    }

}

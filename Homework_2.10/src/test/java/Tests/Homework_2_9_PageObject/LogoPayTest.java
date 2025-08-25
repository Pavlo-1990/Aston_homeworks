package Tests.Homework_2_9_PageObject;

import Pages.MainPage;
import Pages.PayPage;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class LogoPayTest {
    private WebDriver driver;

    @BeforeAll
    static void setup() {
        System.out.println("НАЧАЛО ТЕСТИРОВАНИЯ");
        System.setProperty("web driver.chrome.driver", "/src/test/resources/chromedriver-win64");
    }

    @BeforeEach
    void setupThis(){
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);

        // Всплывающее окно с куками
        System.out.println("НАЧАЛО ТЕСТА\n");
        mainPage.handleCookiesPopup();
    }

    @AfterEach
    void tearThis(){
        System.out.println("\nТЕСТ ОКОНЧЕН");
        driver.quit(); // закрытие браузера после завершения теста
    }

    @AfterAll
    static void tear(){
        System.out.println("ТЕСТИРОВАНИЕ ОКОНЧЕНО");
    }

    @RepeatedTest(1)
    @DisplayName("Логотипы платёжных систем")
    @Description("Тестирование логотипов платёжных систем")
    void logoPayTest() {
        PayPage payPage = new PayPage(driver);
        List<String> altList = getListAlt();
        List<String> svgList = getListSvg();

        payPage.identityLogo(); // идентификация логотипов
        payPage.listLogo(); // отображение списка логотипов и их URI-адресов
        payPage.assertLogo(altList, svgList); // проверка логотипов на соответствие требованиям
    }

    @Description("Метод по возвращению списка названий логотипов")
    private List<String> getListAlt() {
        List<String> altList = new ArrayList<>(Arrays.asList(
                "Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"));
        return altList;
    }

    @Description("Метод по возвращению списка медиафайлов с логотипами формата .svg")
    private List<String> getListSvg() {
        List<String> svgList = new ArrayList<>(Arrays.asList(
                "visa.svg", "visa-verified.svg", "mastercard.svg", "mastercard-secure.svg", "belkart.svg"));
        return svgList;
    }
}

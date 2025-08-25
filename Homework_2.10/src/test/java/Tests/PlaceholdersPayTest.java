package Tests;

import Pages.MainPage;
import Pages.PayPage;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class PlaceholdersPayTest {
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

        System.out.println("НАЧАЛО ТЕСТА\n");
        mainPage.handleCookiesPopup(); // обработка всплывающего окна с куками
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
    @DisplayName("Плейсхолдеры полей ввода")
    @Description("Тестирование на соответствие наименований плейсхолдеров")
    void placeholdersPayTest() {
        // Окно «Онлайн пополнение без комиссии»
        System.out.println("==========================================================================");
        System.out.println("Окно «Онлайн пополнение без комиссии»");
        System.out.println("==========================================================================");

        PayPage payPage = new PayPage(driver);

        for (int i = 1; i <= 4; i++){
            payPage.clickOptionButton(); // раскрытие списка услуг оплаты
            payPage.clickOption(i); // выбор опции 1 — «Услуга связи»
            payPage.assertPlaceholders(); // проверка плейсхолдеров на соответствие требованиям
        }
    }
}
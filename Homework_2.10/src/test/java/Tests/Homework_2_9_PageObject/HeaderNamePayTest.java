package Tests.Homework_2_9_PageObject;

import Pages.MainPage;
import Pages.PayPage;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HeaderNamePayTest {
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
    @DisplayName("Наименование заголовка «Онлайн пополнение без комиссии»")
    @Description("Тестирование на соответствие наименования блока «Онлайн пополнение без комиссии» на соответствие требованиям")
    void logoPayTest() {
        PayPage payPage = new PayPage(driver);
        payPage.assertHeader();
    }
}

package Tests.Homework_2_9_PageObject;

import Pages.MainPage;
import Pages.PayPage;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class HrefHelpPayTest {
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
    @DisplayName("Гиперссылка «Подробнее о сервисе»")
    @Description("Тестирование гиперссылки «Подробнее о сервисе»")
    void helpHrefPay() throws ElementClickInterceptedException, InterruptedException {
        PayPage payPage = new PayPage(driver);

        payPage.assertHelpHref(); // проверка гиперссылки на соответствие требованиям
        payPage.clickHelpHref(); // переход по гиперссылке
    }
}
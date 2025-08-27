package Tests.Homework_2_9_PageObject;

import Pages.MainPage;
import Pages.PayPage;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class HrefHelpPayTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    static void setup() {
        System.out.println("НАЧАЛО ТЕСТИРОВАНИЯ");
        System.setProperty("web driver.chrome.driver", "/src/test/resources/chromedriver-win64");
    }

    @BeforeEach
    void setupThis(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        MainPage mainPage = new MainPage(driver, wait);

        System.out.println("НАЧАЛО ТЕСТА\n");

        // Всплывающее окно с куками
        WebElement agreeCookiesButton = mainPage.handleCookiesPopup(); // ожидание всплывающего окна с куками

        if (agreeCookiesButton != null) {
            if (agreeCookiesButton.isDisplayed()) {
                assertEquals("Принять", agreeCookiesButton.getText(), "Название кнопки «Принять» не совпало");
                mainPage.clickAgreeCookiesButton();
            }
        }
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
        PayPage payPage = new PayPage(driver, wait);
        WebElement helpHref = payPage.getHrefHelp();

        assertHelpHref(helpHref); // проверка гиперссылки на соответствие требованиям
        payPage.clickHelpHref(); // переход по гиперссылке
    }

    @Description("Проверка гиперссылки сервиса «Подробнее о сервисе» на соответствие требованиям")
    public void assertHelpHref(WebElement hrefHelp) {
        assertNotNull(hrefHelp, "Элемент ссылки не найден");
        assertEquals("a", hrefHelp.getTagName(), "Ожидаемый тег «a» не совпал с фактическим");
        assertEquals("Подробнее о сервисе", hrefHelp.getText(),
                "Ожидаемое название сервиса «Подробнее о сервисе» не совпадало с фактическим");
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/",
                hrefHelp.getAttribute("href"), "Значение гиперссылки не совпало");
        assertTrue(hrefHelp.isDisplayed());

        System.out.println("Гиперссылка сервиса «Подробнее о сервисе» в порядке");
    }
}
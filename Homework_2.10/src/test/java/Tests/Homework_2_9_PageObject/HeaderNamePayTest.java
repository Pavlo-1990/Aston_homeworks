package Tests.Homework_2_9_PageObject;

import Pages.MainPage;
import Pages.PayPage;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class HeaderNamePayTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    static void setup() {
        System.out.println("НАЧАЛО ТЕСТИРОВАНИЯ");
        System.setProperty("web driver.chrome.driver", "/src/test/resources/chromedriver-win64");
    }

    @BeforeEach
    void setupThis() {
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
    void tearThis() {
        System.out.println("\nТЕСТ ОКОНЧЕН");
        driver.quit(); // закрытие браузера после завершения теста
    }

    @AfterAll
    static void tear() {
        System.out.println("ТЕСТИРОВАНИЕ ОКОНЧЕНО");
    }

    @RepeatedTest(10)
    @DisplayName("Наименование заголовка «Онлайн пополнение без комиссии»")
    @Description("Тестирование на соответствие наименования блока «Онлайн пополнение без комиссии» на соответствие требованиям")
    void logoPayTest() {
        PayPage payPage = new PayPage(driver, wait);
        WebElement headerName = payPage.getHeaderName();

        assertHeader(headerName);
    }

    @Description("Проверка названия блока «Онлайн пополнение без комиссии» на соответствие требованиям")
    public void assertHeader(WebElement headerName) {
        assertEquals("Онлайн пополнение\nбез комиссии", headerName.getText(),
                "Ожидаемое наименование заголовка не совпало с фактическим");
        assertEquals("h2", headerName.getTagName(), "Ожидаемый тег «h2» не совпал с фактическим");
        assertTrue(headerName.isDisplayed()); // проверка отображение заголовка
        System.out.println("Наименование заголовка блока «Онлайн пополнение без комиссии» в порядке");
    }
}

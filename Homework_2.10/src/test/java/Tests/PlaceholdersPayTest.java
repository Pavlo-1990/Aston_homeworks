package Tests;

import Pages.MainPage;
import Pages.PayPage;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class PlaceholdersPayTest {
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
    @DisplayName("Плейсхолдеры полей ввода")
    @Description("Тестирование на соответствие наименований плейсхолдеров")
    void placeholdersPayTest() {
        // Окно «Онлайн пополнение без комиссии»
        System.out.println("==========================================================================");
        System.out.println("Окно «Онлайн пополнение без комиссии»");
        System.out.println("==========================================================================");

        PayPage payPage = new PayPage(driver, wait);
        WebElement numberField;
        WebElement sum;
        WebElement email;
        String currentOption;
        int indexCurrentOption;


        for (int i = 1; i <= 4; i++){
            payPage.clickOptionButton(); // раскрытие списка услуг оплаты
            payPage.clickOption(i); // выбор опции 1 — «Услуга связи»

            numberField = payPage.getIdInputField(0);
            sum = payPage.getIdInputField(1);
            email = payPage.getIdInputField(2);
            currentOption = payPage.getCurrentOption();
            indexCurrentOption = payPage.getIndexCurrentOption();

            assertPlaceholders(numberField, sum, email, currentOption, indexCurrentOption); // проверка плейсхолдеров на соответствие требованиям
        }
    }

    @Description("Проверка плейсхолдеров полей ввода окна «Онлайн пополнение без комиссии»")
    public void assertPlaceholders(WebElement numberField, WebElement sum, WebElement email, String currentOption, int indexCurrentOption) {
        String[] numberFieldPlaceholders = {"Номер телефона", "Номер абонента", "Номер счета на 44", "Номер счета на 2073"};

        // Проверка наименований плейсхолдеров полей ввода
        assertEquals(numberFieldPlaceholders[indexCurrentOption], numberField.getAttribute("placeholder"));
        assertEquals("Сумма", sum.getAttribute("placeholder"));
        assertEquals("E-mail для отправки чека", email.getAttribute("placeholder"));

        System.out.printf("Плейсхолдеры опции %s в порядке\n\n", currentOption);
    }
}
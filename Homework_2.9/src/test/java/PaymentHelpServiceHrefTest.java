import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentHelpServiceHrefTest {
    private static WebDriver driver;

    @BeforeAll
    static void setup() {
        System.out.println("НАЧАЛО ТЕСТИРОВАНИЯ");
        System.setProperty("web driver.chrome.driver", "/src/test/resources/chromedriver-win64");
    }

    @BeforeEach
    void setupThis(){
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");

        System.out.println("НАЧАЛО ТЕСТА");

        // Всплывающее окно с куками
        try {
            WebElement agreeCookiesButton = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(By.id("cookie-agree"))); // ожидание появления всплывающего окна
            assertEquals("Принять", agreeCookiesButton.getText(), "Название кнопки «Принять» не совпало");

            if (agreeCookiesButton.isDisplayed()) {
                agreeCookiesButton.click(); // клик по кнопке "Принять", если окно появилось
                System.out.println("Нажата кнопка «Принять». Согласие на обработку cookies-файлов принято");
            }
        } catch (TimeoutException tEx){
            System.out.println("Всплывающее окно с куками не появилось. Тест продолжается.");
        }
    }

    @AfterEach
    void tearThis(){
        System.out.println("ТЕСТ ОКОНЧЕН");
        driver.quit();
    }

    @AfterAll
    static void tear(){
        // Закрытие тестового окна браузера после завершения тестирования
        System.out.println("ТЕСТИРОВАНИЕ ОКОНЧЕНО");
    }

    @RepeatedTest(10)
    @DisplayName("Тестирование гиперссылки «Подробнее о сервисе»")
    void linkTextTest()  throws ElementClickInterceptedException {
        // Элемент гиперссылки
        WebElement linkText = driver.findElement(By.linkText("Подробнее о сервисе"));

        // Наличие элемента гиперссылки
        assertNotNull(linkText, "Элемент ссылки не найден");

        // Тег гиперссылки
        assertEquals("a", linkText.getTagName(), "Тег не совпадал");

        // Название гиперссылки
        assertEquals("Подробнее о сервисе", linkText.getText(), "Текст не совпадал");

        // Значение гиперссылки
        String href = linkText.getAttribute("href");
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", href, "Значение гиперссылки не совпало");

        // Отображение гиперссылки
        assertTrue(linkText.isDisplayed());

        // Переход на страницу по клику гиперссылки «Подробнее о сервисе»
        linkText = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.linkText("Подробнее о сервисе")));
        
        linkText.click();
        System.out.println("Гиперссылка «Подробнее о сервисе» была успешно активирована кликом мыши. Осуществляется переход по ссылке на новую страницу.");

        String helpPaymentServiceLink = driver.getCurrentUrl(); // возвращение значения URI текущей страницы ("Подробнее о сервисе")
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/",
                helpPaymentServiceLink, "Значение гиперссылки не совпало");

        System.out.println("ТЕСТ УСПЕШНО ПРОЙДЕН!");
    }
}

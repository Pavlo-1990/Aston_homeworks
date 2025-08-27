import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentHeaderTest {
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
    @DisplayName("Тестирование названия блока «Онлайн пополнение без комиссии»")
    void headerTest() {
        WebElement elementHeader = driver.findElement(By.xpath("//div[@id='pay-section']//h2")); // поиск заголовка по XPath
        assertEquals("Онлайн пополнение\nбез комиссии", elementHeader.getText(),
                "Названия заголовка не совпали"); // *
                                            /* Содержимое заголовка <h2>: в тексте "Онлайн пополнение " после слова "пополнение" стоит один пробел.
                                            Однако метод "getText() нормализовал пробел, возвратив текст без конечного пробела. */

        assertEquals("h2", elementHeader.getTagName(), "Тег не совпал");
        assertTrue(elementHeader.isDisplayed()); // проверка отображение заголовка
    }
}

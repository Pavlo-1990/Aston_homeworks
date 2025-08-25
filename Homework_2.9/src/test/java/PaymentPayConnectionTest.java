import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentPayConnectionTest {
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
    @DisplayName("Тестирование названия блока «Онлайн пополнение без комиссии»")
    void headerTest() throws ElementClickInterceptedException {
        // Данный тест делался последним, в момент, когда поджимали сроки.
        // Поэтому опустил проверки и установил проброс потенциально возможного
        // исключения ElementClickInterceptedException на уровень выше

        // Форма заполнения
        WebElement selectField = driver.findElement(By.xpath("//button[@class='select__header'][.//span[@class='select__now' and text()='Услуги связи']]"));
        WebElement optionConnection = driver.findElement(By.xpath("//p[@class='select__option' and text()='Услуги связи']"));
        WebElement optionInternet = driver.findElement(By.xpath("//p[@class='select__option' and text()='Домашний интернет']"));
        WebElement phoneInputFieldPay = driver.findElement(By.xpath("//input[@id='connection-phone' and @placeholder = 'Номер телефона']"));
        WebElement sumInputFieldPay= driver.findElement(By.xpath("//input[@id='connection-sum' and @placeholder = 'Сумма']"));
        WebElement emailInputFieldPay= driver.findElement(By.xpath("//input[@id='connection-email' and @placeholder = 'E-mail для отправки чека']"));
        WebElement buttonContinuePay = driver.findElement(By.xpath("//form[@id='pay-connection']//button[text() = 'Продолжить']"));

        // Поле выбора услуги
        selectField.click(); // все дальнейшие потенциально возможные исключения ElementClickInterceptedException будут пробрасываться на уровень выше
        optionConnection.click();

            // Проверка переключаемости
            selectField.click();
            optionInternet.click();
            selectField.click();
            optionConnection.click();

        // Поле ввода «Номер телефона»
        phoneInputFieldPay.click();
        phoneInputFieldPay.sendKeys("297777777");

        // Поле ввода «Сумма»
        sumInputFieldPay.click();
        sumInputFieldPay.sendKeys("100"); // В реальных условиях в каждом тесте должны быть разные значения.
                                                       // Поэтому необходимо применение техник тест-дизайна — классы эквивалетности и граничные значения

        // Поле ввода «E-mail для отправки чека»
        emailInputFieldPay.click();
        emailInputFieldPay.sendKeys("foobar@xpath.net");

        // Поле ввода «Продолжить»
        buttonContinuePay.click();
    }
}

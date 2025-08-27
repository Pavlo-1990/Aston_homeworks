package Tests.Homework_2_9_PageObject;

import Pages.MainPage;
import Pages.PayPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class LogoPayTest {
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

    @Story("Тестирование логотипов платёжных систем")
    @RepeatedTest(5)
    @DisplayName("Логотипы платёжных систем")
    void logoPayTest() {
        PayPage payPage = new PayPage(driver, wait);
        List<String> altList = getListAlt();
        List<String> svgList = getListSvg();
        List<WebElement> listLogo = payPage.getListLogo();

        payPage.identityLogo(); // идентификация логотипов
        payPage.listLogo(); // отображение списка логотипов и их URI-адресов
        assertLogo(altList, svgList, listLogo); // проверка логотипов на соответствие требованиям
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

    @Step("Проверка гиперссылки сервиса «Подробнее о сервисе» на соответствие требованиям")
    public void assertLogo(List<String> altList, List<String> svgList, List<WebElement> listLogo) {
        // Проверка названий логотипов
        for (int i = 0; i < listLogo.size(); i++) {
            assertEquals(altList.get(i), listLogo.get(i).getAttribute("alt"),
                    "Ожидаемое название логотипа «" + altList.get(i) + "» не совпало с фактическим");
        }
        System.out.println("Названия логотипов в порядке");

        // Проверка значений URI-адресов
        for (int i = 0; i < listLogo.size(); i++) {
            assertEquals("https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/" + svgList.get(i),
                    listLogo.get(i).getAttribute("src"),
                    "Ожидаемое название медиафайла с логотипом «" + svgList.get(i) + "» не совпало с фактическим");
        }
        System.out.println("Значения URI-адресов логотипов в порядке");

        // Проверка значение тега «img»
        for (WebElement logo : listLogo) {
            assertEquals("img", logo.getTagName(), "Ожидаемый тег «img» не совпал с фактическим");
        }
        System.out.println("Тег «img» логотипов в порядке");

        // Проверка отображения логотипа на экране
        for (WebElement logo : listLogo) {
            wait.until(ExpectedConditions.visibilityOf(logo));
            assertTrue(logo.isDisplayed(), "Логотип не отобразился");
        }
        System.out.println("Все логотипы окна «Онлайн пополнение без комиссии» отобразились");
    }
}

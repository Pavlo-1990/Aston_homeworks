package Tests;

import Pages.BepaidPage;
import Pages.MainPage;
import Pages.PayPage;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

public class ConnectionPayTest {
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

    @RepeatedTest(10)
    @DisplayName("Опция «Услуга связи»")
    void PaymentTest() throws InterruptedException {
    // ОКНО «ОНЛАЙН ПОПОЛНЕНИЕ БЕЗ КОМИССИИ»
        System.out.println("==========================================================================");
        System.out.println("Окно «Онлайн пополнение без комиссии»");
        System.out.println("==========================================================================");

        PayPage payPage = new PayPage(driver);

        // Кнопка выбора услуги для оплаты
        payPage.clickOptionButton(); // раскрытие списка услуг оплаты
        payPage.clickOption(1); // выбор опции 1 — «Услуга связи»
                                             // выбор опции 3 — «Домашний интернет»
                                             // выбор опции 3 — «Рассрочка»
                                             // выбор опции 4 — «Задолженность»

        // Заполнение полей
        String phoneValue = payPage.typePhone("297777777");
        String sumValue = payPage.typeSum("100");
        payPage.typeEmail("foobar@xpath.net");
        payPage.clickContinueButton(); // активация кнопки «Продолжить»

// [IFRAME] ОКНО «РЕКВИЗИТЫ БАНКОВСКОЙ КАРТЫ»
        System.out.println("\n==========================================================================");
        System.out.println("Окно «Реквизиты банковской карты»");
        System.out.println("==========================================================================");

        BepaidPage bepaidPage = new BepaidPage(driver);

    // Проверки
        // Проверка текста
        bepaidPage.sumHeader(sumValue); // проверка заголовка: сумма оплаты
        bepaidPage.phoneHeader(phoneValue); // проверка заголовка: номер телефона
        bepaidPage.SumButtonDisabled(sumValue); // проверка кнопки «Оплатить» (состояние disabled)

        // Проверка логотипов
        List<String> namesLogoList = getNamesLogoList();
        List<String> svgList = getListSvg();

        bepaidPage.identityLogo(namesLogoList); // идентификация логотипов
        bepaidPage.listLogo(); // отображение списка логотипов и их URI-адресов
        bepaidPage.assertLogo(namesLogoList, svgList); // проверка логотипов на соответствие требованиям

        // Проверка плейсхолдеров
        Map<String, String> placeholdersBepaidMap = getPlaceholdersBepaidMap();
        bepaidPage.assertPlaceholdersBepaid(placeholdersBepaidMap);
    }

    @Description("Метод по возвращению списка плейсхолдеров окна «Реквизиты банковской карты»")
    private Map<String, String> getPlaceholdersBepaidMap() {
        Map<String, String> bepaidPlaceholdersBepaidMap = new LinkedHashMap<>(){{
            put("creditCard", "Номер карты");
            put("expirationDate", "Срок действия");
            put("cvc", "CVC");
            put("holder", "Имя и фамилия на карте");
        }};
        return bepaidPlaceholdersBepaidMap;
    }

    @Description("Метод по возвращению списка названий логотипов окна «Реквизиты банковской карты»")
    private List<String> getNamesLogoList() {
        List<String> nameslogoList = new ArrayList<>(Arrays.asList("Visa", "MasterCard", "Белкарт", "Maestro", "Мир"));
        return nameslogoList;
    }

    @Description("Метод по возвращению списка медиафайлов с логотипами формата .svg окна «Реквизиты банковской карты»")
    private List<String> getListSvg() {
        List<String> svgList = new ArrayList<>(Arrays.asList(
                "visa-system.svg", "mastercard-system.svg", "belkart-system.svg", "maestro-system.svg", "mir-system-ru.svg"));
        return svgList;
    }
}

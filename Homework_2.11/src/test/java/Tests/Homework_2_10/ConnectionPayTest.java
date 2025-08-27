package Tests.Homework_2_10;

import Pages.BepaidPage;
import Pages.MainPage;
import Pages.PayPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionPayTest {
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

    @Story("Опция «Услуга связи»")
    @RepeatedTest(5)
    @DisplayName("Опция «Услуга связи»")
    void paymentTest(){
        // ОКНО «ОНЛАЙН ПОПОЛНЕНИЕ БЕЗ КОМИССИИ»
        System.out.println("==========================================================================");
        System.out.println("Окно «Онлайн пополнение без комиссии»");
        System.out.println("==========================================================================");

        PayPage payPage = new PayPage(driver, wait);

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

        BepaidPage bepaidPage = new BepaidPage(driver, wait);

        // Проверки
        // Проверка текста
        WebElement sumHeader = bepaidPage.sumHeader(sumValue); // проверка заголовка: сумма оплаты
        WebElement phoneHeader = bepaidPage.phoneHeader(phoneValue); // проверка заголовка: номер телефона
        WebElement sumButtonDisabled = bepaidPage.SumButtonDisabled(sumValue); // проверка кнопки «Оплатить» (состояние disabled)
        List<String> namesLogoList = getNamesLogoList();

        assertSumHeader(sumHeader, sumValue);
        assertPhoneHeader(phoneHeader, phoneValue);
        assertSumButtonDisabled(sumButtonDisabled, sumValue);

        // Проверка логотипов
        List<String> svgList = getListSvg();
        List<WebElement> listLogo = bepaidPage.getListLogo();

        bepaidPage.identityLogo(namesLogoList); // идентификация логотипов
        bepaidPage.listLogo(); // отображение списка логотипов и их URI-адресов
        assertLogo(namesLogoList, svgList, listLogo); // проверка логотипов на соответствие требованиям

        // Проверка плейсхолдеров
        Map<String, String> placeholdersBepaidMap = getPlaceholdersBepaidMap();
        assertPlaceholdersBepaid(placeholdersBepaidMap);
    }

    @Description("Метод по возвращению списка плейсхолдеров окна «Реквизиты банковской карты»")
    private Map<String, String> getPlaceholdersBepaidMap() {
        Map<String, String> bepaidPlaceholdersBepaidMap = new LinkedHashMap<>() {{
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

    @Step("Проверка логотипов Окна «Реквизиты банковской карты» на соответствие требованиям")
    public void assertLogo(List<String> namesLogoList, List<String> svgList, List<WebElement> listLogo) {
        // Проверка значений URI-адресов
        for (int i = 0; i < listLogo.size(); i++) {
            assertEquals("https://checkout.bepaid.by/widget_v2/assets/images/payment-icons/card-types/" + svgList.get(i),
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
        /* Логотипы карт «Мир» и «Maestro» сменяют друг друга, образуя анимационную последовательность */
        int countAttempt = 1;
        for (WebElement logo : listLogo) {
            wait.until(ExpectedConditions.visibilityOf(logo));
            assertTrue(logo.isDisplayed(), "Логотип по ссылке " + logo.getAttribute("src") + " не отобразился");
        }
        System.out.println("Все логотипы окна «Онлайн пополнение без комиссии» отобразились\n");
    }

    @Step("Проверка заголовка: сумма оплаты")
    public void assertSumHeader(WebElement sumHeader, String valueSum) {
        assertEquals(valueSum + ".00 BYN", sumHeader.getText(),
                "Ожидаемый результат текста суммы в заголовке не совпал с фактическим");
        System.out.printf("Сумма оплаты в заголовке «%s» в порядке\n", sumHeader.getText());
    }

    @Step("Проверка заголовка: номер телефона")
    public void assertPhoneHeader(WebElement phoneHeader, String valuePhone) {
        String fullText = phoneHeader.getText();
        int index = fullText.indexOf("Номер:375");
        String phoneSubstring = fullText.substring(index);

        assertEquals("Номер:375" + valuePhone, phoneSubstring,
                "Ожидаемый результат текста номера телефона в заголовке не совпал с фактическим");
        System.out.printf("Номер телефона в заголовке «%s» в порядке\n", phoneSubstring);
    }

    @Step("Проверка кнопки «Оплатить» (состояние disabled)")
    public void assertSumButtonDisabled(WebElement sumButtonDisabled, String sumValue) {
        assertEquals("Оплатить " + sumValue + ".00 BYN", sumButtonDisabled.getText(),
                "Ожидаемый результат текста на кнопке не совпал с фактическим");
        System.out.printf("Текст «%s» кнопки «Оплатить» (состояние disabled) в порядке\n", sumButtonDisabled.getText());
    }

    @Step("Проверка плейсхолдеров полей ввода окна «Реквизиты банковской карты»")
    public void assertPlaceholdersBepaid(Map<String, String> placeholdersBepaidMap) {
        for (Map.Entry<String, String> entry : placeholdersBepaidMap.entrySet()) {
            String xpath = String.format("//input[@formcontrolname='%s']/following-sibling::label[text()='%s']", entry.getKey(), entry.getValue());
            WebElement inputField = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            assertEquals(entry.getValue(), inputField.getText(), String.format("Ожидаемый текст плейсхолдера «%s» не совпал с фактическим", entry.getValue()));
            System.out.printf("Реквизиты банковской карты: плейсхолдер «%s» в порядке\n", entry.getValue());
        }
    }
}

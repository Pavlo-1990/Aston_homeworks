package Pages;

import jdk.jfr.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PayPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    private final JavascriptExecutor js;

    // СОЗДАНИЕ И ИНИЦИАЛИЗАЦИЯ ЭЛЕМЕНТОВ СТРАНИЦЫ
    // Кнопка выбора услуги оплаты
    @FindBy(xpath = "//button[@class='select__header'][//span[@class='select__now']]")
    private WebElement optionButton; // кнопка выбора услуги для оплаты

    @FindBy(xpath = "//ul[@class='select__list']//p[@class='select__option' and text()='Услуги связи']")
    private WebElement optionConnection; // опция «Услуги связи»

    @FindBy(xpath = "//ul[@class='select__list']//p[@class='select__option' and text()='Домашний интернет']")
    private WebElement optionInternet; // опция «Домашний интернет»

    @FindBy(xpath = "//ul[@class='select__list']//p[@class='select__option' and text()='Рассрочка']")
    private WebElement optionInstalment; // опция «Рассрочка»

    @FindBy(xpath = "//ul[@class='select__list']//p[@class='select__option' and text()='Задолженность']")
    private WebElement optionArrears; // опция «Задолженность»

    String currentOption; // название текущей (выбранной услуги) оплаты
    private int indexCurrentOption = 0; // индекс текущей (выбранной) услуги оплаты

    // Кнопка продолжить
    @FindBy(xpath = "//form[@id='pay-connection']//button[text() = 'Продолжить']")
    private WebElement buttonContinue; // кнопка «Продолжить»

    @FindBy(xpath = "//iframe[@class='bepaid-iframe']")
    private WebElement bepaidIframe; // [iframe] окно «Реквизиты банковской карты»

    // Заголовок блока «Онлайн пополнение без комиссии»
    @FindBy(xpath = "//div[@id='pay-section']//h2")
    private WebElement headerName;

    // Поля ввода
    /* «Услуги связи» */
    @FindBy(id = "connection-phone")
    private WebElement inputFieldPhoneConnection; // поле ввода «Номер телефона»
    @FindBy(id = "connection-sum")
    private WebElement inputFieldSumConnection; // поле ввода «Сумма»
    @FindBy(id = "connection-email")
    private WebElement inputFieldEmailConnection; // поле ввода «E-mail для отправки чека»

    /* «Домашний интернет» */
    @FindBy(id = "internet-phone")
    private WebElement inputFieldPhoneInternet; // поле ввода «Номер абонента»
    @FindBy(id = "internet-sum")
    private WebElement inputFieldSumInternet; // поле ввода «Сумма»
    @FindBy(id = "internet-email")
    private WebElement inputFieldEmailInternet; // поле ввода «E-mail для отправки чека»

    /* «Рассрочка» */
    @FindBy(id = "score-instalment")
    private WebElement inputFieldPhoneInstalment; // поле ввода «Номер счета 44»
    @FindBy(id = "instalment-sum")
    private WebElement inputFieldSumInstalment; // поле ввода «Сумма»
    @FindBy(id = "instalment-email")
    private WebElement inputFieldEmailInstalment; // поле ввода «E-mail для отправки чека»

    /* «Задолженность» */
    @FindBy(id = "score-arrears")
    private WebElement inputFieldPhoneArrears; // поле ввода «Номер счета на 2073»
    @FindBy(id = "arrears-sum")
    private WebElement inputFieldSumArrears; // поле ввода «Сумма»
    @FindBy(id = "arrears-email")
    private WebElement inputFieldEmailArrears; // поле ввода «E-mail для отправки чека»

    private WebElement[][] inputFieldsIdArrays; // Двумерный массив с id полей ввода всех услуг оплаты

    // Список логотипов
    @FindBy(xpath = "//*[@id='pay-section']//img")
    private List<WebElement> listLogo;

    Map<String, String> logoMap = new LinkedHashMap<>(); // данные логотипов

    // Гиперссылка «Подробнее о сервисе»
    @FindBy(linkText = "Подробнее о сервисе")
    private WebElement hrefHelp;

    // КОНСТРУКТОР
    public PayPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this); // инициализация элементов аннотации @FindBy
    }

    // МЕТОДЫ ДЛЯ КНОПКИ ВЫБОРА УСЛУГИ ДЛЯ ОПЛАТЫ
    @Description("Активация кнопки: раскрытие списка услуг оплаты")
    public void clickOptionButton() {
        try {
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", optionButton); // прокрутка страницы до кнопки выбора услуг
            wait.until(ExpectedConditions.elementToBeClickable(optionButton));
            actions.moveToElement(optionButton).pause(250).click().pause(250).build().perform();
        } catch (TimeoutException tEx) {
            System.out.println("Не сработал первый клик");
            actions.moveToElement(optionButton).pause(250).click().pause(250).build().perform();
        }
    }

    @Description("Выбор опции")
    public void clickOption(int optionNumber) {
        WebElement option = optionConnection;
        switch (optionNumber) {
            case 1: // Опция 1 — «Услуги связи»
                option = optionConnection;
                currentOption = "«Услуги связи»";
                System.out.println("Переключено на опцию «Услуги связи»");
                break;
            case 2: // Опция 2 — «Домашний интернет»
                option = optionInternet;
                currentOption = "«Домашний интернет»";
                System.out.println("Переключено на опцию «Домашний интернет»");
                break;
            case 3: // Опция 3 —«Рассрочка»
                option = optionInstalment;
                currentOption = "«Рассрочка»";
                System.out.println("Переключено на опцию «Рассрочка»");
                break;
            case 4: // Опция 4 —«Задолженность»
                option = optionArrears;
                currentOption = "«Задолженность»";
                System.out.println("Переключено на опцию «Задолженность»");
                break;
            default:
                System.out.println("Передан недопустимый аргумент в метод clickOption(String option)");
                throw new IllegalArgumentException("Передан недопустимый аргумент в метод clickOption(String option)»");
        }

        indexCurrentOption = optionNumber - 1;

        try {
            wait.until(ExpectedConditions.elementToBeClickable(option));
            actions.moveToElement(option).pause(250).click().pause(250).build().perform();
        } catch (TimeoutException tEx) {
            System.out.println("Не сработал первый клик");
            actions.moveToElement(option).pause(250).click().pause(250).build().perform();
        }
    }

    // МЕТОДЫ ДЛЯ ЗАПОЛНЕНИЯ ПОЛЕЙ ВВОДА
    @Description("Метод по возвращению выбранного id поля текущей услуги оплаты")
    private WebElement getArrayIdInputField(int index) {
        if (inputFieldsIdArrays == null) {
            inputFieldsIdArrays = new WebElement[][]{
                    {inputFieldPhoneConnection, inputFieldSumConnection, inputFieldEmailConnection},
                    {inputFieldPhoneInternet, inputFieldSumInternet, inputFieldEmailInternet},
                    {inputFieldPhoneInstalment, inputFieldSumInstalment, inputFieldEmailInstalment},
                    {inputFieldPhoneArrears, inputFieldSumArrears, inputFieldEmailArrears}
            };
        }
        return inputFieldsIdArrays[indexCurrentOption][index];
    }

    // Заполнение поля ввода «Услуги связи»
    @Description("Передача данных для заполнения поля «Номер телефона»")
    public String typePhone(String value) {
        fillingInInputFields(value, getArrayIdInputField(0));
        return value;
    }

    @Description("Передача данных для заполнения поля «Сумма»")
    public String typeSum(String value) {
        fillingInInputFields(value, getArrayIdInputField(1));
        return value;
    }

    @Description("Передача данных для заполнения поля «E-mail для отправки чека»")
    public void typeEmail(String value) {
        fillingInInputFields(value, getArrayIdInputField(2));
    }

    // Общий метод заполнения полей ввода для всех услуг оплаты
    @Description("Заполнение выбранного поля")
    private void fillingInInputFields(String value, WebElement idInputField) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(idInputField));
            actions.moveToElement(idInputField).pause(250).click().pause(250).sendKeys(value).pause(250).build().perform();
        } catch (TimeoutException tEx) {
            System.out.println("Не сработал первый клик");
            actions.moveToElement(idInputField).pause(250).click().pause(250).sendKeys(value).pause(250).build().perform();
        }
        //idInputField.sendKeys(value);
        System.out.println("Поле «" + idInputField.getAttribute("placeholder") + "» заполнено значением «" + value + "»");
    }

    // МЕТОДЫ ДЛЯ КНОПКИ «ПРОДОЛЖИТЬ»
    @Description("Активация кнопки «Продолжить»")
    public void clickContinueButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(buttonContinue));
            actions.moveToElement(buttonContinue).pause(250).click().pause(250).build().perform();
        } catch (TimeoutException tEx) {
            System.out.println("Не сработал первый клик");
            actions.moveToElement(buttonContinue).pause(250).click().pause(250).build().perform();
        }
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(bepaidIframe));
    }

    // МЕТОД ДЛЯ ПРОВЕРКИ НАЗВАНИЯ БЛОКА «ОНЛАЙН ПОПОЛНЕНИЕ БЕЗ КОМИССИИ»
    @Description("Проверка названия блока «Онлайн пополнение без комиссии» на соответствие требованиям")
    public void assertHeader() {
        assertEquals("Онлайн пополнение\nбез комиссии", headerName.getText(),
                "Ожидаемое наименование заголовка не совпало с фактическим");
        assertEquals("h2", headerName.getTagName(), "Ожидаемый тег «h2» не совпал с фактическим");
        assertTrue(headerName.isDisplayed()); // проверка отображение заголовка
        System.out.println("Наименование заголовка блока «Онлайн пополнение без комиссии» в порядке");
    }

    // МЕТОД ДЛЯ ПРОВЕРКИ ПЛЕЙСХОЛДЕРОВ
    @Description("Проверка плейсхолдеров полей ввода окна «Онлайн пополнение без комиссии»")
    public void assertPlaceholders() {
        String[] numberFieldPlaceholders = {"Номер телефона", "Номер абонента", "Номер счета на 44", "Номер счета на 2073"};

        // Проверка наименований плейсхолдеров полей ввода
        assertEquals(numberFieldPlaceholders[indexCurrentOption], getArrayIdInputField(0).getAttribute("placeholder"));
        assertEquals("Сумма", getArrayIdInputField(1).getAttribute("placeholder"));
        assertEquals("E-mail для отправки чека", getArrayIdInputField(2).getAttribute("placeholder"));

        System.out.printf("«Плейсхолдеры опции %s в порядке\n\n", currentOption);
    }

    // МЕТОДЫ ДЛЯ ПРОВЕРКИ ЛОГОТИПОВ
    @Description("Идентификация логотипов")
    public void identityLogo() {
        System.out.println("Количество найденных логотипов: " + listLogo.size());
        for (WebElement logo : listLogo) {
            logoMap.put(logo.getAttribute("alt"), logo.getAttribute("src")); // каждому логотипу соответствует уникальный URI-адрес
        }
        System.out.println("Значения всех логотипов идентифицированы");
    }

    @Description("Вывод списка логотипов и их URI-адресов в консоль")
    public void listLogo() {
        int count = 1;
        System.out.println("Список логотипов:");
        for (Map.Entry<String, String> entry : logoMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("\n" + (count++) + ". Логотип: " + key + "\n   URI-адрес: " + value);
        }
        System.out.println();
    }

    @Description("Проверка логотипов на соответствие требованиям")
    public void assertLogo(List<String> altList, List<String> svgList) {
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

    // МЕТОДЫ ДЛЯ ПРОВЕРКИ ГИПЕРССЫЛКИ СЕРВИСА «ПОДРОБНЕЕ О СЕРВИСЕ»

    @Description("Активация гиперссылки «Подробнее о сервисе»")
    public void clickHelpHref() {
        try {
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", hrefHelp); // прокрутка страницы до кнопки выбора услуг
            wait.until(ExpectedConditions.elementToBeClickable(hrefHelp));
            hrefHelp.click();
        } catch (TimeoutException tEx) {
            System.out.println("Не сработал первый клик");
            hrefHelp.click();
        }

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        System.out.println("Осуществлён успешный переход на страницу сервиса «Подробнее о сервисе» " + driver.getCurrentUrl());
    }

    @Description("Проверка гиперссылки сервиса «Подробнее о сервисе» на соответствие требованиям")
    public void assertHelpHref() {
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

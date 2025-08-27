package Pages;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import jdk.jfr.Description;
import org.openqa.selenium.JavascriptExecutor;
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

@Feature("Окно «Онлайн пополнение без комиссии»")
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

    private String currentOption; // название текущей (выбранной услуги) оплаты
    private int indexCurrentOption; // индекс текущей (выбранной) услуги оплаты

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

    private Map<String, String> logoMap = new LinkedHashMap<>(); // данные логотипов

    // Гиперссылка «Подробнее о сервисе»
    @FindBy(linkText = "Подробнее о сервисе")
    private WebElement hrefHelp;

// КОНСТРУКТОР
    public PayPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this); // инициализация элементов аннотации @FindBy
    }

// МЕТОДЫ ДЛЯ КНОПКИ ВЫБОРА УСЛУГИ ДЛЯ ОПЛАТЫ
    @Step("Активация кнопки: раскрытие списка услуг оплаты")
    public void clickOptionButton() {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", optionButton); // прокрутка страницы до кнопки выбора услуг
        wait.until(ExpectedConditions.elementToBeClickable(optionButton));
        actions.moveToElement(optionButton).pause(250).click().pause(250).build().perform();
    }

    @Step("Выбор опции")
    public void clickOption(int indexOption) {
        WebElement option;
        switch (indexOption) {
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

        indexCurrentOption = indexOption - 1;

        wait.until(ExpectedConditions.elementToBeClickable(option));
        actions.moveToElement(option).pause(250).click().pause(250).build().perform();
    }

// МЕТОДЫ ДЛЯ ЗАПОЛНЕНИЯ ПОЛЕЙ ВВОДА
    @Description("Метод по возврату выбранного id поля текущей услуги оплаты")
    public WebElement getIdInputField(int index) {
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
    @Step("Передача данных для заполнения поля «Номер телефона»")
    public String typePhone(String value) {
        fillingInInputFields(value, getIdInputField(0));
        return value;
    }

    @Step("Передача данных для заполнения поля «Сумма»")
    public String typeSum(String value) {
        fillingInInputFields(value, getIdInputField(1));
        return value;
    }

    @Step("Передача данных для заполнения поля «E-mail для отправки чека»")
    public String typeEmail(String value) {
        fillingInInputFields(value, getIdInputField(2));
        return value;
    }

    // Общий метод для заполнения полей ввода для всех услуг оплаты
    @Description("Заполнение выбранного поля")
    private void fillingInInputFields(String value, WebElement idInputField) {
        wait.until(ExpectedConditions.elementToBeClickable(idInputField));
        actions.moveToElement(idInputField).pause(250).click().pause(250).sendKeys(value).pause(250).build().perform();
        System.out.println("Поле «" + idInputField.getAttribute("placeholder") + "» заполнено значением «" + value + "»");
    }

// КНОПКА «ПРОДОЛЖИТЬ»
    @Step("Активация кнопки «Продолжить»")
    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonContinue));
        actions.moveToElement(buttonContinue).pause(250).click().pause(250).build().perform();
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(bepaidIframe));
    }

// ЛОГОТИПЫ
    @Step("Идентификация логотипов")
    public void identityLogo() {
        System.out.println("Количество найденных логотипов: " + listLogo.size());
        for (WebElement logo : listLogo) {
            logoMap.put(logo.getAttribute("alt"), logo.getAttribute("src")); // каждому логотипу соответствует уникальный URI-адрес
        }
        System.out.println("Значения всех логотипов идентифицированы");
    }

    @Step("Вывод списка логотипов и их URI-адресов в консоль")
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

// ГИПЕРССЫЛКА СЕРВИСА «ПОДРОБНЕЕ О СЕРВИСЕ»
    @Step("Активация гиперссылки «Подробнее о сервисе»")
    public void clickHelpHref() {
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", hrefHelp); // прокрутка страницы до кнопки выбора услуг
        wait.until(ExpectedConditions.elementToBeClickable(hrefHelp));
        hrefHelp.click();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        System.out.println("Осуществлён успешный переход на страницу сервиса «Подробнее о сервисе» " + driver.getCurrentUrl());
    }

// МЕТОДЫ ВОЗВРАЩЕНИЯ
    @Description("Возврат названия текущей (выбранной услуги) оплаты")
    public String getCurrentOption(){
        return currentOption;
    }

    @Description("Возврат индекса названия текущей (выбранной услуги) оплаты")
    public int getIndexCurrentOption(){
        return indexCurrentOption;
    }

    @Description("Возврат наименования заголовка «Онлайн пополнение без комиссии»")
    public WebElement getHeaderName(){
        return headerName;
    }

    @Description("Возврат списка логотипов")
    public List<WebElement> getListLogo(){
        return listLogo;
    }

    @Description("Возврат гиперссылки «Подробнее о сервисе»")
    public WebElement getHrefHelp(){
        return hrefHelp;
    }
}

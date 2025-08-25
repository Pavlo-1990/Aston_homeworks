package Pages;

import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Iframe")
@DisplayName("Окно «Реквизиты банковской карты»")
public class BepaidPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    private final JavascriptExecutor js;

    // Список логотипов
    @FindBy(xpath = "//*[@class='cards-brands cards-brands__container ng-tns-c891095944-0 ng-trigger ng-trigger-brandsState ng-star-inserted']//img")
    private List<WebElement> listLogo;

    List<String> namesLogoList;

    public BepaidPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this); // инициализация элементов аннотации @FindBy
    }

// МЕТОДЫ ПРОВЕРКИ
    @Description("Проверка заголовка: сумма оплаты")
    public void sumHeader(String valueSum) {
        WebElement bepaidSumHeader = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class = 'pay-description__cost']/span[contains(text(), '" + valueSum + ".00 BYN')]")));
        assertEquals(valueSum + ".00 BYN", bepaidSumHeader.getText(),
                "Ожидаемый результат текста суммы в заголовке не совпал с фактическим");
        System.out.printf("Сумма оплаты в заголовке «%s» в порядке\n", bepaidSumHeader.getText());
    }

    @Description("Проверка заголовка: номер телефона")
    public void phoneHeader(String valuePhone) {
        WebElement bepaidPhoneHeader = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class = 'pay-description__text']/span[contains(text(), 'Номер:375" + valuePhone + "')]")));

        String fullText = bepaidPhoneHeader.getText();
        int index = fullText.indexOf("Номер:375");
        String phoneSubstring = fullText.substring(index);

        assertEquals("Номер:375" + valuePhone, phoneSubstring,
                "Ожидаемый результат текста номера телефона в заголовке не совпал с фактическим");
        System.out.printf("Номер телефона в заголовке «%s» в порядке\n", phoneSubstring);
    }

    @Description("Проверка кнопки «Оплатить» (состояние disabled)")
    public void SumButtonDisabled(String valueSum) {
        WebElement bepaidSumButtonDisabled = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@class = 'colored disabled']" +
                        "[contains(text(), 'Оплатить') and contains(text(), '" + valueSum + ".00 BYN')]")));
        assertEquals("Оплатить " + valueSum + ".00 BYN", bepaidSumButtonDisabled.getText(),
                "Ожидаемый результат текста на кнопке не совпал с фактическим");
        System.out.printf("Текст «%s» кнопки «Оплатить» (состояние disabled) в порядке\n", bepaidSumButtonDisabled.getText());
    }

    @Description("Проверка плейсхолдеров полей ввода окна «Реквизиты банковской карты»")
    public void assertPlaceholdersBepaid(Map<String, String> placeholdersBepaidMap){
        for (Map.Entry<String, String> entry : placeholdersBepaidMap.entrySet()) {
            String xpath = String.format("//input[@formcontrolname='%s']/following-sibling::label[text()='%s']", entry.getKey(), entry.getValue());
            WebElement inputField = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            assertEquals(entry.getValue(), inputField.getText(), String.format("Ожидаемый текст плейсхолдера «%s» не совпал с фактическим", entry.getValue()));
            System.out.printf("Реквизиты банковской карты: плейсхолдер «%s» в порядке\n", entry.getValue());
        }
    }

    // МЕТОДЫ ДЛЯ ПРОВЕРКИ ЛОГОТИПОВ
    @Description("Идентификация логотипов")
    public void identityLogo(List<String> namesLogoList) {
        this.namesLogoList = namesLogoList;
        System.out.println("Количество найденных логотипов: " + listLogo.size());
        System.out.println("Значения всех логотипов идентифицированы");
    }

    @Description("Вывод списка логотипов и их URI-адресов в консоль")
    public void listLogo() {
        int count = 1;
        System.out.println("Список логотипов:");
        for(int i = 0; i < listLogo.size(); i++){
            String name = namesLogoList.get(i);
            String uri = listLogo.get(i).getAttribute("src");
            System.out.println("\n" + (count++) + ". Логотип: " + name + "\n   URI-адрес: " + uri);
        }
        System.out.println();
    }

    @Description("Проверка логотипов на соответствие требованиям")
    public void assertLogo(List<String> namesLogoList, List<String> svgList) {
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
}

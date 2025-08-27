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

import java.util.List;


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

    private WebElement bepaidSumHeader; // заголовок: сумма оплаты
    private WebElement bepaidPhoneHeader; //  заголовок: номер телефона
    private WebElement bepaidSumButtonDisabled; // кнопка «Оплатить» (состояние disabled)
    private List<String> namesLogoList;

    public BepaidPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.actions = new Actions(driver);
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this); // инициализация элементов аннотации @FindBy
    }

// ОЖИДАНИЕ ЗАГОЛОВКА И КНОПКИ «ОПЛАТИТЬ» (СОСТОЯНИЕ DISABLED)
    @Description("Ожидание заголовка: сумма оплаты")
    public WebElement sumHeader(String sumValue) {
        bepaidSumHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class = 'pay-description__cost']/span[contains(text(), '" + sumValue + ".00 BYN')]")));
        return bepaidSumHeader;
    }

    @Description("Ожидание заголовка: номер телефона")
    public WebElement phoneHeader(String phoneValue) {
        bepaidPhoneHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class = 'pay-description__text']/span[contains(text(), 'Номер:375" + phoneValue + "')]")));
        return bepaidPhoneHeader;
    }

    @Description("Ожидание кнопки «Оплатить» (состояние disabled)")
    public WebElement SumButtonDisabled(String sumValue) {
        bepaidSumButtonDisabled = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@class = 'colored disabled']" +
                        "[contains(text(), 'Оплатить') and contains(text(), '" + sumValue + ".00 BYN')]")));
        return bepaidSumButtonDisabled;
    }

// ЛОГОТИПЫ
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

    @Description("Возврат списка логотипов")
    public List<WebElement> getListLogo(){
        return listLogo;
    }
}

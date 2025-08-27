package Pages;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Feature("Главная страница")
public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    private WebElement agreeCookiesButton;

    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.actions = new Actions(driver);

        this.driver.get("https://www.mts.by/");
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        String title = "МТС – мобильный оператор в Беларуси";

        if (!title.equals(driver.getTitle())) {
            throw new IllegalArgumentException("Заголовок вкладки не соответствует странице «МТС – мобильный оператор в Беларуси»");
        }
    }

    @Step("Ожидание всплывающего окна с куками")
    public WebElement handleCookiesPopup() {
        try {
            agreeCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-agree")));
            return agreeCookiesButton;
        } catch (TimeoutException tEx) {
            System.out.println("Всплывающее окно с куками не появилось. Тест продолжается.\n");
            return null;
        }
    }

    @Step("Активация кнопки «Принять» в куках")
    public void clickAgreeCookiesButton(){
        actions.moveToElement(agreeCookiesButton).clickAndHold().release().build().perform();
        System.out.println("Нажата кнопка «Принять». Согласие на обработку cookies-файлов принято\n");
    }
}

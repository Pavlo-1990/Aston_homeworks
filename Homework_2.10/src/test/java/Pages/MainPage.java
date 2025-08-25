package Pages;

import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Главная страница")
public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.actions = new Actions(driver);

        this.driver.get("https://www.mts.by/");
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        String title = "МТС – мобильный оператор в Беларуси";

        if(!title.equals(driver.getTitle())){
            throw new IllegalArgumentException("Заголовок вкладки не соответствует странице «МТС – мобильный оператор в Беларуси»");
        }
    }

    @Description("Обработка всплывающего окна с куками")
    public void handleCookiesPopup() {
        try {
            WebElement agreeCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-agree")));
            assertEquals("Принять", agreeCookiesButton.getText(), "Название кнопки «Принять» не совпало");

            if (agreeCookiesButton.isDisplayed()) {
                actions.moveToElement(agreeCookiesButton).clickAndHold().release().build().perform();
                System.out.println("Нажата кнопка «Принять». Согласие на обработку cookies-файлов принято\n");
            }
        } catch (TimeoutException tEx) {
            System.out.println("Всплывающее окно с куками не появилось. Тест продолжается.\n");
        }
    }
}

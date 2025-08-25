import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentLogoTest {
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
    @DisplayName("Тестирование логотипов платёжных систем")
    void elementsLogoTest() {
        List<String> listAlt = getListAlt();
        List<String> listSvg = getListSvg();

        // Список элементов с логотипами
        List<WebElement> listLogo = driver.findElements(By.xpath("//*[@id=\"pay-section\"]//img"));
        Map<String, String> mapLogo = new LinkedHashMap<>();
        int count = 1;

        System.out.println("\nКоличество найденных логотипов: " + listLogo.size() + "\n");

        for(WebElement logo : listLogo){
            mapLogo.put(logo.getAttribute("alt"),logo.getAttribute("src") ); // каждому логотипу соответствует уникальный URI-адрес
            assertTrue(logo.isDisplayed(), "Логотип не отобразился");
            assertEquals("img", logo.getTagName(), "Тег не совпал");
        }

        System.out.println("Список логотипов:");
        for (Map.Entry<String, String> entry : mapLogo.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println((count++) + ". Логотип: " + key + "\n   URI-адрес: " + value + "\n");
        }

        // Проверка названий логотипов
        for(int i = 0; i < listLogo.size(); i++) {
            assertEquals(listAlt.get(i), listLogo.get(i).getAttribute("alt"),"Название логотипа «" + listAlt.get(i) + "» не совпало не совпало");
        }

        // Проверка значений URI-адресов
        for(int i = 0; i < listLogo.size(); i++) {
            assertEquals("https://www.mts.by/local/templates/new_design/assets/html/images/pages/index/pay/" + listSvg.get(i),
                    listLogo.get(i).getAttribute("src"), "Название логотипа «" + listSvg.get(i) + "» не совпало не совпало");
        }
    }

    @Description("Метод по возвращению списка названий логотипов")
    private List<String> getListAlt() {
        List<String> listAlt = new ArrayList<>(Arrays.asList(
                "Visa",
                "Verified By Visa",
                "MasterCard",
                "MasterCard Secure Code",
                "Белкарт"
        ));
        return listAlt;
    }

    @Description("Метод по возвращению списка медиафайлов с логотипами формата .svg")
    private List<String> getListSvg() {
        List<String> listSvg = new ArrayList<>(Arrays.asList(
                "visa.svg",
                "visa-verified.svg",
                "mastercard.svg",
                "mastercard-secure.svg",
                "belkart.svg"
        ));
        return listSvg;
    }
}

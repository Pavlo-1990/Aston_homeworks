
    import Tests.Homework_2_10.ConnectionPayTest;
    import Tests.Homework_2_9_PageObject.HeaderNamePayTest;
    import Tests.Homework_2_9_PageObject.HrefHelpPayTest;
    import Tests.Homework_2_9_PageObject.LogoPayTest;
    import Tests.Homework_2_10.PlaceholdersPayTest;
    import io.qameta.allure.*;
    import org.junit.platform.suite.api.SelectClasses;
    import org.junit.platform.suite.api.Suite;
    import org.junit.platform.suite.api.SuiteDisplayName;

    @Suite
    @SelectClasses({
            HeaderNamePayTest.class, // [@Story] класс для тестирования наименования заголовка «Онлайн пополнение без комиссии»
            HrefHelpPayTest.class, // [@Story] класс для тестирования гиперссылки «Подробнее о сервисе»
            LogoPayTest.class, // [@Story] класс для тестирования логотипов платёжных систем
            ConnectionPayTest.class, // [@Story] класс для тестирования опции «Услуга связи»
            PlaceholdersPayTest.class // [@Story] класс для тестирования плейсхолдеров полей ввода
    })

    // Allure-репорт для всех тестов из домашних заданий 2.9 и 2.10
    @Epic("Сервис «Онлайн пополнение без комиссии»")
    @SuiteDisplayName("Набор тестов для проверки основных компонентов сервиса «Онлайн пополнение без комиссии»")
    public class SuiteTests {
    }


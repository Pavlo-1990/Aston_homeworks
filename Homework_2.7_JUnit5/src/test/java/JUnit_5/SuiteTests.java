package JUnit_5;

import org.junit.jupiter.api.DisplayName;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

// Класс для тестирования набора тестовых классов
@Suite
@SelectClasses({
    ArithmeticOperationsTest.class, // класс для тестирования программы по выполнению арифметических операций с двумя числами
    CompareIntegersTest.class, // класс для тестирования программы сравнения двух чисел
    FactorialTest.class, // класс для тестирования программы по вычислению факториала числа
    TriangleTest.class // класс для тестирования программы по вычислению площади треугольника
})
@SuiteDisplayName("Класс для тестирования набора тестовых классов")
public class SuiteTests {
}


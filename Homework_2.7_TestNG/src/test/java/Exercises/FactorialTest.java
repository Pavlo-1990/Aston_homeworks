package Exercises;

import jdk.jfr.Description;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

@Description("Класс для тестирования программы по вычислению факториала числа")
public class FactorialTest {
    @BeforeClass
    static void setup(){
        System.out.println("ТЕСТИРОВАНИЕ ПРОГРАММЫ ПО ВЫЧИСЛЕНИЮ ФАКТОРИАЛА ЧИСЛА");
        System.out.println("НАЧАЛО ТЕСТИРОВАНИЯ\n");
    }

    @BeforeMethod
    void setupThis(){
        System.out.println("Тест начался");
    }

    @AfterMethod
    void tearThis(){
        System.out.println("Тест окончен\n");
    }

    @AfterClass
    static void tear(){
        System.out.println("ТЕСТИРОВАНИЕ ОКОНЧЕНО\n");
    }

    // Допустимые значения аргументов, при которых вычисленное значение факториала
    // будет корректным для типа long, находятся в диапазоне от 0 до 20
    @Test(
        description = "Метод для вычисления факториала числа",
        dataProvider = "factorialPositiveArgs"
    )
    void positiveFactorial(int val) {
        long factorial = 1;
        long actual;
        long expected;

        for (int i = 0; i <= val; i++) {
            if (i > 0) {
                factorial = factorial * i;
            }
            actual = Factorial.factorial(i);
            expected = factorial;
            assertEquals(expected, actual);
        }
    }

    @Test(
        description = "Негативный тест метода для вычисления факториала числа",
        dataProvider = "factorialNegativeArgs",
        expectedExceptions = IllegalArgumentException.class
    )
    void negativeFactorial(int val) {
        try {
            System.out.println("Получен отрицательный аргумент, равный " + val);
            Factorial.factorial(val);
            fail("Метод не выбросил исключение IllegalArgumentException()");
        } catch (IllegalArgumentException e) {
            System.out.println("Выброшено исключение IllegalArgumentException()");
            fail("Тест провален, если по условию теста в аргументе метода не должно быть отрицательного числа");
        } catch (Exception e) {
            System.out.println("Выброшено исключение, отличное от IllegalArgumentException()");
            fail("Выброшено исключение, отличное от IllegalArgumentException()");
        }
    }

    @DataProvider(name = "factorialPositiveArgs")
    public Object[][] factorialPosArgsDataProvider() {
        return new Object[][]{
                {20}
        };
    }

    @DataProvider(name = "factorialNegativeArgs")
    public Object[][] factorialNegArgsDataProvider() {
        return new Object[][]{
                {-1}
        };
    }
}
package JUnit_5;

import Exercises.Factorial;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс для тестирования программы по вычислению факториала числа")
class FactorialTest {
    @BeforeAll
    static void setup(){
        System.out.println("ТЕСТИРОВАНИЕ ПРОГРАММЫ ПО ВЫЧИСЛЕНИЮ ФАКТОРИАЛА ЧИСЛА");
        System.out.println("НАЧАЛО ТЕСТИРОВАНИЯ\n");
    }

    @BeforeEach
    void setupThis(){
        System.out.println("Тест начался");
    }

    @AfterEach
    void tearThis(){
        System.out.println("Тест окончен\n");
    }

    @AfterAll
    static void tear(){
        System.out.println("ТЕСТИРОВАНИЕ ОКОНЧЕНО\n");
    }

    // Допустимые значения аргументов, при которых вычисленное значение факториала
    // будет корректным для типа long, находятся в диапазоне от 0 до 20
    @ParameterizedTest()
    @ValueSource(ints = {20, -1})
    @DisplayName("Метод для вычисления факториала числа")
    void factorial(int ints) {
        long factorial = 1;
        long actual;
        long expected;

        if(ints < 0) {
            System.out.println("Получен отрицательный аргумент, равный " + ints);
            assertThrows(IllegalArgumentException.class, () -> Factorial.factorial(ints),
                    "Выброшено исключение, отличное от IllegalArgumentException()");
            System.out.println("Выброшено исключение IllegalArgumentException()");
            fail("Тест провален, если по условию теста в аргументе метода не должно быть отрицательного числа");
        } else {
            for (int i = 0; i <= ints; i++) {
                if (i > 0) {
                    factorial = factorial * i;
                }
                actual = Factorial.factorial(i);
                expected = factorial;
                assertEquals(expected, actual);
            }
        }
    }
}

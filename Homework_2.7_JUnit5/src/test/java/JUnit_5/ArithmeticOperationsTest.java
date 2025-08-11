package JUnit_5;

import Exercises.ArithmeticOperations;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс для тестирования программы по выполнению арифметических операций с двумя числами")
class ArithmeticOperationsTest {
    @BeforeAll
    static void setup(){
        System.out.println("ТЕСТИРОВАНИЕ ПРОГРАММЫ ПО ВЫПОЛНЕНИЮ АРИФМЕТИЧЕСКИХ ОПЕРАЦИЙ С ДВУМЯ ЧИСЛАМИ");
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

// Метод для вычисления суммы двух чисел
    @ParameterizedTest
    @CsvSource({
        "3, 4, 7",   // x = 3, y = 4 -> expected = 7
        "-15, 67, 52",    // x = -15, y = 67 -> expected = 52
        "21, -21, 0"    // x = 21, y = -21 -> expected = 0
    })
    @DisplayName("Метод для вычисления суммы двух чисел")
    void sum(int x, int y, int expected) {
        int actual = ArithmeticOperations.sum(x,y);
        assertEquals(expected, actual);
    }

// Метод для вычисления разности между двумя числами
    @Disabled
    @Test
    @DisplayName("Метод для вычисления разности между двумя числами")
    void subtraction() {
        int actual = ArithmeticOperations.subtraction(-25,70);
        int expected = -95;
        assertEquals(expected, actual);
    }

// Метод для вычисления произведения двух чисел
    @ParameterizedTest
    @MethodSource("generateArgsForMultiplication")
    @DisplayName("Метод для вычисления произведения двух чисел")
    void multiplication(int x, int y) {
        int actual = ArithmeticOperations.multiplication(x,y);
        int expected = x * y;
        assertEquals(expected, actual);
    }

    static Stream<Arguments> generateArgsForMultiplication() {
        List<Arguments> args = new ArrayList<>();
        for (int x = 1; x <= 10; x++) {
            for (int y = 1; y <= 10; y++) {
                args.add(Arguments.of(x, y, x * y)); // expected = x * y
            }
        }
        return args.stream();
    }

// Метод для вычисления частного двух чисел
    @Test
    @DisplayName("Метод для вычисления частного двух чисел")
    void division() {
        float actual1 = ArithmeticOperations.division(4.4f,-0.3f);
        float expected1 = -14.66f;
        assertEquals(expected1, actual1,0.01, "Точность — 0.01");

        // При делении на ноль c участием float исключение не вызывается, но присваиваются специальные значения
        float actual2 = ArithmeticOperations.division(4.4f,0.0f);
        float expected2 = Float.POSITIVE_INFINITY;
        assertEquals(expected2, actual2);

        float actual3 = ArithmeticOperations.division(-3.1f,0.0f);
        float expected3 = Float.NEGATIVE_INFINITY;
        assertEquals(expected3, actual3);

        float actual4 = ArithmeticOperations.division(0.0f,0.0f);
        float expected4 = Float.NaN;
        assertEquals(expected4, actual4);
    }
}

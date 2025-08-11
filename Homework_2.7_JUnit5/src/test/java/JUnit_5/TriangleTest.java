package JUnit_5;

import Exercises.Triangle;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс для тестирования программы по вычислению площади треугольника")
class TriangleTest {
    // Поле класса теста
    private final Triangle triangle;
    private double base;
    private double height;
    private static int count = 1;
    private static int number = 1;

    // Конструктор класса теста
    TriangleTest(){
        this.triangle = new Triangle();
        System.out.println("Конструктор класса тестирования: " + (number++) + "-й треугольник для теста №" + count + " создан.");
    }

    // Общие для всех тестов методы
    @BeforeAll
    static void setup(){
        System.out.println("ТЕСТИРОВАНИЕ ПРОГРАММЫ ПО ВЫЧИСЛЕНИЮ ПЛОЩАДИ ТРЕУГОЛЬНИКА");
        System.out.println("НАЧАЛО ТЕСТИРОВАНИЯ\n");
    }

    @BeforeEach
    void setupThis(){
        base = count / 2.45;
        height = count * 0.73;
        System.out.println("Тест №" + count + " начался");
    }

    @AfterEach
    void tearThis(){
        System.out.println("Тест №" + (count++) + " окончен\n");
    }

    @AfterAll
    static void tear(){
        System.out.println("ТЕСТИРОВАНИЕ ОКОНЧЕНО\n");
    }

    // Основной метод тестирования
    @RepeatedTest(10)
    @DisplayName("Метод для вычисления площади треугольника")
    void area() {
        double actual = triangle.area(base, height);
        double expected = 0.5 * base * height;
        assertEquals(expected, actual);
    }
}
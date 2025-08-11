package Exercises;

import jdk.jfr.Description;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

@Description("Класс для тестирования программы по вычислению площади треугольника")
public class TriangleTest {
    // Поле класса теста
    private final Triangle triangle;
    private double base;
    private double height;
    private static int count = 1;

    // Конструктор класса теста
    TriangleTest(){
        this.triangle = new Triangle();
    }

    // Общие для всех тестов методы
    @BeforeClass
    static void setup(){
        System.out.println("ТЕСТИРОВАНИЕ ПРОГРАММЫ ПО ВЫЧИСЛЕНИЮ ПЛОЩАДИ ТРЕУГОЛЬНИКА");
        System.out.println("НАЧАЛО ТЕСТИРОВАНИЯ\n");
    }

    @BeforeMethod
    void setupThis(){
        base = count / 2.45;
        height = count * 0.73;
        System.out.println("Тест №" + count + " начался");
    }

    @AfterMethod
    void tearThis(){
        System.out.println("Тест №" + (count++) + " окончен\n");
    }

    @AfterClass
    static void tear(){
        System.out.println("ТЕСТИРОВАНИЕ ОКОНЧЕНО\n");
    }

    // Основной метод тестирования
    @Test(
        description = "Метод для вычисления площади треугольника",
        invocationCount = 10
    )
    void area() {
        double actual = triangle.area(base, height);
        double expected = 0.5 * base * height;
        assertEquals(expected, actual);
    }
}
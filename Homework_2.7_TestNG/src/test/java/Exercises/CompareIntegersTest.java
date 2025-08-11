package Exercises;

import jdk.jfr.Description;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

@Description("Класс для тестирования программы сравнения двух чисел")
public class CompareIntegersTest {
    @BeforeClass
    static void setup(){
        System.out.println("ТЕСТИРОВАНИЕ ПРОГРАММЫ ДЛЯ СРАВНЕНИЯ ДВУХ ЧИСЕЛ");
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

    @Test(
        description = "Метод для сравнения двух чисел",
        dataProvider = "compareArgs"
    )
    void compare(int a, int b) {
        String actual = CompareIntegers.compare(a, b);
        String expected;
        if (a > b){
            expected  = "Число " + a + " больше числа " + b;
        } else if (a < b) {
            expected  = "Число " + a + " меньше числа " + b;
        } else {
            expected  = "Число " + a + " равно числу " + b;
        }

        System.out.println(expected);
        assertEquals(expected, actual);
    }

    @DataProvider(name = "compareArgs")
    public Object[][] compareArgsDataProvider() {
        return new Object[][]{
            {100, 10},  // 100 > 10, expected = "Число 100 больше числа 10"
            {-45, 0},   // -45 < 0, expected = "Число -45 меньше числа 0"
            {-19, -25}, // -19 > -25, expected = "Число -19 больше числа -250"
            {80, 80}    // 80 = 80, expected = "Число 80 равно числу 80"
        };
    }
}
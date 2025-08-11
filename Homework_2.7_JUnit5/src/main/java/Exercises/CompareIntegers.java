package Exercises;

public class CompareIntegers {

    public static String compare(int a, int b) {
        if (a > b){
            return "Число " + a + " больше числа " + b;
        } else if (a < b) {
            return "Число " + a + " меньше числа " + b;
        } else {
            return "Число " + a + " равно числу " + b;
        }
    }
}

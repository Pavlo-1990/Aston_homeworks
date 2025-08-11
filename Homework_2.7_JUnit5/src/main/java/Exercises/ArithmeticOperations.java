package Exercises;

// Класс для арифметических операций с двумя числами
public class ArithmeticOperations {
    // Сумма
    public static int sum(int a, int b){
        int sum = a + b;
        System.out.println("Сумма чисел " + a + " и " + b + " равна " + sum);
        return sum;
    }

    // Вычитание
    public static int subtraction(int a, int b){
        int sub = a - b;
        System.out.println("Разность чисел " + a + " и " + b + " равна " + sub);
        return sub;
    }

    // Произведение
    public static int multiplication(int a, int b){
        int mult = a * b;
        System.out.println("Произведение чисел " + a + " и " + b + " равна " + mult);
        return mult;
    }

    // Деление
    public static float division(float a, float b){
        if( b == 0){
            //throw new IllegalArgumentException("На ноль делить нельзя");
        }

        float div =  a / b;
        System.out.println("Частное чисел " + a + " и " + b + " равно " + div);
        return div;
    }
}

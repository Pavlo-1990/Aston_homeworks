package Exercises;

public class Factorial {
    public static long factorial(long f){
        if (f < 0) {
            throw new IllegalArgumentException("Аргумент факториала может быть только целым неотрицательным числом.");
        }

        long factorial = 1;

        for (int i = 1; i <= f; i++){
            factorial = factorial * i;
        }

        System.out.println("Факториал числа " + f + " равен " + factorial);

        return factorial;
    }
}

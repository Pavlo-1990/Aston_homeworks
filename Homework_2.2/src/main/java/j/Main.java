package j;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

// Вступление
// Расширил возможности программы, добавив генераторы случайных чисел для аргументов методов и значений переменных

public class Main {

    static Scanner scan = new Scanner(System.in);   // Применяется в заданиях 9 и 13.
                                                    // Метод scan.close() применён в задании №13

    // Генератор случайных чисел типа int в диапазоне от (-100) до 100
    static Random rand = new Random();
    static int x1 = rand.nextInt(101) - 50;
    static int y1 = rand.nextInt(101) - 50;

    // Генератор случайных чисел типа int в диапазоне от 1 до 30
    static int x2 = 1 + rand.nextInt(30);
    static int y2 = 1 + rand.nextInt(30);

    // Генератор случайных чисел типа int в диапазоне от 1 до 10
    static int x3 = 1 + rand.nextInt(10);
    static int y3 = 1 + rand.nextInt(10);

    public static void main(String[] args) {
        System.out.println();

        // 1. Создайте метод printThreeWords(), который при вызове
        // должен отпечатать в столбец три слова: Orange, Banana, Apple;

        System.out.println("1. void printThreeWords()");
        printThreeWords();

        // 2. Создайте метод checkSumSign(), в теле которого объявите две int переменные a и b,
        // и инициализируйте их любыми значениями, которыми захотите. Далее метод должен просуммировать
        // эти переменные, и если их сумма больше или равна 0, то вывести в консоль сообщение
        // “Сумма положительная”, в противном случае - “Сумма отрицательная”;

        System.out.println("2. void checkSumSign()");
        checkSumSign();

        // 3. Создайте метод printColor() в теле которого задайте int переменную value и
        // инициализируйте ее любым значением. Если value меньше (0 включительно), то в консоль
        // метод должен вывести сообщение “Красный”, если лежит в пределах от 0 (0 исключительно)
        // до 100 (100 включительно), то “Желтый”, если больше 100 (100 исключительно) - “Зеленый”;

        System.out.println("3. void printColor()");
        printColor();

        // 4. Создайте метод compareNumbers(), в теле которого объявите две int переменные a и b,
        // и инициализируйте их любыми значениями, которыми захотите. Если a больше или равно b,
        // то необходимо вывести в консоль сообщение “a >= b”, в противном случае “a < b”;

        System.out.println("4. void compareNumbers()");
        compareNumbers();

        // 5. Напишите метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит
        // в пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false;

        System.out.println("5. boolean checkSum(" + x2 + ", " + y2 + ")");

        boolean resultCheckSum = checkSum(x2, y2);

        if (resultCheckSum) {
            System.out.println("Сумма чисел \"" + x2 + "\" и \"" + y2 + "\", равная \"" + (x2 + y2) +
                    "\", лежит в пределах от 10 до 20 включительно\n" + resultCheckSum + "\n");
        } else {
            System.out.println("Сумма чисел \"" + x2 + "\" и \"" + y2 + "\", равная \"" + (x2 + y2) +
                    ", не лежит в пределах от 10 до 20 включительно\n" + resultCheckSum + "\n");
        }


        // 6. Напишите метод, которому в качестве параметра передается целое число, метод должен
        // напечатать в консоль, положительное ли число передали или отрицательное.
        // Замечание: ноль считаем положительным числом;

        System.out.println("6. void checkSigh(" + x1 + "):");
        checkSighVoid(x1);

        // 7. Напишите метод, которому в качестве параметра передается целое число.
        // Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
        // Замечание: ноль считаем положительным числом;

        System.out.println("7. checkSighBoolean(" + x1 + ")");
        System.out.println( checkSighBoolean(x1) + "\n" );

        // 8. Напишите метод, которому в качестве аргументов передается строка и число,
        // метод должен отпечатать в консоль указанную строку, указанное количество раз;

        System.out.println("8. void printRepeaterString(\"Задание №8 выполнено!\", " + x3 + ")");
        printRepeaterString("Задание №8 выполнено!", x3);

        // 9. Напишите метод, который определяет, является ли год високосным, и возвращает
        // boolean (високосный - true, не високосный - false). Каждый 4-й год является високосным,
        // кроме каждого 100-го, при этом каждый 400-й – високосный;

        System.out.println("9. boolean checkLeap()");
        System.out.println(" " + checkLeap() + "\n");

        // 10. Задать целочисленный массив, состоящий из элементов 0 и 1.
        // Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
        // С помощью цикла и условия заменить 0 на 1, 1 на 0;

        System.out.println("10. int[] array10");

        int[] array10 = {0, 1, 1, 0 ,1 ,0, 0, 0, 1, 0};

        System.out.println(" Было:  " + Arrays.toString(array10));

        for (int i = 0; i < array10.length; i++){
            if (array10[i] == 0){
                array10[i] = 1;
            } else {
                array10[i] = 0;
            }
        }

        System.out.println(" Стало: " + Arrays.toString(array10) + "\n");

        // 11. Задать пустой целочисленный массив длиной 100.
        // С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 ... 100;

        System.out.println("11. int[] array11");

        int[] array11 = new int[100];

        for (int i = 0; i < array11.length; i++){
            array11[i] = i + 1;
        }

        System.out.println(" array11[0] = " + array11[0] + "\n array11[99] = " + array11[99] + "\n");

        // 12. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом,
        // и числа меньшие 6 умножить на 2;

        System.out.println("12. int[] array12");

        int[] array12 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        System.out.println(" Было:  " + Arrays.toString(array12));

        for (int i = 0; i < array12.length; i++){
            if (array12[i] < 6) {
                array12[i] = array12[i] * 2;
            }
        }

        System.out.println(" Стало: " + Arrays.toString(array12) + "\n");

        // 13. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
        // и с помощью цикла(-ов) заполнить его диагональные элементы единицами (можно только одну из диагоналей,
        // если обе сложно). Определить элементы одной из диагоналей можно по следующему принципу:
        // индексы таких элементов равны, то есть [0][0], [1][1], [2][2], ..., [n][n];

        System.out.println("13. int[][] twoDimArr");
        System.out.print(" Введите размерность двумерного массива (от 3 до 10): ");

        int n = 0;

        do {
            if (scan.hasNextInt()){
                n = scan.nextInt(); // Корректные значения типа int будут засчитаны до первого пробела — значения после пробела будут игнорированы

                if ( !( (n >= 3) && (n <= 10) ) ) {
                    System.out.print(" Введён некорректный размер двумерного массива типа int. Введите число от 3 до 10: ");
                    continue;
                }

                break;
            } else {
                System.out.print(" Введён некорректный тип данных. Введите данные типа int со значением от 3 до 10: ");
            }
            scan.nextLine(); // Очистка буфера
        } while(true);

        scan.close();

        int[][] twoDimArr = new int[n][n];
        for (int i = 0; i < twoDimArr.length; i++){
            // Заполнение единицами главной диагонали
            twoDimArr[i][i] = 1;

            // Заполнение единицами побочной диагонали
            twoDimArr[i][twoDimArr.length - 1 - i] = 1;
        }

        int index = 0;

        System.out.print("    ");

        for(int i = 0; i < twoDimArr.length; i++){
            System.out.print(i + "  ");
        }

        System.out.println();

        for (int[] i: twoDimArr) {
            System.out.println( " " + (index++) + " " + Arrays.toString(i) + " ");
        }

        System.out.println();

        // 14. Написать метод, принимающий на вход два аргумента: len и initialValue,
        // и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue.

        System.out.println("14. int[] ArrWithLen(" + x3 + ", " + y3 + ")");
        System.out.println(" " + Arrays.toString(ArrWithLen(x3, y3)));
    }

    // 1. Создайте метод printThreeWords(), который при вызове
    // должен отпечатать в столбец три слова: Orange, Banana, Apple;

    public static void printThreeWords(){
        System.out.println(" Orange\n Banana\n Apple\n");
    }

    // 2. Создайте метод checkSumSign(), в теле которого объявите две int переменные a и b,
    // и инициализируйте их любыми значениями, которыми захотите. Далее метод должен просуммировать
    // эти переменные, и если их сумма больше или равна 0, то вывести в консоль сообщение
    // “Сумма положительная”, в противном случае - “Сумма отрицательная”;

    public static void checkSumSign(){
        int a = x1;
        int b = y1;
        int sum = a + b;

        if (sum >= 0){
            System.out.println("Сумма чисел \"" + a + "\" и \"" + b + "\", равная \"" + (a + b) + "\", — положительное число\n");
        } else {
            System.out.println("Сумма чисел \"" + a + "\" и \"" + b + "\", равная \"" + (a + b) + "\", — отрицательное число\n");
        }
    }

    // 3. Создайте метод printColor() в теле которого задайте int переменную value и
    // инициализируйте ее любым значением. Если value меньше (0 включительно), то в консоль
    // метод должен вывести сообщение “Красный”, если лежит в пределах от 0 (0 исключительно)
    // до 100 (100 включительно), то “Желтый”, если больше 100 (100 исключительно) - “Зеленый”;

    public static void printColor(){
        int value = 100;

        if (value <= 0) {
            System.out.println("Красный\n");
        } else if (value > 0 && value <= 100) {
            System.out.println("Жёлтый\n");
        } else if (value > 100){
            System.out.println("Зелёный\n");
        }
    }

    // 4. Создайте метод compareNumbers(), в теле которого объявите две int переменные a и b,
    // и инициализируйте их любыми значениями, которыми захотите. Если a больше или равно b,
    // то необходимо вывести в консоль сообщение “a >= b”, в противном случае “a < b”;

    public static void compareNumbers(){
        int a = x1;
        int b = y1;

        if (a > b){
            System.out.println(a + " " + ">" + " " + b + "\n");
        } else if (a < b){
            System.out.println(a + " " + "<" + " " + b + "\n");
        } else {
            System.out.println(a + " " + "=" + " " + b + "\n");
        }
    }

    // 5. Напишите метод, принимающий на вход два целых числа и проверяющий, что их сумма лежит
    // в пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false;
    public static boolean checkSum(int x, int y){
        int sum = x + y;
        return sum >= 10 && sum <= 20;
    }

    // 6. Напишите метод, которому в качестве параметра передается целое число,
    // метод должен напечатать в консоль, положительное ли число передали или отрицательное.
    // Замечание: ноль считаем положительным числом;

    public static void checkSighVoid(int x){
        if (x >= 0) {
            System.out.println("Число \"" + x + "\" — положительное\n");
        } else {
            System.out.println("Число \"" + x + "\" — отрицательное\n");
        }
    }

    // 7. Напишите метод, которому в качестве параметра передается целое число.
    // Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
    // Замечание: ноль считаем положительным числом;

    public static boolean checkSighBoolean(int x){
        if (x < 0) {
            System.out.println("Число \"" + x + "\" — отрицательное");
            return true;
        } else {
            System.out.println("Число \"" + x + "\" — положительное");
            return false;
        }
    }

    // 8. Напишите метод, которому в качестве аргументов передается строка и число,
    // метод должен отпечатать в консоль указанную строку, указанное количество раз;

    public static void printRepeaterString(String s, int x){
        for (int i = 1; i <= x; i++) {
            System.out.println(" " + i + ". " + s);
        }

        System.out.println();
    }

    // 9. Напишите метод, который определяет, является ли год високосным, и возвращает
    // boolean (високосный - true, не високосный - false). Каждый 4-й год является високосным,
    // кроме каждого 100-го, при этом каждый 400-й – високосный;
        public static boolean checkLeap(){
            int year = 0;

            System.out.print(" Введите год. Отрицательное число — это год до н.э. Нулевого года не существует: ");

            do {
                if (scan.hasNextInt()){
                    year = scan.nextInt(); // Корректные значения типа int будут засчитаны до первого пробела — значения после пробела будут игнорированы

                    if (year == 0){
                        System.out.print(" Нулевого года не существует. Повторите ввод: ");
                        continue;
                    }

                    if ( (( year % 4 == 0) && !(year % 100 == 0))  ||  (year % 400 == 0)) {
                        System.out.println(" " + year + " — високосный год");
                        return true;
                    } else {
                        System.out.println(" " + year + " — невисокосный год");
                        return false;
                    }
                } else {
                    System.out.print(" Введён некорректный тип данных. Введите данные типа int: ");
                }
                scan.nextLine(); // Очистка буфера
            } while(true);

        }

    // 14. Написать метод, принимающий на вход два аргумента: len и initialValue,
    // и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue.

    public static int[] ArrWithLen(int len, int initialValue) {
        int[] awl = new int[len];

        for(int i = 0; i < awl.length; i++){
            awl[i] = initialValue;
        }

        // Arrays.fill(awl, initialValue);

        return awl;
    }
}
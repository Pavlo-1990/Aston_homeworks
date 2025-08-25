package Exercises;

import java.util.Arrays;
import java.util.Random;

public class MainApp {
    public static void main(String[] args) {
    // СОЗДАНИЕ МАССИВА С КОРРЕКТНЫМИ ДАННЫМИ
        String[][] strTwoDecArrCorrect = new String[4][4]; // массив с корректным завершением
        Random rand = new Random(); // генератор целых чисел типа int

        // Инициализация корректного двумерного массива типа String строками в виде целочисленных данных от 0 до 100
        for (int i = 0; i < strTwoDecArrCorrect.length; i++) {
            for (int j = 0; j < strTwoDecArrCorrect[i].length; j++) {
                strTwoDecArrCorrect[i][j] = String.valueOf(rand.nextInt(101));
            }
        }

        // Проверка двумерного массива на соответствие размерности 4x4, преобразование в int и суммирование его элементов
        try {
            checkSizeAndInitTwoDecArr(strTwoDecArrCorrect);
        } catch (MyArraySizeException masEx){
            throw new MyArraySizeException("Размер двумерного массива должен быть 4×4. " +
                    "Выбранный массив этому не соответствует.\n");
        } catch (MyArrayDataException madEx) {
            System.out.println("Преобразование в int не удалось");
            madEx.printStackTrace();
        }

        // Вывод корректного двумерного массива 4x4 в консоль
        System.out.println("Двумерный массив 4×4 типа String:");

        for (String[] s : strTwoDecArrCorrect) {
            System.out.print(Arrays.toString(s) + " ");
            System.out.println();
        }

        System.out.println();


    // КОД ДЛЯ АВАРИЙНОГО ЗАВЕРШЕНИЯ С MyArraySizeException
        String[][] strTwoDecArrSize = new String[4][10];

        // Инициализация
        for (int i = 0; i < strTwoDecArrSize.length; i++) {
            for (int j = 0; j < strTwoDecArrSize[i].length; j++) {
                strTwoDecArrSize[i][j] = String.valueOf(rand.nextInt(101));
            }
        }

        // Вызов метода, содержащий в себе массив, для аварийного завершения с MyArraySizeException
        try {
            // checkSizeAndInitTwoDecArr(strTwoDecArrSize);
        } catch (MyArraySizeException masEx){
            throw new MyArraySizeException("Размер двумерного массива должен быть 4×4. " +
                    "Выбранный массив этому не соответствует.\n");
        } catch (MyArrayDataException madEx) {
            System.out.println("Преобразование в int не удалось");
            madEx.printStackTrace();
        }

    // КОД ДЛЯ АВАРИЙНОГО ЗАВЕРШЕНИЯ С MyArrayDataException
        String[][] strTwoDecArrData = new String[4][4];

        // Копирование двумерных массивов по значению
        for(int i = 0; i < strTwoDecArrCorrect.length; i++){
            System.arraycopy(strTwoDecArrCorrect[i], 0, strTwoDecArrData[i], 0, strTwoDecArrCorrect[i].length);
        }

        strTwoDecArrData[2][1] = "Наличие нецифровых символов в String[2][1]";
        strTwoDecArrData[0][2] = "Наличие нецифровых символов в String[0][2]"; // исключение сработает здесь
        strTwoDecArrData[3][0] = "Наличие нецифровых символов в String[3][0]";

        try {
            // checkSizeAndInitTwoDecArr(strTwoDecArrData);
        } catch (MyArraySizeException masEx){
            throw new MyArraySizeException("Размер двумерного массива должен быть 4×4. " +
                    "Выбранный массив этому не соответствует.\n");
        } catch (MyArrayDataException madEx) {
            System.out.println("Преобразование в int не удалось");
            madEx.printStackTrace();
        }

    // КОД ДЛЯ АВАРИЙНОГО ЗАВЕРШЕНИЯ С ArrayIndexOutOfBoundsException
        try {
            // callAioobException();
        } catch (ArrayIndexOutOfBoundsException aioobEx) {
            System.out.println("Была совершена попытка обратиться к несуществующему элементу двумерного массива за его пределами.\n");
            aioobEx.printStackTrace();
        }
    }

// ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ КЛАССА
    // Проверка двумерного массива на соответствие размерности 4x4, преобразование в int и суммирование его элементов
    public static void checkSizeAndInitTwoDecArr(String[][] strTwoDecArr) {
        // проверка размерности двумерного массива
        for (int i = 0; i < strTwoDecArr.length; i++) {
            if ((strTwoDecArr.length != 4) || (strTwoDecArr[i].length != 4)) {
                System.out.println("Неподходящий двумерный массив");
                throw new MyArraySizeException("Данный текст в консоль не выводится. Исключение пробросится в метод main()");
            }
        }

        System.out.println("Массив соответствует размерности 4×4.");

        // преобразование строк двумерного массива в целочисленные значения типа int, а также суммирование этих значений
        int sum = 0;

        for (int i = 0; i < strTwoDecArr.length; i++) {
            for (int j = 0; j < strTwoDecArr[i].length; j++) {
                try {
                    sum += Integer.parseInt(strTwoDecArr[i][j]);
                } catch (NumberFormatException e){
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        System.out.println("Сумма целочисленных значений всех строковых элементов двумерного массива " +
                "размера 4×4 составляет " + sum + ".\n");
    }

    // Метод, содержащий в себе массив, для аварийного завершения с ArrayIndexOutOfBoundsException
    public static void callAioobException() throws ArrayIndexOutOfBoundsException {
        String[][] strTwoDecArrBound = new String[4][4];
        strTwoDecArrBound[4][0] = "За пределами первоначальной размерности двумерного массива";
    }
}
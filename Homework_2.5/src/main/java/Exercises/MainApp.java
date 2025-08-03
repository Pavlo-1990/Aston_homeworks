package Exercises;

import java.util.Arrays;
import java.util.Random;

public class MainApp {
    public static void main(String[] args) {
    // Создание массива с корректными данными
        String[][] strTwoDecArrCorrect = new String[4][4]; // массив с корректным завершением
        Random rand = new Random(); // генератор целых чисел типа int
        Object obj = new Object();

        // Инициализация корректного двумерного массива типа String строками в виде целочисленных данных от 0 до 100
        for (int i = 0; i < strTwoDecArrCorrect.length; i++) {
            for (int j = 0; j < strTwoDecArrCorrect[i].length; j++) {
                strTwoDecArrCorrect[i][j] = String.valueOf(rand.nextInt(101));
            }
        }

        // Вывод корректного двумерного массива в консоль
        System.out.println("Двумерный массив типа String:");

        for (String[] s : strTwoDecArrCorrect) {
            System.out.print(Arrays.toString(s) + " ");
            System.out.println();
        }

        System.out.println();

    // Код для аварийного завершения с MyArraySizeException
        String[][] strTwoDecArrSize = new String[4][10];

    // Код для аварийного завершения с MyArrayDataException в методе
        String[][] strTwoDecArrData = new String[4][4];

        // Копирование двумерных массивов по значению
        for(int i = 0; i < strTwoDecArrCorrect.length; i++){
            System.arraycopy(strTwoDecArrCorrect[i], 0, strTwoDecArrData[i], 0, strTwoDecArrCorrect[i].length);
        }

        strTwoDecArrData[2][1] = "Наличие нецифровых символов в String[2][1]";
        strTwoDecArrData[0][2] = "Наличие нецифровых символов в String[0][2]"; // исключение сработает здесь
        strTwoDecArrData[3][0] = "Наличие нецифровых символов в String[3][0]";

    // Вызов метода для преобразования в int и суммирования
        try {
            initTwoDecArr(strTwoDecArrCorrect); // метод с массивом для корректного завершения
            //initTwoDecArr(strTwoDecArrSize); // метод с массивом для аварийного завершения с MyArraySizeException
            //initTwoDecArr(strTwoDecArrData); // метод с массивом для аварийного завершения с MyArrayDataException
            //callAioobException(); // метод с массивом для аварийного завершения с ArrayIndexOutOfBoundsException
        } catch (MyArraySizeException masEx) {
            masEx.printStackTrace();
        } catch (MyArrayDataException madEx) {
            madEx.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException aioobEx) {
            System.out.println("Была совершена попытка обратиться к несуществующему элементу двумерного массива за его пределами.\n");
            aioobEx.printStackTrace();
        } finally {
            System.out.println("Вызовы всех трёх исключений происходят в методе main(), так как все возможные неполадки берут\n" +
                    "своё начало из кода этого метода, за исправление которого отвечают только те разработчики,\n" +
                    "которые работают с main().\n"
            );
        }
    }

    public static void initTwoDecArr(String[][] strTwoDecArr) {
    // проверка размерности двумерного массива
        if( (strTwoDecArr.length != 4) || (strTwoDecArr[0].length != 4) ){
            throw new MyArraySizeException("Размер двумерного массива должен быть 4×4. " +
                    "Выбранный массив этому не соответствует. Необходимо выбрать подходящий массив.\n");
        }

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

    // Код для аварийного завершения с ArrayIndexOutOfBoundsException
    public static void callAioobException() throws ArrayIndexOutOfBoundsException {
        String[][] strTwoDecArrBound = new String[4][4];
        strTwoDecArrBound[4][0] = "За пределами первоначальной размерности двумерного массива";
    }
}
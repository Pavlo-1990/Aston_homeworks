package Exercises;

// Исключение делает всё то же, что и NumberFormatException, с той оговоркой, что именно оно обрабатывается
// в блоке catch, а не его родитель, а также имеет своё уникальное сообщение, отображающееся в консоли
// с помощью его передачи в суперкласс через конструктор во время вызова исключения
public class MyArrayDataException extends NumberFormatException{
    private int row; // номер строки двумерного массива
    private int column; // номер столбца двумерного массива

    public MyArrayDataException(int row, int column){
        super("В String[" + row + "][" + column + "] обнаружены нецифровые символы");
        this.row = row;
        this.column = column;
    }
}

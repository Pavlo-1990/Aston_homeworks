package Exercises;

// Исключение унаследовано от IllegalArgumentException, как его частный случай, а именно —
// исключение срабатывает при несоответствии размерности двумерного массива типа String,
// которая должна быть 4×4.
class MyArraySizeException extends IllegalArgumentException{
    public MyArraySizeException(String message){
        super(message);
    }
}

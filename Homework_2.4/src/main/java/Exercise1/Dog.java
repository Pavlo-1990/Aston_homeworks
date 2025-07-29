package Exercise1;

public class Dog extends Animal implements Runnable, Swimable {
    private static int amount = 0; // подсчёт собак
    private static final int LENGTHMAXRUN = 500; // максимальная длина препятствия в беге
    private static final int LENGTHMAXSWIM = 10; // максимальная длина препятствия в плавании

    Dog(String name) {
        super(name);
        amount++;
    }

    @Override
    public void run(int length) {
        super.run(length);
    }
    
    @Override
    public void swim(int length){
        super.swim(length);
    }

    @Override
    public int getLengthMaxRun() {
        return LENGTHMAXRUN;
    }

    @Override
    public int getLengthMaxSwim() {
        return LENGTHMAXSWIM;
    }

    public static int getAmount(){
        return amount;
    }

}

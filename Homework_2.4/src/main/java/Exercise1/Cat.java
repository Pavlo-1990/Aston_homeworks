package Exercise1;

import java.util.Random;

public class Cat extends Animal implements Runnable{
    private static int amount = 0; // подсчёт котов
    private static final int LENGTHMAXRUN = 200; // максимальная длина препятствия в беге
    private boolean satiety = false; // сытость, по умолчанию кот голоден
    private int satietyMax; // порция еды для полной сытости кота

    private Random rand = new Random();

    // конструктор для создания массива голодных безымянных кошек, которые будут есть из миски
    public Cat(){
        amount++;
        satietyMax = 15 + rand.nextInt(10); // порция еды от 15 до 25 единиц
    }

    public Cat(String name){
        super(name);
        amount++;
    }

    @Override
    public void run(int length) {
        super.run(length);
    }

    @Override
    public int getLengthMaxRun() {  
        return LENGTHMAXRUN;
    }

    @Override
    public int getLengthMaxSwim() {
       return 0;
    }

    public static int getAmount(){
        return amount;
    }

    public void eatFromBowl(Bowl bowl, int portion){
        if (satiety == false){
            System.out.print("Порция кота  - " + portion + " единиц(а,ы) еды. ");
            satiety = bowl.takeFood(portion, satiety);

            if (satiety){
                System.out.println("Кот наелся.");
            } else {
                System.out.println("Кот не поел.");
            }
        } else {
            System.out.println("Кот уже ел.");
        }
    }

    public int getSatietyMax(){
        return satietyMax;
    }
}

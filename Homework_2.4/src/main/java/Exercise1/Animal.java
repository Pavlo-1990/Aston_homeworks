package Exercise1;

public abstract class Animal implements Runnable, Swimable { // "Все животные могут бежать и плыть..."
    private static int amount = 0; // подсчёт всех животных
    private String name;

    // конструктор для создания массива голодных безымянных кошек в классе Cat, которые будут есть из миски
    public Animal(){
        amount++;
    }

    public Animal(String name){
        this.name = name;
        amount++;
    }

    public void run(int length){
        if (length > 0 && length <= getLengthMaxRun()) {
            System.out.println(getClass().getSimpleName() + " " + getName() +
                    ": животное пробежало " + length + " м.");
        } else if (length > getLengthMaxRun()){
            System.out.println(getClass().getSimpleName() + " " + getName() +
                    ": животное не способно пробежать за раз свыше " + getLengthMaxRun() + " м.");
        } else {
            throw new IllegalArgumentException("Аргумент length должен быть положительным числом.");
        }
    }

    public void swim(int length) {
        if (length > 0 && length <= getLengthMaxSwim()) {
            System.out.println(getClass().getSimpleName() + " " + getName() +
                    ": животное проплыло " + length + " м.");
        } else if (length > getLengthMaxSwim() && getLengthMaxSwim() > 0 ){
            System.out.println(getClass().getSimpleName() + " " + getName() +
                    ": животное не способно проплыть за раз свыше " + getLengthMaxSwim() + " м.");
        } else {
            throw new IllegalArgumentException("Аргумент length должен быть положительным числом.");
        }
    }

    public String getName(){
        return name;
    }

    public static int getAmount(){ // возвращает количество существ
        return amount;
    }
}

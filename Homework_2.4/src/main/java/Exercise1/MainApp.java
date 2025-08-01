package Exercise1;

// По условию сказано "Все животные могут бежать и плыть..."
// А что значит "могут"? "Могут" в значении "должны"?
// Я сделал именно так, поэтому класс Animal имплементит интерфейсы по бегу и плаванию.
// По-хорошему, каждому подклассу надо было привязывать свои интерфейсы, а, конкретно,
// класс Cat не должен имплементить интерфейс по плаванию Swimable, так как коты не умеют
// плавать. Но условие путает


public class MainApp {
    public static void main(String[] args) {
        Dog dog1 = new Dog("Бим");
        Dog dog2 = new Dog("Халдей");
        Dog dog3 = new Dog("Хитрый");
        Dog dog4 = new Dog("Каштанка");
        Cat cat1 = new Cat("Котопёс");
        Cat cat2 = new Cat("Гав");

    // Количество существ
        System.out.println("\n\tКоличество");
        System.out.println("Количество собак: " + Dog.getAmount());
        System.out.println("Количество котов: " + Cat.getAmount());
        System.out.println("Количество животных: " + Animal.getAmount());

    // Бег
        System.out.println("\n\tБег");
        dog1.run(500);
        dog2.run(550);
            // dog3.run(0); // вызов исключения IllegalArgumentException
        dog4.run(1);
        cat1.run(199);
        cat2.run(250);

    // Плавание
        System.out.println("\n\tПлавание");
        dog1.swim(2);
            // dog2.swim(-20); // вызов исключения IllegalArgumentException
        dog3.swim(10);
        dog4.swim(11);

            // cat1.swim(100); // вызов исключения IllegalArgumentException
            // cat2.swim(-7100); // вызов исключения IllegalArgumentException

    // Массив кошек и миска с едой
        System.out.println("\n\tМассив кошек и миска с едой");
        Cat[] cats = new Cat[5];
        Bowl bowl = new Bowl(60);
        int count = 1;

        for( int i = 0; i < cats.length; i++ ){
            cats[i] = new Cat();
        }

        for (Cat c : cats){
            System.out.print("Кот " + (count++) + ": " );
            c.eatFromBowl(bowl, c.getSatietyMax());
        }

    // Наполнить миску и докормить не поевших котов
        System.out.println("\n\tНаполнить миску и докормить не поевших котов");
        count = 1;
        bowl.fillBowl(30);
        for (Cat c : cats){
            System.out.print("Кот " + (count++) + ": " );
            c.eatFromBowl(bowl, c.getSatietyMax());
        }

    // Количество существ после кормёжки котов
        System.out.println("\n\tКоличество существ после кормёжки котов");
        System.out.println("Количество собак: " + Dog.getAmount());
        System.out.println("Количество котов: " + Cat.getAmount());
        System.out.println("Количество животных: " + Animal.getAmount());
    }
}

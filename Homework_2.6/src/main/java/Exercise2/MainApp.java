package Exercise2;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
    // Ключевые параметры
        String phone; // номер телефона
        String surname; // фамилия

    // Вспомогательные параметры
        int number; // служебная переменная для инициализации количества фамилий и телефонных номеров
        int index = 0; // служебная переменная, служащая индексом для массивов и позицией для коллекций
        int count = 1; // служебная переменная для последовательной нумерации телефонных контактов при выводе в консоль

    // Генераторы значений для аргументов
        Surnames[] surnames = Surnames.values(); // массив всех возможных фамилий
        Random randPhone = new Random(); // генерация телефонных номеров
        Random randSurname = new Random(); // генерация фамилий
        Random randNumber = new Random(); // генерация количество телефонных номеров под одной фамилией

    // Создание списка уникальных фамилий
        Set<Surname> surnamesSet = new HashSet<>();
        number = Surnames.values().length + 1000; // количество фамилий
                                                  /* Добавил костыль "+ 1000", чтобы сгенерировать все предложенные
                                                  в enum фамилии наверняка (ведь это Set) — всё в целях демонстрации.
                                                  Но ничего не помешает в дальнейшем добавить другую фамилию =) */
        for(int i = 0; i < number; i++){
            surname = surnames[randSurname.nextInt(surnames.length)].toString();
            surnamesSet.add(new Surname(surname));
        }

    // Создание списка уникальных телефонных номеров
        List<Set<Phone>> phonesSetList = new ArrayList<>(); // массив уникальных списков телефонных номеров,
                                                            // каждый из которых привязан к одной фамилии

        for(int i = 0; i < surnamesSet.size(); i++){
            // Создание уникального списка телефонных номеров и его добавление в массив
            phonesSetList.add(new TreeSet<>(
                Comparator.comparing(Phone :: getPhone) // сортировка телефонных номеров по возрастанию
            ));

            number = 1 + randNumber.nextInt(5); // количество телефонных номеров у текущего списка

            // Создание телефонных номеров и их добавление в текущий список, привязанного к фамилии
            for(int j = 0; j < number; j++){
                phone = String.valueOf(1000000 + randPhone.nextInt(8999999));
                phonesSetList.get(i).add(new Phone(phone));
            }
        }

    // Создание телефонного справочника

        Map<Surname, Set<Phone>> phoneBookMap = new TreeMap<>(
            Comparator.comparing(Surname:: getSurname) // сортировка фамилий в алфавитном порядке
        );

        // Внесение данных в телефонный справочник
        for(Surname ss : surnamesSet) {
            phoneBookMap.put(ss, phonesSetList.get(index++));
        }

    // Консоль
        System.out.println("Телефонный справочник\n");

        for(Map.Entry<Surname, Set<Phone>> pbm : phoneBookMap.entrySet()){
            System.out.println( (count++) + ". " + pbm.getKey().getSurname() + ":");

            for(Phone ph : pbm.getValue()) {
                System.out.println(ph.getPhoneFormat());
            }
            System.out.println();
        }

        System.out.println();

        // Класс телефонного справочника с методами add() и get()
        System.out.println("Применение методов add() и get() класса PhoneBook\n");
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("ПЛЕХАНОВ", "111-11-11");
        phoneBook.add("БУСЛАЕВ", "222-22-22");
        System.out.println("БУСЛАЕВ " + phoneBook.get("БУСЛАЕВ"));
        System.out.println("ФЕДОТОВ " + phoneBook.get("ФЕДОТОВ"));
        System.out.println("СТРОГАНОВ " + phoneBook.get("СТРОГАНОВ"));
        System.out.println("ПЛЕХАНОВ " + phoneBook.get("ПЛЕХАНОВ"));
        phoneBook.add("БУСЛАЕВ", "533-23-99");
        phoneBook.add("БУСЛАЕВ", "100-55-12");
        System.out.println("БУСЛАЕВ " + phoneBook.get("БУСЛАЕВ"));



    }
}


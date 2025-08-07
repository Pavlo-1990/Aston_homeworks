package Exercise2;

import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

class PhonesSet {
    private Set<Phone> phonesSet = new TreeSet<>(
        Comparator.comparing(Phone:: getPhone) // сортировка телефонных номеров по возрастанию
    ); // список телефонных номеров, привязанный к одной фамилии
    private String phone;  // номер телефона
    private Random randPhone = new Random(); // генерация телефонных номеров
    private Random randNumber = new Random(); // генерация количество телефонных номеров под одной фамилией

    PhonesSet(String phone) {
        phonesSet.add(new Phone(phone));
    }

    PhonesSet(int randNumPhone) {
        int number = 1 + randNumber.nextInt(randNumPhone); // количество телефонных номеров у списка

        // Создание телефонных номеров и их добавление в список
        for (int j = 0; j < number; j++) {
            phone = String.valueOf(1000000 + randPhone.nextInt(8999999));
            phonesSet.add(new Phone(phone));
        }
    }

    protected void addPhone(String numPhone){
        phonesSet.add(new Phone(numPhone));
    }
    protected Set<Phone> getPhonesSet(){
        return phonesSet;
    }
}
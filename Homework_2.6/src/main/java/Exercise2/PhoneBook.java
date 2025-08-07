package Exercise2;

import java.util.*;

public class PhoneBook {

    private Map<String, Set<String>> phoneBook; // телефонный справочник

// Конструктор
    PhoneBook(){
        phoneBook = new TreeMap<>(); // сортировка фамилий в алфавитном порядке
    }

// Создание и добавление нового телефонного контакта
    public void add(String surname, String phoneNumber) {
        phoneBook.computeIfAbsent(surname, k -> new TreeSet<>()).add(phoneNumber); // добавление нового телефонного номера к фамилии
    }

// Получение по фамилии телефонного номера
    public Set<String> get(String surname) {
        return phoneBook.getOrDefault(surname, Collections.emptySet()); // возвращение телефонного номера для выбранной фамилии
                                                                        // либо пустого множества, если к фамилии не обнаружена
    }
}


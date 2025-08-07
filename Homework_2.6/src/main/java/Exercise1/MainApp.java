package Exercise1;

import java.util.*;
/* Все объявления я делал в методе main().  */
public class MainApp {
    public static void main(String[] args) {
    // Ключевые параметры
        String name; // имя студента
        int group; // группа
        int year; // курс
        int grade; // средний балл

    // Вспомогательные параметры
        int number = 30; // общее количество студентов

    // Генераторы значений для аргументов
        Names[] names = Names.values(); // массив всех возможных имён
        Random randName = new Random(); // генерация имён
        Random randGroup = new Random(); // генерация групп
        Random randYear = new Random(); // генерация курсов
        Random randGrade = new Random(); // генерация средних баллов

    // Создание списка студентов
        Set<Student> studentsSet = new TreeSet<>(
                Comparator.comparingInt(Student :: getYear). // сортировка по курсу,
                thenComparingInt(Student :: getGrade). // затем сортировка по среднему баллу,
                thenComparing(Student :: getName). // затем сортировка по имени,
                thenComparingInt(Student :: getGroup) // затем сортировка по группе.
        );

        // Создание студента и его добавление в список
        for(int i = 0; i < number; i++){
            name = names[randName.nextInt(names.length)].toString(); // 24 возможных имени для студентов
            group = 1 + randGroup.nextInt(3); // три группы студентов
            year = 1 + randYear.nextInt(5);   // пятилетний курс обучения
            grade = 1 + randGrade.nextInt(5); // пятибалльная система оценок
            studentsSet.add(new Student(name, group, year, grade)); // генерация студента
                                /* При генерации возможны дубликаты, которые будут игнорированы. Поэтому итоговое количество студентов
                                уменьшится на число обнаруженных повторений. В данном случае дубликатами выступают именно те студенты,
                                у которых наблюдается полное совпадение с уже существующим студентом по всем значениям в четырёх аргументах. */
        }

    // Консоль
        System.out.printf("\n%55s", "Список всех студентов до отчисления\n");
        Student.infoStudents(studentsSet); // список всех студентов до отчисления
        Student.infoYearStudents(studentsSet,4); // список студентов выбранного курса до отчисления
                                /* Вопреки заданию решил не сокращать отображение информации о студентах до имени и курса,
                                а придерживаться общей формы отображения, как во всех остальных методах, — так информация
                                в консоли легче читается, понимается и, что важно, удобна для сравнения касаемо изменений. */
        Student.expelStudents(studentsSet); // отчисление студентов за неуспеваемость (средний балл <3)
        System.out.println("------------------------------------------------------------------------------------------------------");

        System.out.printf("\n%65s", "Список всех студентов после отчисления, но до выпускного\n");
        Student.infoStudents(studentsSet); // список всех студентов после отчисления
        Student.infoYearStudents(studentsSet,4); // список студентов выбранного курса после отчисления
        Student.promoteStudents(studentsSet); // перевод на следующий курс и выпуск студентов
        Student.resetGrades(studentsSet); // обнуление среднего балла
        System.out.println("------------------------------------------------------------------------------------------------------");

        System.out.printf("\n%60s", "Список студентов в начале нового учебного года\n");
        Student.infoStudents(studentsSet); // список всех студентов после отчисления
        Student.infoYearStudents(studentsSet,5); // список студентов выбранного курса до отчисления
                                /* В консоли отобразил тех же студентов, которые теперь курсом старше, но за вычетом выпускников. */
    }
}
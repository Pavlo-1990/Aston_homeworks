package Exercise1;

import java.util.Iterator;
import java.util.Set;

class Student {
    private String name;
    private int group;
    private int year;
    private int grade;

    Student(String name, int group, int year, int grade) {
        this.name = name;
        this.group = group;
        this.year = year;
        this.grade = grade;
    }

    protected String getName() {
        return name;
    }

    protected int getGroup() {
        return group;
    }

    protected int getYear() {
        return year;
    }

    protected void setYear(int year) {
        this.year = year;
    }

    protected int getGrade() {
        return grade;
    }

    protected void setGrade(int grade) {
        this.grade = grade;
    }

// Список всех студентов
    protected static void infoStudents(Set<Student> students){
        int count = 1;

        for (Student stud : students){
            System.out.printf("%5s", (count++) + ". ");
            System.out.printf("%-27s", "Имя студента: " + stud.getName() );
            System.out.printf("%-12s", "Группа: " + stud.getGroup());
            System.out.printf("%-10s", "Курс: " + stud.getYear());
            System.out.printf("%-10s", "Средний балл: " + stud.getGrade() + "\n");
        }
    }

// Список студентов выбранного курса
    protected static void infoYearStudents(Set<Student> students, int course) {
        if (course < 1 || course > 5){
            throw new IllegalArgumentException("Неверное значение аргумента." +
                    "Аргумент с параметром int course может принимать значение от 1 до 5");
        }

        int count = 1;

        System.out.printf("\n%50s", "Студенты, учащиеся на " + course + " курсе\n");

        for (Student stud : students){
            if(stud.getYear() == course) {
                System.out.printf("%5s", (count++) + ". ");
                System.out.printf("%-27s", "Имя студента: " + stud.getName() );
                System.out.printf("%-12s", "Группа: " + stud.getGroup());
                System.out.printf("%-10s", "Курс: " + stud.getYear());
                System.out.printf("%-10s", "Средний балл: " + stud.getGrade() + "\n");
            }
        }
    }

// Отчисление студентов за неуспеваемость (средний балл <3)
    protected static void expelStudents(Set<Student> students){
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if(student.getGrade() < 3) {
                iterator.remove();
            }
        }
    }

// Перевод на следующий курс и выпуск студентов
    protected static void promoteStudents(Set<Student> students){
        // Перевод студентов на следующий курс
        Iterator<Student> iterator1 = students.iterator();

        while (iterator1.hasNext()) {
            Student student = iterator1.next();
            if(student.getYear() >= 1 || student.getYear() <= 5) {
                student.setYear(student.getYear() + 1);
            }
        }

        // Выпускники
        Iterator<Student> iterator2 = students.iterator();

        while (iterator2.hasNext()) {
            Student student = iterator2.next();
            if(student.getYear() > 5) {
                iterator2.remove();
            }
        }
    }

// Обнуление среднего балла
    protected static void resetGrades(Set<Student> students){
        Iterator<Student> iterator = students.iterator();

        while (iterator.hasNext()) {
            Student student = iterator.next();
            if(student.getYear() >= 1 || student.getYear() <= 5) {
                student.setGrade(0);
            }
        }
    }
}

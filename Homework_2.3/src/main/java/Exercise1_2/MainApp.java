package Exercise1_2;

import Exercise3.Park;

public class MainApp {
    public static void main(String[] args) {
        // 2. Создать массив из 5 товаров.

        System.out.println("Задание №1 и №2\n" );

        Product[] productsArray = new Product[5];

        productsArray[0] = new Product("Хлеб", "20.01.1999",
                "Хлебокомбинат \"Батонокрошитель\"", "Россия", 60.50f, false);

        productsArray[1] = new Product("Молоко","22.07.2025",
                "Нероссийский молочный комбинат","Белоруссия",89.99f, false);

        productsArray[2] = new Product("Холодильник", "13.12.2023",
                "Иглу","Россия", 45000.00f, true);

        productsArray[3] = new Product("Автомобиль","19.06.1965","Karen Mobile",
                "CSA",500.00f, true);

        productsArray[4] = new Product("Джинсы","01.01.2021","Lewis Ostrich without Co",
                "Великобритания",5000.00f, false);

        for (Product p : productsArray) {
            p.infoProduct();
        }
    }
}
package Exercise1_2;

// 1. Создать класс "Товар" с полями: название, дата производства, производитель,
// страна происхождения, цена, состояние бронирования покупателем.
// Конструктор класса должен заполнять эти поля при создании объекта.
// Внутри класса «Товар» написать метод, который выводит информацию об объекте в консоль.

public class Product {
    private String productName;
    private String productionDate;
    private String manufacturer;
    private String country;
    private float price;
    private boolean reservationStatus;

    public Product(String productName, String productionDate, String manufacturer,
            String country, float price, boolean reservationStatus){
        if (price <= 0){
            throw new IllegalArgumentException("Введено неположительное значение. Цена всегда должна быть положительным числом.");
        }
        this.productName = productName;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.reservationStatus = reservationStatus;
    }

    public void infoProduct(){
        System.out.println(
                "Название: " + this.productName + "\n" +
                "Дата производства: " + this.productionDate + "\n" +
                "Производитель: " + this.manufacturer + "\n" +
                "Страна происхождения: " + this.country + "\n" +
                "Цена: " + this.price + "\n" +
                "Состояние бронирования покупателем: " + this.reservationStatus + "\n"
        );
    }
}

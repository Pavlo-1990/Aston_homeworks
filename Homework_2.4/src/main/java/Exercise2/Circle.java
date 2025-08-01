package Exercise2;

public class Circle implements Shape {
    // Круг - это плоскость внутри окружности.
    // Окружность - это граница круга в виде замкнутой линии, все точки которой равноудалены от его центра;

    // характеристики круга и окружности
    private double radius;
    private double circumference; // длина окружности
    private double area; // площадь круга

    // цвета круга и окружности
    private String fillColor; // цвет заливки круга
    private String borderColor; // цвет границы окружности

    public Circle(double radius) {
        if ( !(radius > 0) ) {
            System.out.println("Отрицательное значение радиуса окружности");
            throw new IllegalArgumentException("Такой окружности (круга) не существует");
        }

        this.radius = radius;
    }

    // расчёт длины окружности
    @Override
    public double calcPerimeter() { // circumference - длина окружности
        return 2 * Math.PI * radius;
    }

    // расчёт площади круга
    @Override
    public double calcArea() {
        return Math.PI * Math.sqrt(radius);
    }

    // установка цветов заливки (круга) и границы (окружности)
    public void setColors(Colors fill, Colors border){
        fillColor = fill.toString(); // выбор цвета заливки
        borderColor = border.toString(); // выбор цвета границы
    }

    // инициализация длины окружности и площади круга
    public void initCalc() {
        if (circumference == 0.0) { circumference = calcPerimeter();}
        if (area == 0.0) { area = calcArea();}
    }

    // вывод информации о круге и окружности
    public void info(){
        System.out.printf("Длина окружности равна %.2f\n", circumference);
        System.out.printf("Площадь круга равна %.2f\n", area);
        System.out.println("Цвет круга (заливка): " + fillColor);
        System.out.println("Цвет окружности (граница): " + borderColor);
    }
}

package Exercise2;

public class Rectangle implements Shape {
    // стороны прямоугольника
    private double side13;
    private double side24;

    // характеристики треугольника
    private double perimeter; // периметр прямоугольника
    private double area; // площадь прямоугольника

    // цвета прямоугольника
    private String fillColor; // цвет заливки прямоугольника
    private String borderColor; // цвет границы прямоугольника

    Rectangle(double side13, double side24) {
        if ( !(side13 > 0) || !(side24 > 0) ) {
            System.out.println("Отрицательные значения сторон прямоугольника");
            try {
                throw new IllegalArgumentException("Такого прямоугольника не существует");
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }

        this.side13 = side13;
        this.side24 = side24;
    }

    // расчёт периметра прямоугольника
    @Override
    public double calcPerimeter() {
        return 2 * (side13 + side24);
    }

    // расчёт площади прямоугольника
    @Override
    public double calcArea() {
        return side13 * side24;
    }

    // установка цветов заливки и границы прямоугольника
    public void setColors(Colors fill, Colors border){
        fillColor = fill.toString(); // выбор цвета заливки
        borderColor = border.toString(); // выбор цвета границы
    }

    // инициализация периметра и площади прямоугольника
    public void initCalc() {
        if (perimeter == 0.0) { perimeter = calcPerimeter();}
        if (area == 0.0) { area = calcArea();}
    }

    // вывод информации о прямоугольнике
    public void info(){
        System.out.printf("Периметр прямоугольника равен %.2f\n", perimeter);
        System.out.printf("Площадь прямоугольника равна %.2f\n", area);
        System.out.println("Цвет заливки прямоугольника: " + fillColor);
        System.out.println("Цвет границы прямоугольника: " + borderColor);
    }
}

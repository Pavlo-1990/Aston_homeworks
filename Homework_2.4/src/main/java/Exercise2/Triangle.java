package Exercise2;

// Для расчёта площади треугольника принципиально упор делался
// на классическую формулу с высотой и основанием и только на неё

public class Triangle implements Shape {
    // стороны треугольника
    private double side1;
    private double side2;
    private double side3;
    private double base; // основание треугольника
    private double[] bases; // все возможные основания треугольника

    // характеристики треугольника
    private double height; // высота треугольника
    private double semiperimeter; // полупериметр треугольника
    private double perimeter; // периметр треугольника
    private double area; // площадь треугольника

    // цвета треугольника
    private String fillColor; // цвет заливки треугольника
    private String borderColor; // цвет границы треугольника

    public Triangle(double side1, double side2, double side3) {
        if (!(side1 > 0) || !(side2 > 0) || !(side3 > 0)) {
            System.out.println("Отрицательные значения сторон треугольника");
            try {
                throw new IllegalArgumentException("Такого треугольника не существует");
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }

        if (!((side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1))) {
            System.out.println("Третья сторона треугольника больше двух других");
            try {
                throw new IllegalArgumentException("Такого треугольника не существует");
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
        }

        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.bases = new double[]{this.side1, this.side2, this.side3};
    }

    // расчёт периметра треугольника
    @Override
    public double calcPerimeter() {
        return (side1 + side2 + side3);
    }

    // расчёт площади треугольника
    @Override
    public double calcArea() {
        return 0.5 * base * height;

        // формула Герона
        // return Math.sqrt(semiperimeter() * (semiperimeter() - side1) * (semiperimeter() - side2) * (semiperimeter() - side3));
    }

    // установка цветов заливки и границы треугольника
    public void setColors(Colors fill, Colors border){
        fillColor = fill.toString(); // выбор цвета заливки
        borderColor = border.toString(); // выбор цвета границы
    }

    // инициализация характеристик треугольника, в том числе периметра и площади
    public void initCalc(int baseIndex) {
        base = bases[baseIndex]; // выбор основания треугольника
        if (perimeter == 0.0) { perimeter = calcPerimeter();}
        if (semiperimeter == 0.0) { semiperimeter = calcSemiperimeter();}
        height = calcHeight(); // высота зависит от основания треугольника
        if (area == 0.0) { area = calcArea();} // расчёт зависит от base и height
    }

    // вывод информации о треугольнике
    public void info(){
        System.out.printf("Периметр треугольника равен %.2f\n", perimeter);
        System.out.printf("Площадь треугольника равна %.2f\n", area);
        System.out.println("Цвет заливки треугольника: " + fillColor);
        System.out.println("Цвет границы треугольника: " + borderColor);
    }

    // расчёт полупериметра треугольника
    public double calcSemiperimeter(){
        return perimeter/2;
    }

    // расчёт высоты треугольника
    public double calcHeight(){
        return (2/base) * Math.sqrt(
                calcSemiperimeter() * (semiperimeter - side1) * (semiperimeter - side2) * (semiperimeter - side3));
    }
}

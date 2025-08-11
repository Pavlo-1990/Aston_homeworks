package Exercises;

public class Triangle {
    public Triangle(){}
    // Площадь треугольника
    public double area(double base, double height){
        if (base < 0 || height < 0) {
            throw new IllegalArgumentException("Основание и высота треугольника могут принимать только положительные значения");
        }

        double area = 0.5 * base * height;
        System.out.printf("Площадь треугольника при основании %.2f и высоте %.2f равна %.2f.\n", base , height, area);
        return area;
    }
}
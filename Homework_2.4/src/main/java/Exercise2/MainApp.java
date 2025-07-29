package Exercise2;

public class MainApp {
  public static void main(String[] args) {
        // Треугольники
            // Triangle impossibleTriangle1 = new Triangle(-5, 1, -0.4); // отрицательные значения сторон
            // Triangle impossibleTriangle2 = new Triangle(6, 2.12, 3.45 ); // третья сторона больше двух других
        Triangle triangle = new Triangle(3, 4, 5);
        triangle.setColors(Colors.BLACK, Colors.GREEN);
        triangle.initCalc(1);
        triangle.info();
        System.out.println();

        // Прямоугольники
        Rectangle rectangle = new Rectangle(3.1, 7.5);
        rectangle.setColors(Colors.BLUE, Colors.BROWN);
        rectangle.initCalc();
        rectangle.info();
        System.out.println();

        // Круги и окружности
        Circle circle = new Circle(8.4);
        circle.setColors(Colors.PURPLE, Colors.PURPLE);
        circle.initCalc();
        circle.info();
        System.out.println();
    }
}

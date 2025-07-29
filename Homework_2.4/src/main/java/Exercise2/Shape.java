package Exercise2;

public interface Shape {
    // расчёт периметра геометрической фигуры (дефолтный метод)
    default double calcPerimeter(){
        System.out.println("У неопределённой геометрической фигуры существует только абстрактное понятие периметра.");
        return 0.0;
    }

    // расчёт площади геометрической фигуры
    default double calcArea(){
        System.out.println("У неопределённой геометрической фигуры существует только абстрактное понятие площади.");
        return 0.0;
    }

    // установить цвет заливки и цвет границы геометрической фигуры
    void setColors(Colors fill, Colors border);
}

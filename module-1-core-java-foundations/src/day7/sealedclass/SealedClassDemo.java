package day7.sealedclass;

import java.util.List;

/**
 * Day 7 - Sealed class: the permitted subclasses and a switch over them.
 */
public class SealedClassDemo {

    // allowed, because Triangle is non-sealed
    static class RightTriangle extends Triangle {
        RightTriangle(double base, double height) {
            super(base, height);
        }
    }

    // class Hexagon extends Shape { ... }        // does not compile: Hexagon is not in the permits list of Shape

    public static void main(String[] args) {
        List<Shape> shapes = List.of(new Circle(2), new Rectangle(4, 5), new Triangle(6, 3), new RightTriangle(3, 4));

        for (Shape shape : shapes) {
            System.out.println(describe(shape) + ", area = " + String.format("%.2f", shape.area()));
        }
        // Circle with radius 2.0, area = 12.57
        // Rectangle 4.0 x 5.0, area = 20.00
        // Triangle with base 6.0 and height 3.0, area = 9.00
        // Triangle with base 3.0 and height 4.0, area = 6.00
    }

    // no default needed: Circle, Rectangle and Triangle are all the Shapes there can be
    private static String describe(Shape shape) {
        return switch (shape) {
            case Circle circle -> "Circle with radius " + circle.getRadius();
            case Rectangle rectangle -> "Rectangle " + rectangle.getWidth() + " x " + rectangle.getHeight();
            case Triangle triangle -> "Triangle with base " + triangle.getBase() + " and height " + triangle.getHeight();
        };
    }
}

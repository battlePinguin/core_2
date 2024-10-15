package com.javaCode;

public class GeometryUtils {

    public static boolean compareShapes(Shape shape1, Shape shape2) {
        return shape1.getArea() == shape2.getArea();
    }

    public static double convertUnits(double value, double conversionRate) {
        return value * conversionRate;
    }
}

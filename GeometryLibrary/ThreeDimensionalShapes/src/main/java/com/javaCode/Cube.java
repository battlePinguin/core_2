package com.javaCode;

public class Cube implements ThreeDimensionalShape {
    private double side;

    public Cube(double side) {
        this.side = side;
    }

    public double getVolume() {
        return side * side * side;
    }

    public double getSurfaceArea() {
        return 6 * side * side;
    }
}

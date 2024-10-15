package com.javaCode;

public class Sphere implements ThreeDimensionalShape {
    private double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }

    public double getVolume() {
        return (4 / 3) * Math.PI * Math.pow(radius, 3);
    }

    public double getSurfaceArea() {
        return 4 * Math.PI * radius * radius;
    }
}

package com.javaCode;


public class App 
{
    public static void main( String[] args ) {

        Shape circle = new Circle(5);
        Shape circle2 = new Circle(7);
        Shape rectangle = new Rectangle(4, 6);
        Shape triangle = new Triangle(3, 4, 5);
        ThreeDimensionalShape cube = new Cube(3);
        ThreeDimensionalShape sphere = new Sphere(5);

        System.out.println("Circle Area: " + circle.getArea());
        System.out.println("Circle Perimeter: " + circle.getPerimeter());
        System.out.println("Circle Info: " + circle.getInfo());

        System.out.println("Rectangle Area: " + rectangle.getArea());
        System.out.println("Rectangle Perimeter: " + rectangle.getPerimeter());

        System.out.println("Triangle Area: " + triangle.getArea());
        System.out.println("Triangle Perimeter: " + triangle.getPerimeter());


        boolean areEqual = GeometryUtils.compareShapes(circle, circle2);
        System.out.println("Are the two circles equal in area? " + areEqual);
        double conversionRateForArea = 10000; // 1 м^2 = 10000 см^2
        System.out.println("\n2D Figures (in centimeters):");
        System.out.println("Circle: Area = " + GeometryUtils.convertUnits(circle.getArea(), conversionRateForArea) + " cm^2");
        System.out.println("Rectangle: Area = " + GeometryUtils.convertUnits(rectangle.getArea(), conversionRateForArea) + " cm^2");
        System.out.println("Triangle: Area = " + GeometryUtils.convertUnits(triangle.getArea(), conversionRateForArea) + " cm^2");

        System.out.println("\n3D Figures:");
        System.out.println("Cube: Volume = " + cube.getVolume() + ", Surface Area = " + cube.getSurfaceArea());
        System.out.println("Sphere: Volume = " + sphere.getVolume() + ", Surface Area = " + sphere.getSurfaceArea());

    }
}

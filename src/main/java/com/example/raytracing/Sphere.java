package com.example.cs255_1;

/*Author
Upenyu Hlangabeza - 2035108
 */

public class Sphere  {

    Vector cs ;
    double radius;
    Vector colour;

    private Sphere selectedSphere;

    public Sphere(double radius,Vector position,Vector colour){
        this.radius=radius;
        this.cs = position;
        this.colour = colour;
    }

    public Vector getCs(){
        return cs;
    }
    public Vector getColour(){
        return colour;
    }
    public double getRadius(){
        return radius;
    }
    public void setCs(Vector cs) {
        this.cs=cs;
    }    public void setColour(Vector colour) {
        this.colour=colour;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}

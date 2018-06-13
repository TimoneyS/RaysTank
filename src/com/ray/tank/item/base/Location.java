package com.ray.tank.item.base;

public class Location {
    
    private double x;
    private double y;
    
    private double bakX;
    private double bakY;
    
    public Location(double x, double y) {
         this.x = x;
         this.y = y;
    }
    
    public void save() {
        bakX = x;
        bakY = y;
    }
    
    public void load() {
        x = bakX;
        y = bakY;
    }
    
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double X() {
        return x;
    }
    
    public double Y() {
        return y;
    }
    
}

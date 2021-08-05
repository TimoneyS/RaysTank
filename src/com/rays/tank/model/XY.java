package com.rays.tank.model;

public class XY {
    private int x;
    private int y;

    public static XY of(int x, int y) {
        return new XY(x, y);
    }

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public XY multiply(int blockSize) {
        x *= blockSize;
        y *= blockSize;
        return this;
    }
}

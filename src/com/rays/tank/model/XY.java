package com.rays.tank.model;

public class XY {
    private int x;
    private int y;

    public static XY of(int x, int y) {
        return new XY(x, y);
    }

    public static XY of(int[] dir) {
        return new XY(dir[0], dir[1]);
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

    public XY plus(XY xy) {
        x += xy.getX();
        y += xy.getY();
        return this;
    }

    public int maxDist(XY xy) {
        return Math.max(
                Math.abs(getX() - xy.getX()),
                Math.abs(getY() - xy.getY())
        );
    }

    public boolean noMoreThan(int i) {
        return getX() < i || getY() < i;
    }
}

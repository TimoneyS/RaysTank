package com.rays.tank.common;

import com.rays.tank.model.XY;

public class XYBuilder {
    private XY xy = new XY(0, 0);

    private XYBuilder(XY xy) {
        this.xy = xy;
    }

    public static XYBuilder of(int xAndy) {
        return of(xAndy, xAndy);
    }

    public static XYBuilder of(XY xy) {
        return of(xy.getX(), xy.getY());
    }

    public static XYBuilder of(int[] arr) {
        return of(arr[0], arr[1]);
    }

    public static XYBuilder of(int x, int y) {
        return new XYBuilder(new XY(x, y));
    }

    public XYBuilder multiply(int number) {
        xy.setX(xy.getX() * number);
        xy.setY(xy.getY() * number);
        return this;
    }

    public XYBuilder plus(int d) {
        return plus(d, d);
    }

    public XYBuilder plus(XY xy) {
        return plus(xy.getX(), xy.getY());
    }

    public XYBuilder plus(int dx, int dy) {
        xy.setX(xy.getX() + dx);
        xy.setY(xy.getY() + dy);
        return this;
    }

    public XY get() {
        return xy;
    }
}

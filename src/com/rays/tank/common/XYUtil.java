package com.rays.tank.common;

import com.rays.tank.model.XY;

public class XYUtil {
    public static XY create(int x, int y) {
        return new XY(x, y);
    }

    public static XY plus(XY xy, int dx, int dy) {
        return create(xy.getX() + dx, xy.getY() + dy);
    }

    public static int maxDist(XY xy, XY newXY) {
        return Math.max(
                Math.abs(xy.getX() - newXY.getX()),
                Math.abs(xy.getY() - newXY.getY())
                );
    }

    public static boolean noMoreThen(XY newXY, int i) {
        return newXY.getX() < i || newXY.getY() < i;
    }
}

package com.rays.tank.view;

import java.awt.*;

public class Drawer {

    public static void drawShape(Graphics2D g2, Color color, Shape shape) {
        g2.setColor(color);
        g2.fill(shape);
    }

    public static void drawWithBound(Graphics2D g2, Color color, Shape shape) {
        g2.setColor(color);
        g2.fill(shape);

        g2.setColor(Color.BLACK);
        g2.draw(shape);
    }

}

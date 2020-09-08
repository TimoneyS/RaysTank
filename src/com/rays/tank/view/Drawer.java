package com.rays.tank.view;

import java.awt.*;

public class Drawer {

    public static void drawRect(Graphics g, Color color, int x, int y, int w, int h) {
        Color c = g.getColor();
        g.setColor(color);
        g.fillRect(x-w/2, y - h/2, w, h);
        g.setColor(Color.BLACK);
        g.drawRect(x-w/2, y - h/2, w, h);
        g.setColor(c);
    }

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

package com.rays.tank.view;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class DrawUtil {
    public static void drawImage(Graphics graphics, Image image, int direction, int x, int y, int blockSize) {
        Graphics2D g2 = (Graphics2D) graphics;
        AffineTransform affineTransform = g2.getTransform();
        g2.rotate(Math.PI * 2 * direction * 90 / 360, x, y);
        graphics.drawImage(image, x - blockSize / 2, y - blockSize / 2, blockSize, blockSize, null);
        g2.setTransform(affineTransform);
    }
}

package com.ray.tank.common;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public class DrawUtil {
    
    public static void drawShape(Graphics2D g2, Color color, Shape shape) {
        
        g2.setColor(color);
        g2.fill(shape);
        
        g2.setColor(Color.BLACK);
        g2.draw(shape);

        

    }
    
}

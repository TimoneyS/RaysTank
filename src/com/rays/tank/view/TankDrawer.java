package com.rays.tank.view;

import com.rays.tank.common.DrawUtil;
import com.rays.tank.model.Tank;

import java.awt.*;
import java.awt.geom.*;

public class TankDrawer {

    public static void draw(Graphics2D g2, Tank tank) {

        AffineTransform odlTramsform = g2.getTransform();

        double x = tank.getX(), y = tank.getY(), size = tank.getSize(), rad = tank.getDirection();
        g2.rotate(rad, x, y);

        RoundRectangle2D rbody = new  RoundRectangle2D.Double(
                x-3*size, y-3*size, 6*size, 6*size, 1*size, 1*size);
        DrawUtil.drawShape(g2, tank.getColor(), rbody);

        Drawer.drawShape(g2, Color.BLACK, new Rectangle2D.Double(x-5*size, y-5*size, 2*size, 10*size));
        Drawer.drawShape(g2, Color.BLACK, new Rectangle2D.Double(x+3*size, y-5*size, 2*size, 10*size));

        // 绘制履带
        for(int i = 0; i < 10; i += 2) {
            // y2 = y + size*(weelState/10 + 2*i);
            double y2 = y + size*i + tank.getMoveStatus()/5*size - 3 * size;
            g2.draw(new Line2D.Double(x-5*size, y2, x-3*size, y2));
            g2.draw(new Line2D.Double(x+5*size, y2, x+3*size, y2));
        }

        // 绘制炮管
        g2.setColor(Color.BLACK);
        Shape rfire = new Rectangle2D.Double(x-0.4*size, y-8*size, 1*size, 8*size);
        g2.fill(rfire);

        // 绘制炮台
        g2.setColor(Color.BLACK);
        Shape rfilePlat = new Ellipse2D.Double(x-1.5*size, y-1.5*size, 3*size, 3*size);
        g2.fill(rfilePlat);

        g2.setTransform(odlTramsform);
    }
}

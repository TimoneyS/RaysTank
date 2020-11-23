package com.rays.tank.model;

import com.rays.tank.common.Const;
import com.rays.tank.common.DrawUtil;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BattleField {
    private BufferedImage image = new BufferedImage(Const.D_WIDTH, Const.D_HEIGTH, BufferedImage.TYPE_INT_RGB);
    private Graphics graphics = image.createGraphics();
    private Map<Integer, Tank> map = new HashMap<>();

    public BattleField(InputStream inputStream) {
        try(BufferedReader bis = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int tag = 0;
            int id = 2;
            while ((line = bis.readLine()) != null) {
                if (line.contains("[BOT]")) {
                    tag = 1;
                    continue;
                } else if (line.contains("[PLA]")) {
                    tag = 2;
                    continue;
                }
                if (tag == 0) {
                    continue;
                }

                if (tag == 1) {
                    Tank tank = parse(line);
                    if (tank != null) {
                        map.put(id ++, tank);
                    }
                } else if (tag == 2) {
                    Tank tank = parse(line);
                    map.put(0, tank);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Tank parse(String line) {
        int start = line.indexOf('[');
        int end = line.indexOf(']');
        if (start >= end) {
            return null;
        }
        String data = line.substring(start+1, end);
        String[] arr = data.trim().split(",");
        Tank t = new Tank();
        t.setX(Integer.parseInt(arr[0].trim()));
        t.setY(Integer.parseInt(arr[1].trim()));
        t.setDirection(Integer.parseInt(arr[2].trim()));
        return t;
    }

    public synchronized Image getImage() {
        graphics.fillRect(0,0, image.getWidth(), image.getHeight());
        for (Tank tank : map.values()) {
            drawTank(tank);
        }
        return image;
    }

    private void drawTank(Tank tank) {

        int x = tank.getX();
        int y = tank.getY();
        int rad = tank.getDirection();
        double size = Const.size;
        Color c = graphics.getColor();

        Graphics2D g2 = (Graphics2D)graphics;
        AffineTransform odlTramsform = g2.getTransform();
        g2.rotate(rad * Math.PI * 2 / 360, x, y);

        //������
        RoundRectangle2D rbody = new  RoundRectangle2D.Double(
                x-3*size, y-3*size, 6*size, 6*size, 1*size, 1*size);
        DrawUtil.drawShape(g2, Color.ORANGE, rbody);

        // ����
        Rectangle2D rweell = new Rectangle2D.Double(x-5*size, y-5*size, 2*size, 10*size);
        Rectangle2D rweelr = new Rectangle2D.Double(x+3*size, y-5*size, 2*size, 10*size);
        DrawUtil.drawShape(g2, Color.LIGHT_GRAY, rweelr);
        DrawUtil.drawShape(g2, Color.LIGHT_GRAY, rweell);

        int weelState = (int) (System.currentTimeMillis() % 10);
        double y2;
        for(int i = 0; i < 10; i += 2) {
            // y2 = y + size*(weelState/10 + 2*i);
            y2 = y + size*i + weelState/5*size - 3 * size;
            g2.draw(new Line2D.Double(x-5*size, y2, x-3*size, y2));
            g2.draw(new Line2D.Double(x+5*size, y2, x+3*size, y2));
        }

        // �ڹ�
        g2.setColor(Color.DARK_GRAY);
        Rectangle2D rfire = new Rectangle2D.Double(x-0.4*size, y-8*size, 1*size, 8*size);
        g2.fill(rfire);

        // ��̨
        g2.setColor(Color.DARK_GRAY);
        Ellipse2D rfilePlat = new Ellipse2D.Double(x-1.5*size, y-1.5*size, 3*size, 3*size);

        g2.fill(rfilePlat);

        g2.setTransform(odlTramsform); //�ָ����ݵ���ת����
        graphics.setColor(c);
    }
}
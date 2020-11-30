package com.rays.tank.model;

import com.rays.tank.common.Context;
import com.rays.tank.view.Images;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BattleField {
    private BufferedImage image = new BufferedImage(Context.D_WIDTH, Context.D_HEIGTH, BufferedImage.TYPE_INT_RGB);
    private Graphics graphics = image.createGraphics();
    private Map<Integer, Tank> tankMap = new HashMap<>();
    private Map<Long, Tank> bulletMap = new HashMap<>();

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

                Tank tank = parse(line);
                if (tank != null) {
                    if (tag == 1) {
                        tank.setId(id ++);
                        tankMap.put(tank.getId(), tank);
                    } else {
                        tank.setBot(false);
                        tank.setId(0);
                        tankMap.put(0, tank);
                    }
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
        Color c = graphics.getColor();
        graphics.fillRect(0,0, image.getWidth(), image.getHeight());
        for (Tank tank : tankMap.values()) {
            drawTank(tank);
        }
        drawGridLine();
        graphics.setColor(c);
        return image;
    }

    private void drawGridLine() {
        if (Context.debug) {
            graphics.setColor(Color.BLACK);
            for (int i = 0; i < Context.D_WIDTH; i += Context.blockSize) {
                graphics.drawLine(i, 0, i, Context.D_HEIGTH);
            }
            for (int i = 0; i < Context.D_HEIGTH; i += Context.blockSize) {
                graphics.drawLine(0, i, Context.D_WIDTH, i);
            }
        }
    }

    private void drawTank(Tank tank) {
        Graphics2D g2 = (Graphics2D) graphics;
        AffineTransform affineTransform = g2.getTransform();
        g2.rotate(Math.PI * 2 * tank.getDirection() * 90 / 360, tank.getX(), tank.getY());
        int diff = Context.blockSize / 2;
        if (tank.isBot()) {
            graphics.drawImage(
                    Images.imgTankEmyArr[tank.getMoveStatus() % Images.imgTankEmyArr.length],
                    tank.getX() - diff, tank.getY() - diff, Context.blockSize, Context.blockSize, null);
        } else {
            graphics.drawImage(
                    Images.imgTankPlaArr[tank.getMoveStatus() % Images.imgTankPlaArr.length],
                    tank.getX() - diff, tank.getY() - diff, Context.blockSize, Context.blockSize, null);
        }
        g2.setTransform(affineTransform);
    }
}
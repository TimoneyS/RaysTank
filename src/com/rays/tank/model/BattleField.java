package com.rays.tank.model;

import com.rays.tank.common.Const;
import com.rays.tank.view.Images;

import java.awt.*;
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

                Tank tank = parse(line);
                if (tank != null) {
                    if (tag == 1) {
                        tank.setId(id ++);
                        map.put(tank.getId(), tank);
                    } else {
                        tank.setBot(false);
                        tank.setId(0);
                        map.put(0, tank);
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
        graphics.fillRect(0,0, image.getWidth(), image.getHeight());
        for (Tank tank : map.values()) {
            drawTank(tank);
        }
        return image;
    }

    private void drawTank(Tank tank) {
        tank.move();
        if (tank.isBot()) {
            graphics.drawImage(
                    Images.imgTankEmyArr[tank.getMoveStatus() % 6],
                    tank.getX(), tank.getY(), Const.blockSize, Const.blockSize, null);
        } else {
            graphics.drawImage(
                    Images.imgTankPlaArr[tank.getMoveStatus() % 6],
                    tank.getX(), tank.getY(), Const.blockSize, Const.blockSize, null);
        }
    }
}
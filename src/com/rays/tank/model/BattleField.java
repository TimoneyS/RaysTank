package com.rays.tank.model;

import com.rays.tank.common.Const;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BattleField {
    private BufferedImage image = new BufferedImage(Const.D_WIDTH, Const.D_HEIGTH, BufferedImage.TYPE_INT_RGB);
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

    public Image getImage() {
        return image;
    }
}
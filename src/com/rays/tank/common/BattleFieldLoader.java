package com.rays.tank.common;

import com.rays.tank.model.BattleField;
import com.rays.tank.model.Boom;
import com.rays.tank.model.Bullet;
import com.rays.tank.model.Tank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BattleFieldLoader {
    public static void parseBattleField(InputStream inputStream, BattleField battleField) {
        try(BufferedReader bis = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int tag = 0;
            Context.sequence.set(2);
            while ((line = bis.readLine()) != null) {
                if (line.contains("[BOT]")) {
                    tag = 1;
                    continue;
                } else if (line.contains("[PLA]")) {
                    tag = 2;
                    continue;
                } else if (line.contains("[BULLET]")) {
                    tag = 3;
                    continue;
                } else if (line.contains("[BOOM]")) {
                    tag = 4;
                    continue;
                }
                if (tag == 1 || tag == 2) {
                    Tank tank = parseTank(line);
                    if (tank != null) {
                        if (tag == 1) {
                            tank.setId(Context.nextSeq());
                            battleField.getTankMap().put(tank.getId(), tank);
                        } else {
                            tank.setBot(false);
                            tank.setId(0);
                            battleField.getTankMap().put(0, tank);
                        }
                    }
                } else if (tag == 3) {
                    Bullet bullet = parseBullet(line);
                    if (bullet != null) {
                        bullet.setId(Context.nextSeq());
                        battleField.getBulletMap().put(bullet.getId(), bullet);
                    }
                } else if (tag == 4) {
                    Boom boom = parseBoom(line);
                    if (boom != null) {
                        boom.setId(Context.nextSeq());
                        battleField.getBoomMap().put(boom.getId(), boom);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Boom parseBoom(String line) {
        String[] arr = lineToArray(line);
        if (arr == null) {
            return null;
        }
        try {
            Boom boom = new Boom();
            boom.setX(Integer.parseInt(arr[0].trim()));
            boom.setY(Integer.parseInt(arr[1].trim()));
            boom.setDirection(Integer.parseInt(arr[2].trim()));
            boom.setStatus(Integer.parseInt(arr[3].trim()));
            return boom;
        } catch (Exception e) {
            return null;
        }
    }

    private static Bullet parseBullet(String line) {
        String[] arr = lineToArray(line);
        if (arr == null) {
            return null;
        }
        Bullet bullet = new Bullet();
        bullet.setX(Integer.parseInt(arr[0].trim()));
        bullet.setY(Integer.parseInt(arr[1].trim()));
        bullet.setDirection(Integer.parseInt(arr[2].trim()));
        return bullet;
    }

    private static Tank parseTank(String line) {
        String[] arr = lineToArray(line);
        if (arr == null || arr.length == 0) {
            return null;
        }
        try {
            Tank t = new Tank();
            t.setX(Integer.parseInt(arr[0].trim()));
            t.setY(Integer.parseInt(arr[1].trim()));
            t.setDirection(Integer.parseInt(arr[2].trim()));
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    private static String[] lineToArray(String line) {
        int start = line.indexOf('[');
        int end = line.indexOf(']');
        if (start >= end) {
            return null;
        }
        String data = line.substring(start + 1, end);
        return data.trim().split(",");
    }
}

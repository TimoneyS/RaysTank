package com.rays.tank.common;

import com.rays.tank.model.*;

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
                } else if (line.contains("[MAP]")) {
                    tag = 5;
                }
                if (tag == 1 || tag == 2) {
                    parseTank(battleField, line, tag);
                } else if (tag == 3) {
                    Bullet bullet = parseBullet(line);
                    if (bullet != null) {
                        battleField.getBulletMap().put(bullet.getId(), bullet);
                    }
                } else if (tag == 4) {
                    Boom boom = parseBoom(line);
                    if (boom != null) {
                        boom.setId(Context.nextSeq());
                        battleField.getBoomMap().put(boom.getId(), boom);
                    }
                } else if (tag == 5) {
                    for (int row = 0;row < 14; row ++) {
                        String tempLine = bis.readLine().trim();
                        for (int col = 0; col < 21; col ++) {
                            Grid g = new Grid();
                            g.setType(tempLine.charAt(col) - '0');
                            battleField.getGround()[row][col] = g;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseTank(BattleField battleField, String line, int tag) {
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
    }

    private static Boom parseBoom(String line) {
        String[] arr = lineToArray(line);
        if (arr == null) {
            return null;
        }
        try {
            Boom boom = new Boom(
                    Context.nextSeq(),
                    Integer.parseInt(arr[0].trim()),
                    Integer.parseInt(arr[1].trim()),
                    Integer.parseInt(arr[2].trim()));
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
        return new Bullet(
                Context.nextSeq(),
                Integer.parseInt(arr[0].trim()),
                Integer.parseInt(arr[1].trim()),
                Integer.parseInt(arr[2].trim()));
    }

    private static Tank parseTank(String line) {
        String[] arr = lineToArray(line);
        if (arr == null || arr.length == 0) {
            return null;
        }
        try {
            return new Tank(
                    Integer.parseInt(arr[0].trim()),
                    Integer.parseInt(arr[1].trim()),
                    Integer.parseInt(arr[2].trim()));
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

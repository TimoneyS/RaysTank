package com.rays.tank.model;

import com.rays.tank.common.Context;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BattleField {
    private int row;
    private int column;
    public int enemyLeft = Context.MAX_ENEMY;
    private int[][] fields;
    public XY playerStarter;
    public List<XY> enemyStarter = new ArrayList<>();
    private Map<Integer, Tank> tankMap = new HashMap<>();
    private Map<Integer, Bullet> bulletMap = new HashMap<>();
    private Map<Integer, Boom>   boomMap = new HashMap<>();
    private Queue<Bullet> bulletCache = new ArrayDeque<>();
    private Queue<Tank> tankCache = new ArrayDeque<>();
    private Queue<Boom> boomCache = new ArrayDeque<>();

    public BattleField(int row, int column) {
        this.row = row;
        this.column = column;
        this.fields = new int[row][column];
    }

    public int[][] getFields() {
        return fields;
    }

    public Map<Integer, Tank> getTankMap() {
        return tankMap;
    }

    public Map<Integer, Bullet> getBulletMap() {
        return bulletMap;
    }

    public Map<Integer, Boom> getBoomMap() {
        return boomMap;
    }

    public void addBullet(Bullet bullet) {
        bulletCache.offer(bullet);
    }

    public void addTank(Tank tank) {
        tankCache.offer(tank);
    }

    public void addBoom(Boom boom) {
        boomCache.offer(boom);
    }

    public void clearNoActives() {
        tankMap.entrySet().removeIf(e -> e.getValue().isNotActive());
        bulletMap.entrySet().removeIf(e -> e.getValue().isNotActive());
        boomMap.entrySet().removeIf(e -> e.getValue().isNotActive());
    }

    public void flush() {
        if (bulletCache.size() > 0) {
            bulletCache.forEach(bullet -> bulletMap.put(bullet.getId(), bullet));
            bulletCache.clear();
        }
        if (tankCache.size() > 0) {
            tankCache.forEach(tank -> tankMap.put(tank.getId(), tank));
            tankCache.clear();
        }
        if (boomCache.size() > 0) {
            boomCache.forEach(boom -> boomMap.put(boom.getId(), boom));
            boomCache.clear();
        }
    }

    public int getField(XY xy) {
        return getField(xy.getX(), xy.getY());
    }

    public int getField(int x, int y) {
        if (x < 0
                || x >= getFields().length
                || y < 0
                || y >= fields[0].length) {
            return  -1;
        }
        return fields[x][y];
    }

    public void incGround(XY xy, int i) {
        fields[xy.getX()][xy.getY()] += i;
    }

    public void setField(XY xy, int i) {
        setField(xy.getX(), xy.getY(), i);
    }

    public void setField(int row, int col, int i) {
        fields[row][col] = i;
    }

    public void boom() {
        for (int i = 10; i < Context.D_WIDTH;i += 20) {
            addBullet(new Bullet(-1, -1, i, 10, 2));
            addBullet(new Bullet(-1, -1, i, Context.D_HEIGHT - 10, 0));
        }
    }

    public void setPlayerStarter(XY xy) {
        playerStarter = xy;
    }

    public XY getPlayerStarter() {
        return playerStarter;
    }

    public boolean isGameOver() {
        return enemyLeft <= 0 && tankMap.size() <= 1;
    }

    public Tank getTank(int id) {
        return getTankMap().get(id);
    }
}
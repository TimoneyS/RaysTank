package com.rays.tank.model;

import com.rays.tank.common.BattleFieldLoader;
import com.rays.tank.common.Context;
import com.rays.tank.controller.TankControl;

import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class BattleField {
    private Map<Integer, Tank> tankMap = new HashMap<>();
    private Map<Integer, Bullet> bulletMap = new HashMap<>();
    private Map<Integer, Boom>   boomMap = new HashMap<>();
    private Queue<Bullet> bulletCache = new ArrayDeque<>();
    private Queue<Tank> tankCache = new ArrayDeque<>();
    private Queue<Boom> boomCache = new ArrayDeque<>();

    public BattleField(InputStream inputStream) {
        BattleFieldLoader.parseBattleField(inputStream, this);
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

    public void update() {

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

    public void clearNoActived() {
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
}
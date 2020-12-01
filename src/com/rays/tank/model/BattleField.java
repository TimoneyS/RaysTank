package com.rays.tank.model;

import com.rays.tank.common.BattleFieldLoader;
import com.rays.tank.common.Context;

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
        flush();

        tankMap.values().forEach(Tank::move);
        bulletMap.values().forEach(Bullet::move);
        boomMap.values().forEach(Boom::update);

        bulletMap.values().forEach(bullet -> {
            tankMap.values().forEach(tank -> {
                if (tank.getId() > 1 && (Math.abs(tank.x - bullet.x) + Math.abs(tank.y - bullet.y) < 30)) {
                    Boom boom = new Boom();
                    boom.setId(Context.nextSeq());
                    boom.setX(tank.getX());
                    boom.setY(tank.getY());
                    boomMap.put(boom.getId(), boom);
//                    tank.destroy();
                    bullet.destroy();
                }
            });
        });

        tankMap.entrySet().removeIf(e -> e.getValue().isNotActive());
        bulletMap.values().stream().filter(Bullet::isNotActive).forEach(bullet -> {
            Boom boom = new Boom();
            boom.setId(Context.nextSeq());
            boom.setX(bullet.getX());
            boom.setY(bullet.getY());
            boomMap.put(boom.getId(), boom);
        });

        bulletMap.entrySet().removeIf(e -> e.getValue().isNotActive());
        boomMap.entrySet().removeIf(e -> e.getValue().isNotActive());
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
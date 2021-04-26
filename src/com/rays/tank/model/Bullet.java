package com.rays.tank.model;

import com.rays.tank.common.Context;

public class Bullet extends BaseObject {
    private int speed = Context.bulletSpeed;

    public Bullet(int id, int x, int y, int direction) {
        super(id, x, y, direction);
        setLife(1);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

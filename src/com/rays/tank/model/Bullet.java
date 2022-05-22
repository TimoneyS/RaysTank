package com.rays.tank.model;

import com.rays.tank.common.Context;

public class Bullet extends BaseObject {
    private int speed = Context.bulletSpeed;
    private int parentId = -1;
    private int team = -1;

    public Bullet(int parentId, int team, int x, int y, int direction) {
        super(Context.nextSeq(), x, y, direction);
        this.parentId = parentId;
        this.team = team;
        setLife(1);
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }
}

package com.rays.tank.model;

import com.rays.tank.common.XYUtil;

public class BaseObject {
    protected int id;
    protected XY xy;
    protected int direction;
    protected int radius;
    protected boolean active = true;

    public BaseObject(int x, int y, int direction) {
        this.xy= XYUtil.create(x, y);
        this.direction = direction;
    }

    public BaseObject(int id, int x, int y, int direction) {
        this(x, y, direction);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public XY getXy() {
        return xy;
    }

    public void setXy(XY xy) {
        this.xy = xy;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isNotActive() {
        return !active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void destroy() {
        setActive(false);
    }
}

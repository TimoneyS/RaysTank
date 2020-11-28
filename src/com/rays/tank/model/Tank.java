package com.rays.tank.model;


import java.awt.*;

public class Tank extends BaseObject {

    private int direction;
    private int moveStatus;
    private Color color;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getMoveStatus() {
        return moveStatus;
    }

    public void setMoveStatus(int moveStatus) {
        this.moveStatus = moveStatus;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void move() {
        moveStatus ++;
        moveStatus %= 360;
    }
}

package com.rays.tank.model;


import java.awt.*;

public class Tank extends BaseObject {
    private int direction;
    private int moveStatus;
    private boolean isBot = true;
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

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }

    public void move() {
        moveStatus ++;
        moveStatus %= 360;
    }
}

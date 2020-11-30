package com.rays.tank.model;


import com.rays.tank.common.Context;

import java.awt.*;

public class Tank extends BaseObject {
    private int moveStatus;
    private int speed = 5;
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
        int[] dir = Context.DIRS[direction];
        int newX = this.x + dir[0] * speed;
        int newY = this.y + dir[1] * speed;

        if (newX >= 0 && newX <= Context.D_WIDTH && newY >= 0 && newY <= Context.D_HEIGTH) {
            this.x = newX;
            this.y = newY;
            System.out.println(newX + ", " + newY);
        } else {
            direction = (direction + 2) % 4;
        }

        moveStatus ++;
        moveStatus &= 1023;
    }
}

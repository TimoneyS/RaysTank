package com.rays.tank.model;

import com.rays.tank.common.Context;

public class Tank extends BaseObject {
    private int moveStatus;
    private int speed = 0;
    private boolean isBot = true;
    private long nextShootTime = 0;

    public Tank(int x, int y, int direction) {
        super(x, y, direction);
    }

    public Tank(int id, int x, int y, int direction) {
        super(id, x, y, direction);
    }

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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isBot() {
        return isBot;
    }

    public void setBot(boolean bot) {
        isBot = bot;
    }

    public long getNextShootTime() {
        return nextShootTime;
    }

    public void setNextShootTime(long nextShootTime) {
        this.nextShootTime = nextShootTime;
    }

    public void move() {
        if (speed <= 0) {
            return;
        }
        int[] dir = Context.DIRS[direction];
        int newX = this.x + dir[0] * speed;
        int newY = this.y + dir[1] * speed;
        if (newX >= 0 && newX <= Context.D_WIDTH && newY >= 0 && newY <= Context.D_HEIGTH) {
            this.x = newX;
            this.y = newY;
        }
        moveStatus ++;
        moveStatus &= 1023;
    }
}

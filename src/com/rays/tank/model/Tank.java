package com.rays.tank.model;

public class Tank extends BaseObject {
    private int moveStatus;
    private int speed = 0;
    private boolean isBot = true;
    private long nextShootTime = 0;

    public Tank(int x, int y, int direction) {
        super(x, y, direction);
        setLife(1);
    }

    public Tank(XY playerStarter, int direction) {
        this(playerStarter.getX(), playerStarter.getY(), 0);
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
}

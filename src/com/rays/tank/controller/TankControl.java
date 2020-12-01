package com.rays.tank.controller;

import com.rays.tank.common.Context;
import com.rays.tank.model.Bullet;
import com.rays.tank.model.Tank;

import java.awt.event.KeyEvent;

public class TankControl {
    public static void move(Tank tank) {
        if (tank.getSpeed() <= 0) {
            return;
        }
        int[] dir = Context.DIRS[tank.getDirection()];
        int newX = tank.getX() + dir[0] * tank.getSpeed();
        int newY = tank.getY() + dir[1] * tank.getSpeed();
        if (newX >= 0 && newX <= Context.D_WIDTH && newY >= 0 && newY <= Context.D_HEIGTH) {
            tank.setX(newX);
            tank.setY(newY);
        }
        tank.setMoveStatus((tank.getMoveStatus() + 1) & 1023);
    }

    public static void shoot(Tank tank) {
        if (System.currentTimeMillis() >= tank.getNextShootTime()) {
            int[] dir = Context.DIRS[tank.getDirection()];
            int newX = tank.getX() + dir[0] * Context.blockSize / 2;
            int newY = tank.getY() + dir[1] * Context.blockSize / 2;
            Bullet bullet = new Bullet(Context.nextSeq(), newX, newY, tank.getDirection());
            Context.battleField.addBullet(bullet);
            tank.setNextShootTime(System.currentTimeMillis() + 500);
        }
    }

    public static void handleKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_J) {
            TankControl.shoot(Context.plaTank);
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            Context.plaTank.setDirection(Context.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            Context.plaTank.setDirection(Context.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            Context.plaTank.setDirection(Context.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            Context.plaTank.setDirection(Context.RIGHT);
        } else {
            return;
        }
        Context.plaTank.setSpeed(5);
    }

    public static void handleKeyReleased(KeyEvent e) {
        int releasedDir;
        if (e.getKeyCode() == KeyEvent.VK_W) {
            releasedDir = Context.UP;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            releasedDir = Context.DOWN;
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            releasedDir = Context.LEFT;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            releasedDir = Context.RIGHT;
        } else {
            return;
        }
        if (releasedDir == Context.plaTank.getDirection()) {
            Context.plaTank.setSpeed(0);
        }
    }
}
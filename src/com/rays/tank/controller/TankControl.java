package com.rays.tank.controller;

import com.rays.tank.common.Context;
import com.rays.tank.model.Bullet;
import com.rays.tank.model.Tank;

import java.awt.event.KeyEvent;
import java.util.Random;

public class TankControl {
    public static void move(Tank tank) {
        if (tank.getSpeed() <= 0) {
            return;
        }
        int[] dir = Context.DIRS[tank.getDirection()];
        int newX = tank.getX() + dir[0] * tank.getSpeed();
        int newY = tank.getY() + dir[1] * tank.getSpeed();
        int newHeadX = tank.getX() + dir[0] * Context.blockSize / 2;
        int newHeadY = tank.getY() + dir[1] * Context.blockSize / 2;
        if (newHeadX >= 0 && newHeadX < Context.D_WIDTH && newHeadY >= 0 && newHeadY < Context.D_HEIGHT) {
            int[] rc = Context.toRowAndCol(newHeadX, newHeadY);
            if (Context.battleField.getGround()[rc[0]][rc[1]] == 0) {
                tank.setX(newX);
                tank.setY(newY);
            }
        } else {
            if (tank.getId() > 0) {
                int dirLength = Context.DIRS.length;
                tank.setDirection((tank.getDirection() + 1 + dirLength) % dirLength);
            }
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
        if (e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_SPACE) {
            TankControl.shoot(Context.plaTank);
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            Context.plaTank.setDirection(Context.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            Context.plaTank.setDirection(Context.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            Context.plaTank.setDirection(Context.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Context.plaTank.setDirection(Context.RIGHT);
        } else {
            return;
        }
        Context.plaTank.setSpeed(Context.manMoveSpeed);
    }

    public static void handleKeyReleased(KeyEvent e) {
        int releasedDir;
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            releasedDir = Context.UP;
        } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            releasedDir = Context.DOWN;
        } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            releasedDir = Context.LEFT;
        } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            releasedDir = Context.RIGHT;
        } else {
            return;
        }
        if (releasedDir == Context.plaTank.getDirection()) {
            Context.plaTank.setSpeed(0);
        }
    }
}

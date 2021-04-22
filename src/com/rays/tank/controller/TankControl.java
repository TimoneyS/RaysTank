package com.rays.tank.controller;

import com.rays.tank.common.Context;
import com.rays.tank.common.XYBuilder;
import com.rays.tank.common.XYUtil;
import com.rays.tank.model.Bullet;
import com.rays.tank.model.Tank;
import com.rays.tank.model.XY;

import java.awt.event.KeyEvent;

public class TankControl {
    public static void move(Tank tank) {
        if (tank.getSpeed() <= 0) {
            return;
        }
        int[] dir = Context.DIRS[tank.getDirection()];

        XY newXY = XYBuilder.of(dir).multiply(tank.getSpeed()).plus(tank.getXy()).get();
        XY headXY = XYBuilder.of(dir).multiply(Context.blockSize / 2).plus(tank.getXy()).get();
        XY rowAndCol = Context.toRowAndCol(headXY);
        if (newXY.getX() > 0 && newXY.getY() > 0
                && Context.battleField.getGround(rowAndCol) == 0
                && willNotCrashWithOtherTanks(tank.getId(), newXY)
        ) {
            tank.setXy(newXY);
        } else {
            if(tank.getId() > 0) {
                tank.setDirection((int) (Math.random() * Context.DIRS.length));
            }
        }
        tank.setMoveStatus((tank.getMoveStatus() + 1) & 1023);
    }

    private static boolean willNotCrashWithOtherTanks(int id, XY newXY) {
    return Context.battleField.getTankMap().values().stream()
                .noneMatch(otherTank -> otherTank.getId() != id
                        && (XYUtil.maxDist(otherTank.getXy(), newXY) < Context.blockSize));
    }

    public static void shoot(Tank tank) {
        if (System.currentTimeMillis() >= tank.getNextShootTime()) {
            addBullet(tank, Context.DIRS[tank.getDirection()]);
             tank.setNextShootTime(System.currentTimeMillis() + 100);
        }
    }

    private static void addBullet(Tank tank, int[] dir) {
        XY newXY = XYBuilder.of(dir).multiply(Context.blockSize / 2).plus(tank.getXy()).get();
        Bullet bullet = new Bullet(Context.nextSeq(), newXY.getX(), newXY.getY(), tank.getDirection());
        Context.battleField.addBullet(bullet);
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

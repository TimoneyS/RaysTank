package com.rays.tank.controller;

import com.rays.tank.common.Context;
import com.rays.tank.common.Dirs;
import com.rays.tank.model.Bullet;
import com.rays.tank.model.Tank;
import com.rays.tank.model.XY;

import java.awt.event.KeyEvent;

public class TankControl {
    public static void move(Tank tank) {
        if (tank.getSpeed() <= 0) {
            return;
        }
        int[] dir = Dirs.get(tank.getDirection());

        XY newXY = XY.of(dir).multiply(tank.getSpeed()).plus(tank.getXy());
        XY headXY = XY.of(dir).multiply(Context.blockSize / 2).plus(tank.getXy());
        XY rowAndCol = Context.toRowAndCol(headXY);
        if (headXY.getX() > 0 && headXY.getY() > 0
                && Context.battleField.getField(rowAndCol) == 0
//                && willNotCrashWithOtherTanks(tank.getId(), newXY)
        ) {
            tank.setXy(newXY);
        }
        if ((int)(Math.random() * 100) <= 1 && tank.getId() != Context.PLAYER_ID) {
            TankControl.shoot(tank);
            tank.setDirection((int) (Math.random() * Dirs.size()));
        }
        tank.setMoveStatus((tank.getMoveStatus() + 1) & 1023);
        if ((int)(Math.random() * 100) <= 1 && tank.getId() != Context.PLAYER_ID) {
            TankControl.shoot(tank);
        }
    }

    private static boolean willNotCrashWithOtherTanks(int id, XY newXY) {
        return Context.battleField.getTankMap().values().stream()
                .noneMatch(otherTank -> otherTank.getId() != id
                        && (otherTank.getXy().maxDist(newXY) < Context.blockSize));
    }

    public static void shoot(Tank tank) {
        if (System.currentTimeMillis() >= tank.getNextShootTime()) {
             addBullet(tank, Dirs.get(tank.getDirection()));
             tank.setNextShootTime(System.currentTimeMillis() + 100);
        }
    }

    private static void addBullet(Tank tank, int[] dir) {
        XY newXY = XY.of(dir).multiply(Context.blockSize / 2).plus(tank.getXy());
        Bullet bullet = new Bullet(tank.getId(), tank.getTeam(), newXY.getX(), newXY.getY(), tank.getDirection());
        Context.battleField.addBullet(bullet);
    }

    public static void handleKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_SPACE) {
            TankControl.shoot(Context.getPlaTank());
            return;
        }
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            Context.getPlaTank().setDirection(Dirs.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            Context.getPlaTank().setDirection(Dirs.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            Context.getPlaTank().setDirection(Dirs.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Context.getPlaTank().setDirection(Dirs.RIGHT);
        } else {
            return;
        }
        Context.getPlaTank().setSpeed(Context.manMoveSpeed);
    }

    public static void handleKeyReleased(KeyEvent e) {
        int releasedDir;
        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            releasedDir = Dirs.UP;
        } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            releasedDir = Dirs.DOWN;
        } else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            releasedDir = Dirs.LEFT;
        } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            releasedDir = Dirs.RIGHT;
        } else {
            return;
        }
        if (releasedDir == Context.getPlaTank().getDirection()) {
            Context.getPlaTank().setSpeed(0);
        }
    }
}

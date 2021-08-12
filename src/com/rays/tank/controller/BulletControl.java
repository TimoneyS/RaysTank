package com.rays.tank.controller;

import com.rays.tank.common.Context;
import com.rays.tank.common.Dirs;
import com.rays.tank.model.Bullet;
import com.rays.tank.model.XY;

public class BulletControl {
    public static void move(Bullet bullet) {
        if (bullet.getSpeed() <= 0) {
            return;
        }
        int[] dir = Dirs.get(bullet.getDirection());
        XY newXY = XY.of(dir).multiply(bullet.getSpeed()).plus(bullet.getXy());
        XY rowAndCol = Context.toRowAndCol(newXY);
        if (newXY.noMoreThan(0)
                || newXY.getX() > Context.D_WIDTH
                || newXY.getY() > Context.D_HEIGHT) {
            bullet.setDirection((int) (Math.random() * Dirs.size()));
            bullet.growUp();
        } else if (Context.battleField.getField(rowAndCol) > 0) {
            Context.battleField.incGround(rowAndCol, 1);
            if (Context.battleField.getField(rowAndCol) > 3) {
                Context.battleField.setField(rowAndCol, 0);
            }
            bullet.setDirection((int) (Math.random() * Dirs.size()));
            bullet.growUp();
        } else {
            bullet.setXy(newXY);
        }
    }
}

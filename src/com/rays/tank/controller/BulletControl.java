package com.rays.tank.controller;

import com.rays.tank.common.Context;
import com.rays.tank.common.XYBuilder;
import com.rays.tank.common.XYUtil;
import com.rays.tank.model.Bullet;
import com.rays.tank.model.XY;

public class BulletControl {
    public static void move(Bullet bullet) {
        if (bullet.getSpeed() <= 0) {
            return;
        }
        int[] dir = Context.DIRS[bullet.getDirection()];
        XY newXY = XYBuilder.of(dir).multiply(bullet.getSpeed()).plus(bullet.getXy()).get();
        XY rowAndCol = Context.toRowAndCol(newXY);
        if (XYUtil.noMoreThen(newXY, 0)
                || newXY.getX() > Context.D_WIDTH
                || newXY.getY() > Context.D_HEIGHT) {
            bullet.destroy();
        } else if (Context.battleField.getGround(rowAndCol) > 0) {
            Context.battleField.incGround(rowAndCol, 1);
            if (Context.battleField.getGround(rowAndCol) > 3) {
                Context.battleField.setGround(rowAndCol, 0);
            }
            if (Context.battleField.getBulletMap().size() > 10) {
                bullet.destroy();
            } else {
                bullet.setDirection((int) (Math.random() * Context.DIRS.length));
            }
        } else {
            bullet.setXy(newXY);
        }
    }
}

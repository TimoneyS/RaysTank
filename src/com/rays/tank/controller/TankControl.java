package com.rays.tank.controller;

import com.rays.tank.common.Context;
import com.rays.tank.model.Bullet;
import com.rays.tank.model.Tank;

public class TankControl {
    public static void shoot(Tank tank) {
        if (System.currentTimeMillis() >= tank.getNextShootTime()) {
            int[] dir = Context.DIRS[tank.getDirection()];
            int newX = tank.getX() + dir[0] * Context.blockSize / 2;
            int newY = tank.getY() + dir[1] * Context.blockSize / 2;
            Bullet bullet = new Bullet(Context.nextSeq(), newX, newY, tank.getDirection());
            Context.battleField.addBullet(bullet);
        }
    }
}

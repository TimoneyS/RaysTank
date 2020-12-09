package com.rays.tank.controller;

import com.rays.tank.common.Context;
import com.rays.tank.model.Bullet;

public class BulletControl {
    public static void move(Bullet bullet) {
        if (bullet.getSpeed() <= 0) {
            return;
        }
        int[] dir = Context.DIRS[bullet.getDirection()];
        int newX = bullet.getX() + dir[0] * bullet.getSpeed();
        int newY = bullet.getY() + dir[1] * bullet.getSpeed();
        int[] rc = Context.toRowAndCol(newX, newY);

        if (newX < 0 || newX > Context.D_WIDTH || newY < 0 || newY > Context.D_HEIGTH) {
            bullet.destroy();
        } else if (Context.battleField.getGround()[rc[0]][rc[1]] > 0) {
            Context.battleField.getGround()[rc[0]][rc[1]] = Context.battleField.getGround()[rc[0]][rc[1]] + 1;
            if (Context.battleField.getGround()[rc[0]][rc[1]] > 3) {
                Context.battleField.getGround()[rc[0]][rc[1]]= 0;
            }
            bullet.destroy();
        } else {
            bullet.setX(newX);
            bullet.setY(newY);
        }
    }
}

package com.rays.tank.common;

import com.rays.tank.model.Bullet;

public class BulletControl {
    public static void move(Bullet bullet) {
        if (bullet.getSpeed() <= 0) {
            return;
        }
        int[] dir = Context.DIRS[bullet.getDirection()];
        int newX = bullet.getX() + dir[0] * bullet.getSpeed();
        int newY = bullet.getY() + dir[1] * bullet.getSpeed();

        if (newX >= 0 && newX <= Context.D_WIDTH && newY >= 0 && newY <= Context.D_HEIGTH) {
            bullet.setX(newX);
            bullet.setY(newY);
        } else {
            bullet.destroy();
        }
    }
}

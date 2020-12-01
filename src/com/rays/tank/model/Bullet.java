package com.rays.tank.model;

import com.rays.tank.common.Context;

public class Bullet extends BaseObject {
    private int speed = Context.bulletSpeed;

    public Bullet(int id, int x, int y, int direction) {
        super(id, x, y, direction);
    }

    public void move() {
        if (speed <= 0) {
            return;
        }
        int[] dir = Context.DIRS[direction];
        int newX = this.x + dir[0] * speed;
        int newY = this.y + dir[1] * speed;

        if (newX >= 0 && newX <= Context.D_WIDTH && newY >= 0 && newY <= Context.D_HEIGTH) {
            this.x = newX;
            this.y = newY;
        } else {
            destroy();
        }
    }
}

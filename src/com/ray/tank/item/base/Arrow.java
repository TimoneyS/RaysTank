package com.ray.tank.item.base;

/**
 *            0, 1
 *             W
 *     -1,-1   |    1,1
 *        \    |    /
 *          \  |  /
 *            \|/
 *-1, 0A-------|-------D 1,0
 *            /|\
 *          /  |  \
 *        /    |    \
 *     -1,-1   |    1,-1
 *             S
 *            0,-1
 * @author rays1
 *
 */
public class Arrow {

    public static Arrow W = new Arrow(0, 1);
    public static Arrow S = new Arrow(0, -1);
    public static Arrow A = new Arrow(-1, 0);
    public static Arrow D = new Arrow(1, 0);
    public static Arrow ZEOR = new Arrow(0, 0);
    
    private int         x;
    private int         y;
    private boolean canChange;

    public Arrow() {
        canChange = true;
        x = 0;
        y = 0;
    }

    private Arrow(int x, int y) {
        canChange = false;
        this.x = x;
        this.y = y;
    }

    public Arrow plus(Arrow arrow) {
        if (canChange) {
            x += arrow.x;
            y += arrow.y;
            if (x != 0) {
                x = x > 0 ? 1 : -1;
            }
            if (y != 0) {
                y = y > 0 ? 1 : -1;
            }
            return this;
        } else {
            int tx = x + arrow.x;
            int ty = y + arrow.y;
            if (tx != 0) {
                tx = tx > 0 ? 1 : -1;
            }
            if (ty != 0) {
                ty = ty > 0 ? 1 : -1;
            }
            return new Arrow(tx, ty);
        }
       
    }
    
    /**
     * 向量变为角度
     * @return
     */
    public double toDrgree() {
        return Math.toDegrees(toRadians());
        
    }
    
    /**
     * 向量变为弧度
     * @return
     */
    public double toRadians() {
        return Math.atan2(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    @Override
    public String toString() {
        String dir = "";
        if (x == 0) {
            if (y > 0) {
                dir = "↑";
            } else if (y < 0){
                dir = "↓";
            } else {
                dir = "O";
            }
        } else if (x > 0){
            if (y > 0) {
                dir = "↗";
            } else if (y < 0){
                dir = "↘";
            } else {
                dir = "→";
            }
        } else {
            if (y > 0) {
                dir = "↖";
            } else if (y < 0){
                dir = "↙";
            } else {
                dir = "←";
            }
        }
        
       return String.format("[%d, %d] %s", x, y, dir);
        
    }
    
    @Override
    public int hashCode() {
        return x*10 + y;
    }
    
    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }
    
}

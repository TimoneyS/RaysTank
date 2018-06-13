package com.ray.tank.item.base.move;

import com.ray.tank.item.base.Location;

/**
 * 物体移动辅助类
 * @author rays1
 *
 */
public abstract class MoveSupport implements Moveable {
    
    protected double      moveSpeed;    // 移动速度
    
    public void move() {
        
        double direction = getDirection();
        Location l = getLocation();
        
        double tx = l.X() + Math.sin(direction)*moveSpeed;         // 横坐标变化
        double ty = l.Y() - Math.cos(direction)*moveSpeed;         // 纵坐标变化
        
        if (getBattfield().isInBounds(tx, ty)) {
            l.set(tx, ty);
        } else {
            doOutBound();   
        }
        
    }
    
    @Override
    public void doOutBound() {
        // 默认不做任何事
    }
    
    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
    
}

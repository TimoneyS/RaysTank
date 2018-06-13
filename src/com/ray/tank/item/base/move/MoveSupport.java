package com.ray.tank.item.base.move;

import com.ray.tank.item.base.Location;

public abstract class MoveSupport implements Moveable {
    
    public void move() {
        
        double speed = getMoveSpeed();
        double direction = getDirection();
        Location l = getLocation();
        
        double tx = l.X() + Math.sin(direction)*speed;         // 横坐标变化
        double ty = l.Y() - Math.cos(direction)*speed;         // 纵坐标变化
        
        if (getBattfield().isInBounds(tx, ty)) {
            l.set(tx, ty);
        } else {
            outBoundHandle();   
        }
        
    }
    
    @Override
    public void outBoundHandle() {
        // 默认不错任何事
    }
    
}

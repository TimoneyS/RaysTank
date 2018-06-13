package com.ray.tank.item.base.move;

import com.ray.tank.item.base.Location;
import com.ray.tank.item.other.BattleField;

public interface Moveable {
    
    /**
     * 移动越界处理
     */
    public abstract void doOutBound();
    
    /**
     * 方向
     * @return
     */
    public abstract double getDirection();

    /**
     * 移动速度
     * @return
     */
    public abstract void setMoveSpeed(double speed);
    
    /**
     * 坐标
     * @return
     */
    public Location getLocation();
   
    /**
     * 战场边界
     * @return
     */
    public abstract BattleField getBattfield();
    
    
    
}

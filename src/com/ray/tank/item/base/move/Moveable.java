package com.ray.tank.item.base.move;

import com.ray.tank.item.base.Location;
import com.ray.tank.item.other.BattleField;

public interface Moveable {
    
    /**
     * ����
     * @return
     */
    public abstract double getDirection();
    
    public abstract void doOutBound();
    
    /**
     * �ƶ��ٶ�
     * @return
     */
    public abstract double getMoveSpeed();
    
    /**
     * ����
     * @return
     */
    public Location getLocation();
   
    /**
     * ս���߽�
     * @return
     */
    public abstract BattleField getBattfield();
    
}

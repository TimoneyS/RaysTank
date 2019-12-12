package com.rays.tank.item.base.move;

import com.rays.tank.item.base.Location;

public interface Moveable {
    
    /**
     * �ƶ�Խ�紦��
     */
    public abstract void doOutBound();
    
    /**
     * ����
     * @return
     */
    public abstract double getDirection();

    /**
     * �ƶ��ٶ�
     * @return
     */
    public abstract void setMoveSpeed(double speed);
    
    /**
     * ����
     * @return
     */
    public Location getLocation();
   
}

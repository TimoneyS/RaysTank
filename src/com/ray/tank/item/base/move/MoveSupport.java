package com.ray.tank.item.base.move;

import com.ray.tank.common.Global;
import com.ray.tank.item.base.Location;

/**
 * �����ƶ�������
 * @author rays1
 *
 */
public abstract class MoveSupport implements Moveable {
    
    protected double      moveSpeed;    // �ƶ��ٶ�
    
    public void move() {
        
        double direction = getDirection();
        Location l = getLocation();
        
        double tx = l.X() + Math.sin(direction)*moveSpeed;         // ������仯
        double ty = l.Y() - Math.cos(direction)*moveSpeed;         // ������仯
        
        if (Global.battleField.isInBounds(tx, ty)) {
            l.set(tx, ty);
        } else {
            doOutBound();   
        }
        
    }
    
    @Override
    public void doOutBound() {
        // Ĭ�ϲ����κ���
    }
    
    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
    
}

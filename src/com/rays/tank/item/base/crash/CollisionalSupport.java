package com.rays.tank.item.base.crash;

import java.util.Iterator;
import java.util.List;

/**
 * ��ײ��⸨����
 * @author rays1
 *
 */
public abstract class CollisionalSupport implements Collision {

    // ������ײ����
    public void crash(List<? extends Collision> list) {
        Iterator<? extends Collision> i = list.iterator();
        while(i.hasNext())
            if(crash(i.next())) break;
    }
    
    // ��ײ����
    public boolean crash(Collision target) {
        if(isCrash(target)) {
            doCrash(target);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isCrash(Collision target) {
        // ��ײ��Ȿ��
        if(target.hashCode()== hashCode()) {
            return false; //Ŀ���ǵ�ǰ��������
        } else {
            //����
            double d = target.getRadius() + getRadius() - 2;
            //����
            return Math.abs(getLocation().X() - target.getLocation().X()) <= d 
                    && Math.abs(getLocation().Y() - target.getLocation().Y()) <= d;
        }
    }

}

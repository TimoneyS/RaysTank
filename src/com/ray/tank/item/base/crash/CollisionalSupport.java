package com.ray.tank.item.base.crash;

import java.util.Iterator;
import java.util.List;

public abstract class CollisionalSupport implements Collision {

    // 集合碰撞处理
    public void crash(List<? extends Collision> list) {
        Iterator<? extends Collision> i = list.iterator();
        while(i.hasNext())
            if(crash(i.next())) break;
    }
    
    // 碰撞处理
    public boolean crash(Collision target) {
        if(isCrash(target)) {
            doCrash(target);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isCrash(Collision target) {
        //碰撞检测本身不区分队伍颜色等
        if(target.hashCode()== hashCode()) {
            return false;                                                 //目标是当前对象自身
        } else {
            //距离
            double d = target.getRadius() + getRadius() - 2;
            //条件
            return Math.abs(getLocation().X() - target.getLocation().X()) <= d 
                    && Math.abs(getLocation().Y() - target.getLocation().Y()) <= d;
        }
    }

}

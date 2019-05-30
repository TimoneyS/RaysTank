package com.ray.tank.item.base;

import com.ray.tank.item.base.crash.Collision;

public abstract class CollisionalItem extends BaseItem {
    
    public abstract Collision getCollision();
    
}

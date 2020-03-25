package com.rays.tank.model.base;

import com.rays.tank.model.base.crash.Collision;

public abstract class CollisionalItem extends BaseItem {
    
    public abstract Collision getCollision();
    
}

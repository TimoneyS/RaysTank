package com.ray.tank.item.base;

import com.ray.tank.item.base.crash.Collision;
import com.ray.tank.item.other.BattleField;

public abstract class CollisionalItem extends BaseItem {
    
    protected BattleField battleField;                        // 持有的BattleField对象
    
    public abstract Collision getCollision();
    
    public void setBattleField(BattleField battleField) {
        this.battleField = battleField;
    }
    
    public BattleField getBattfield() {
        return battleField;
    }
    
}

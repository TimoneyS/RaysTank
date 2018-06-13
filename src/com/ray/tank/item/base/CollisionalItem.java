package com.ray.tank.item.base;

import com.ray.tank.item.base.crash.Collision;
import com.ray.tank.item.other.BattleField;

public abstract class CollisionalItem extends BaseItem {
    
    protected BattleField battleField;                        // ���е�BattleField����
    
    public abstract Collision getCollision();
    
    public void setBattleField(BattleField battleField) {
        this.battleField = battleField;
    }
    
    public BattleField getBattfield() {
        return battleField;
    }
    
}

package com.rays.tank.model.base;

import java.awt.Graphics2D;

public abstract class BaseItem implements HasLife {
    
    protected boolean alive;
    protected Location location;

    public BaseItem() {
        alive = true;
    }

}

package com.ray.tank.item.base;

import java.awt.Graphics2D;

public abstract class BaseItem implements HasLife {
    
    protected boolean alive;
    protected Location location;

    public BaseItem() {
        alive = true;
    }
    
    public abstract void draw(Graphics2D g);

    public abstract void update();

    public Location getLocation() {
        return location;
    }
    
    public void setLocation(double x, double y) {
        location = new Location(x, y);
    }
    
    public boolean isAlive() {
        return alive;
    }
    
    public void lifeEnd() {
        alive = false;
    }

}

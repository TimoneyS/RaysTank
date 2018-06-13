package com.ray.tank.item.base;

import java.awt.Graphics2D;

public interface BaseItem extends HasLife {

    void draw(Graphics2D g);

    void update();

    void lifeEnd();

}

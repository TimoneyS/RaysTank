package com.ray.tank.item.other;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.ray.tank.common.DrawUtil;
import com.ray.tank.item.base.CollisionalItem;
import com.ray.tank.item.base.Location;
import com.ray.tank.item.base.crash.Collision;
import com.ray.tank.item.base.crash.CollisionalSupport;

public class Wall extends CollisionalItem {

    Location l;
    int width = 10;
    boolean alive;
    
    @Override
    public Collision getCollision() {
        // TODO Auto-generated method stub
        return new WallCollsion();
    }

    @Override
    public void draw(Graphics2D g) {

        int x = (int) l.X();
        int y = (int) l.Y();
        
        DrawUtil.drawShape(g, Color.GRAY, new Rectangle(x-width/2, y-width/2, width, width));
        
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }
    
    class WallCollsion extends CollisionalSupport {

        @Override
        public void doCrash(Collision target) {
            
            Wall.this.lifeEnd();
            
        }

        @Override
        public double getRadius() {
            return width;
        }

        @Override
        public Location getLocation() {
            return l;
        }

        @Override
        public int getType() {
            return 0;
        }

        @Override
        public int getCollosionalType() {
            return 0;
        }

        @Override
        public int getGroup() {
            return 0;
        }

        @Override
        public boolean isAlive() {
            return alive;
        }

    }

}

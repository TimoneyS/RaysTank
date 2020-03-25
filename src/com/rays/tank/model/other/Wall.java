package com.rays.tank.model.other;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.rays.tank.common.DrawUtil;
import com.rays.tank.model.base.CollisionalItem;
import com.rays.tank.model.base.Location;
import com.rays.tank.model.base.crash.Collision;
import com.rays.tank.model.base.crash.CollisionalSupport;

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

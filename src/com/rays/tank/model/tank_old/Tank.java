package com.rays.tank.model.tank_old;

import com.rays.tank.common.Context;
import com.rays.tank.model.base.CollisionalItem;
import com.rays.tank.model.base.Location;
import com.rays.tank.model.base.crash.Collision;
import com.rays.tank.model.base.crash.CollisionalSupport;

import java.awt.*;

public abstract class Tank extends CollisionalItem {

    protected double      direction;
    protected double      weelState;    // �Ĵ���̬Ч��
    protected int         score;        // �÷�
    protected int         group;
    protected Color       color;        // ��ɫ
    protected Collision   tankCollision;
    protected TankMove    tankMove;
    
    public abstract void fire();
    public abstract int getType();
    
    public Tank(double x, double y, double direction,Color teamColor, int group) {
        location = new Location(x, y);
        this.direction = direction;
        this.color = teamColor;
        this.group = group;
        tankCollision = new TankCollision();
        tankMove      = new TankMove();
        weelState = 0;
    }

    public void scoreAdd(int point) {
        score += point;
    }

    @Override
    public Collision getCollision() {
        return tankCollision;
    }
    
    public int getGroup() {
        return group;
    }
    
    protected class TankMove extends MoveSupport {

        @Override
        public double getDirection() {
            return direction;
        }

        @Override
        public Location getLocation() {
            return location;
        }

    }
    
    // ��ײ����
    protected class TankCollision extends CollisionalSupport {

        @Override
        public void doCrash(Collision target) {
            if (target.getType() == Collision.BULLET)
                lifeEnd();
            else
                location.load();
        }

        @Override
        public double getRadius() {
            return Context.size*5;
        }

        @Override
        public Location getLocation() {
            return location;
        }

        @Override
        public int getType() {
            return Tank.this.getType();
        }

        @Override
        public int getCollosionalType() {
            return 0;
        }

        @Override
        public int getGroup() {
            return group;
        }
        
    }
    
}

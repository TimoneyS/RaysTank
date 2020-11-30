package com.rays.tank.model.other;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import com.rays.tank.common.Context;
import com.rays.tank.common.DrawUtil;
import com.rays.tank.model.base.CollisionalItem;
import com.rays.tank.model.base.Location;
import com.rays.tank.model.base.crash.Collision;
import com.rays.tank.model.base.crash.CollisionalSupport;
import com.rays.tank.model.base.move.MoveSupport;
import com.rays.tank.model.tank_old.Tank;

public class Bullet extends CollisionalItem {
    
    private Tank       master;
    private double     direction;        // ����
    private Color      color;            // ��ɫ��־
    private Collision  bulletCollision;
    private double     size = Context.size;
    private BulletMove bulletMove;
	
	public Bullet(Tank tank, Location location, double direction, Color color) {
	    this.master = tank;
        this.direction = direction;
        this.location = new Location(location.X() + Math.sin(direction) * size * 8, 
                location.Y() - Math.cos(direction) * size * 8);
        this.color = color;
        bulletMove = new BulletMove();
        bulletCollision = new BulletCollision();
        bulletMove.setMoveSpeed(Context.bulletSpeed);
    }
	
    //���Ʒ���
	public void draw(Graphics2D g2) {
		Ellipse2D shape = //��״��Բ��
				new Ellipse2D.Double(location.X()- Context.size, location.Y()- Context.size, 2.5* Context.size, 2.5* Context.size);
		DrawUtil.drawShape(g2, color, shape);
	}
	
	//���·���
	public void update() {
		bulletMove.move();
//		bulletCollision.crash(Global.battleField.getTanks());
//		bulletCollision.crash(Global.battleField.getBullets());
	}
	
	//�ӵ��������ڽ��� 
	public void lifeEnd() {
		alive = false;									
//		Global.battleField.createExplode(location);
	}

	@Override
    public Collision getCollision() {
        return bulletCollision;
    }
	
    private class BulletMove extends MoveSupport {
        
        @Override
        public void doOutBound() { lifeEnd();
        }
        
        @Override
        public double getDirection() { return direction; }

        @Override
        public Location getLocation() { return location; }
        
    }
    
	private class BulletCollision extends CollisionalSupport {

        @Override
        public double getRadius() {
            return Context.size*1.25;
        }

        @Override
        public void doCrash(Collision target) {
            if (target.getGroup() != getGroup() && target.isAlive()) {
                if (target.getType() != Collision.BULLET) {
                    lifeEnd();
                    target.doCrash(this);
                    if (!target.isAlive())
                       master.scoreAdd(1);
                } else {
                    lifeEnd();
                    target.doCrash(this);
                }
            }
        }
        
        public int getType() {
            return Collision.BULLET;
        }

        @Override
        public int getCollosionalType() {
            return 0;
        }

        @Override
        public boolean isAlive() {
            return alive;
        }

        @Override
        public Location getLocation() {
            return location;
        }

        @Override
        public int getGroup() {
            return master.getGroup();
        }
	    
	}
	
}
package com.ray.tank.item.other;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import com.ray.tank.common.DrawUtil;
import com.ray.tank.config.Const;
import com.ray.tank.item.base.BaseItem;
import com.ray.tank.item.base.Location;
import com.ray.tank.item.base.crash.Collision;
import com.ray.tank.item.base.crash.CollisionalSupport;
import com.ray.tank.item.base.move.MoveSupport;
import com.ray.tank.item.tank.Tank;

public class Bullet implements BaseItem {
	private boolean alive = true;			//�����־
	private BattleField battleField;		//���е�BattleField����,�Զ�ָ��
	private Tank master;
	private final double direction;			//����
	private final Color color;			    //��ɫ��־
	private Location location;
	private Collision bulletCollision = new BulletCollision();
	double size = Const.size;
	BulletMove bulletMove = new BulletMove();
	
	
	public Bullet(Tank tank, Location location2, double direction2, Color color2) {
	    this.master = tank;
        this.direction = direction2;
        location = new Location(location2.X() + Math.sin(direction) * size * 8, location2.Y() - Math.cos(direction) * size * 8);
        this.color = color2;   
    }
	
    //���Ʒ���
	public void draw(Graphics2D g2) {
		Ellipse2D shape = //��״��Բ��
				new Ellipse2D.Double(location.X()-Const.size, location.Y()-Const.size, 2.5*Const.size, 2.5*Const.size);
		DrawUtil.drawShape(g2, color, shape);
	}
	
	//���·���
	public void update() {
		bulletMove.move();
		bulletCollision.crash(battleField.getTanks());
		bulletCollision.crash(battleField.getBullets());
	}
	
	//�ӵ��������ڽ��� 
	public void lifeEnd() {
		alive = false;									
		battleField.add(Explode.get(location));
	}

	public boolean isAlive() {
		return alive;
	}
	public void setBattleField(BattleField battleField) {
		this.battleField = battleField;
	}
	
    public Collision getCollision() {
        return bulletCollision;
    }
	
    private class BulletMove extends MoveSupport {
        
        @Override
        public void doOutBound() {
            lifeEnd();
        }
        
        @Override
        public double getDirection() {
            return direction;
        }

        @Override
        public double getMoveSpeed() {
            return Const.bulletSpeed;
        }

        @Override
        public Location getLocation() {
            return location;
        }

        @Override
        public BattleField getBattfield() {
            return battleField;
        }
        
    }
    
	private class BulletCollision extends CollisionalSupport {

        @Override
        public double getRadius() {
            return Const.size*1.25;
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
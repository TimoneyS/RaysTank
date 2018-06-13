package com.ray.tank.item.other;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import com.ray.tank.common.DrawUtil;
import com.ray.tank.config.Const;
import com.ray.tank.item.base.CollisionalItem;
import com.ray.tank.item.base.Location;
import com.ray.tank.item.base.crash.Collision;
import com.ray.tank.item.base.crash.CollisionalSupport;
import com.ray.tank.item.base.move.MoveSupport;
import com.ray.tank.item.tank.Tank;

public class Bullet extends CollisionalItem {
    
    private Tank       master;
    private double     direction;        // 方向
    private Color      color;            // 颜色标志
    private Collision  bulletCollision;
    private double     size = Const.size;
    private BulletMove bulletMove;
	
	public Bullet(Tank tank, Location location, double direction, Color color) {
	    this.master = tank;
        this.direction = direction;
        this.location = new Location(location.X() + Math.sin(direction) * size * 8, 
                location.Y() - Math.cos(direction) * size * 8);
        this.color = color;
        bulletMove = new BulletMove();
        bulletCollision = new BulletCollision();
        bulletMove.setMoveSpeed(Const.bulletSpeed);
    }
	
    //绘制方法
	public void draw(Graphics2D g2) {
		Ellipse2D shape = //形状，圆形
				new Ellipse2D.Double(location.X()-Const.size, location.Y()-Const.size, 2.5*Const.size, 2.5*Const.size);
		DrawUtil.drawShape(g2, color, shape);
	}
	
	//更新方法
	public void update() {
		bulletMove.move();
		bulletCollision.crash(battleField.getTanks());
		bulletCollision.crash(battleField.getBullets());
	}
	
	//子弹生命周期结束 
	public void lifeEnd() {
		alive = false;									
		battleField.add(Explode.get(location));
	}

	public boolean isAlive() {
		return alive;
	}
	@Override
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
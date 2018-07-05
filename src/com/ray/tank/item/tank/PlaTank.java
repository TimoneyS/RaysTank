package com.ray.tank.item.tank;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.ray.tank.config.Const;
import com.ray.tank.item.base.Arrow;
import com.ray.tank.item.base.Listenable;
import com.ray.tank.item.base.crash.Collision;
import com.ray.tank.item.other.Bullet;

public class PlaTank extends Tank implements Listenable {

    private boolean fire = false;

    // 运动标志
    private boolean w    = false;
    private boolean s    = false;
    private boolean a    = false;
    private boolean d    = false;

    public PlaTank(double x, double y, double direction, Color teamColor, int group) {
        super(x, y, direction, teamColor, group);
        tankMove.setMoveSpeed(Const.manmoveSpeed);
    }
    
	//提交数据变化
	public void update() {
		move();
		tankCollision.crash(battleField.getTanks());
		fire();
	}
	//生命周期结束
	public void lifeEnd() {
		alive = !Const.canPlayerBeKill;
		battleField.createExplode(location);
	}
	//移动操作
	public void move() {
	    
	    // 首先判定方向
	    Arrow arrow = new Arrow();
	    if (w) arrow.plus(Arrow.W);
        if (s) arrow.plus(Arrow.S);
        if (a) arrow.plus(Arrow.A);
        if (d) arrow.plus(Arrow.D);
        
        if (arrow.equals(Arrow.ZEOR)) {
            // 不移动，不变更方向
        } else {
            // direction
            direction = arrow.toRadians();
            super.move();
        }
        
	}
	//发射子弹
	public void fire() {
		if(alive&&fire) {
//		    for (int i = -3; i < 3; i += 1) {
//		        battleField.addBullet(new Bullet(this, location, Math.toRadians(5 * i) + direction, color));
//            }
			battleField.addBullet(new Bullet(this, location, direction, color));
			fire = false;
		}
	}
	
	public void keyPress(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W : w = true;break;
			case KeyEvent.VK_S : s = true;break;
			case KeyEvent.VK_D : d = true;break;
			case KeyEvent.VK_A : a = true;break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W : w = false;break;
			case KeyEvent.VK_S : s = false;break;
			case KeyEvent.VK_D : d = false;break;
			case KeyEvent.VK_A : a = false;break;
			case KeyEvent.VK_J : fire = true;break;			//点发
		}
		
	}
	
	public int getType() {
		return Collision.PLAYER_TANK;
	}

}

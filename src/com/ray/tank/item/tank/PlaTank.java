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

    // �˶���־
    private boolean w    = false;
    private boolean s    = false;
    private boolean a    = false;
    private boolean d    = false;

    public PlaTank(double x, double y, double direction, Color teamColor, int group) {
        super(x, y, direction, teamColor, group);
        tankMove.setMoveSpeed(Const.manmoveSpeed);
    }
    
	//�ύ���ݱ仯
	public void update() {
		move();
		tankCollision.crash(battleField.getTanks());
		fire();
	}
	//�������ڽ���
	public void lifeEnd() {
		alive = !Const.canPlayerBeKill;
		battleField.createExplode(location);
	}
	//�ƶ�����
	public void move() {
	    
	    // �����ж�����
	    Arrow arrow = new Arrow();
	    if (w) arrow.plus(Arrow.W);
        if (s) arrow.plus(Arrow.S);
        if (a) arrow.plus(Arrow.A);
        if (d) arrow.plus(Arrow.D);
        
        if (arrow.equals(Arrow.ZEOR)) {
            // ���ƶ������������
        } else {
            // direction
            direction = arrow.toRadians();
            super.move();
        }
        
	}
	//�����ӵ�
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
			case KeyEvent.VK_J : fire = true;break;			//�㷢
		}
		
	}
	
	public int getType() {
		return Collision.PLAYER_TANK;
	}

}

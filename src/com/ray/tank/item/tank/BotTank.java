package com.ray.tank.item.tank;

import java.awt.Color;

import com.ray.tank.config.Const;
import com.ray.tank.item.base.Arrow;
import com.ray.tank.item.base.crash.Collision;
import com.ray.tank.item.other.Bullet;
import com.ray.tank.item.other.Explode;

public class BotTank extends Tank {
    
	private double time = 30; //坦克更改方向的帧数
	
	public BotTank(double x, double y, double direction,Color color, int group) {
	    super(x, y, direction, color, group);
	    moveSpeed = Const.botmoveSpeed;
	}
	public void update() {
		
	    if(Const.aiMove) 
		    randomMove();
		
	    tankCollision.crash(battleField.getTanks());
		
		if(Const.aiFire)
		    fire();
	}
	public void randomMove() {
		if(time==1)
		    chooseDirection();
		move(); 
		time ++;
		time = time%30;
	}

	//选择方向
	public void chooseDirection() {
		switch((int)(Math.random()*4)) {
			case 0 : direction = Arrow.W.toRadians();break;
			case 1 : direction = Arrow.A.toRadians();break;
			case 2 : direction = Arrow.S.toRadians();break;
			case 3 : direction = Arrow.D.toRadians();break;
		}
	}
	
	public void fire() {
		if((int)(Math.random()*Const.aiShootRate)==1)		//百分之一的几率开枪
			battleField.add(new Bullet(this, location,direction,color));
	}
	public int getType() {
		return Collision.BOT_TANK;
	}

	public void lifeEnd() {
		battleField.add(Explode.get(location));
		alive = false;
	}

}
package com.ray.tank.item.other;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import com.ray.tank.config.Const;
import com.ray.tank.item.base.BaseItem;
import com.ray.tank.item.base.Location;

public class Explode implements BaseItem{
	private boolean alive;				//生存标志
	private double state = 1;			//状态，描述动画进行到何种程度的标志
	private	final double x,y;			//爆炸的中心位置

	private Explode(Location location) {
	    this.x = location.X();
        this.y = location.Y();
        alive = true;
    }
	
	public static Explode get(Location location) {
	    return new Explode(location);
	}
	
    //绘画
	public void draw(Graphics2D g2) {	
		int radius = Const.MaxExplodRadius;								
		if(state>(radius*4/5)) g2.setColor(Color.RED);					//爆炸阶段5颜色
			else if(state>(radius*3/5)) g2.setColor(Color.ORANGE);		//爆炸阶段4颜色	
			else if(state>(radius*2/5)) g2.setColor(Color.YELLOW);		//爆炸阶段3颜色
			else if(state>(radius*1/5)) g2.setColor(Color.WHITE);		//爆炸阶段2颜色
			else g2.setColor(Color.CYAN);								//爆炸阶段1颜色
		double size = Const.size;
		Ellipse2D s = new Ellipse2D.Double(								//建立形状
				x-state*size/5, y-state*size/5, state*size/2.5, state*size/2.5);
		
		g2.fill(s);														//填充形状
		
	}
	//更新，将状态递进
	public void update() {
		state = state + Const.explodSpeed;
		if(state > Const.MaxExplodRadius)
			lifeEnd();
	}
	//死亡
	public void lifeEnd() {
		alive = false;
	}
	//返回生存标志
	public boolean isAlive() {
		return alive;
	}

	//添加战场
	public void setBattleField(BattleField battlefield) {
	}
	
}
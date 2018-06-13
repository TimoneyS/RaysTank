package com.ray.tank.item.other;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import com.ray.tank.config.Const;
import com.ray.tank.item.base.BaseItem;
import com.ray.tank.item.base.Location;

public class Explode implements BaseItem{
	private boolean alive;				//�����־
	private double state = 1;			//״̬�������������е����̶ֳȵı�־
	private	final double x,y;			//��ը������λ��

	private Explode(Location location) {
	    this.x = location.X();
        this.y = location.Y();
        alive = true;
    }
	
	public static Explode get(Location location) {
	    return new Explode(location);
	}
	
    //�滭
	public void draw(Graphics2D g2) {	
		int radius = Const.MaxExplodRadius;								
		if(state>(radius*4/5)) g2.setColor(Color.RED);					//��ը�׶�5��ɫ
			else if(state>(radius*3/5)) g2.setColor(Color.ORANGE);		//��ը�׶�4��ɫ	
			else if(state>(radius*2/5)) g2.setColor(Color.YELLOW);		//��ը�׶�3��ɫ
			else if(state>(radius*1/5)) g2.setColor(Color.WHITE);		//��ը�׶�2��ɫ
			else g2.setColor(Color.CYAN);								//��ը�׶�1��ɫ
		double size = Const.size;
		Ellipse2D s = new Ellipse2D.Double(								//������״
				x-state*size/5, y-state*size/5, state*size/2.5, state*size/2.5);
		
		g2.fill(s);														//�����״
		
	}
	//���£���״̬�ݽ�
	public void update() {
		state = state + Const.explodSpeed;
		if(state > Const.MaxExplodRadius)
			lifeEnd();
	}
	//����
	public void lifeEnd() {
		alive = false;
	}
	//���������־
	public boolean isAlive() {
		return alive;
	}

	//���ս��
	public void setBattleField(BattleField battlefield) {
	}
	
}
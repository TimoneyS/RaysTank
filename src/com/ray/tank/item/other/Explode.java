package com.ray.tank.item.other;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import com.ray.tank.config.Const;
import com.ray.tank.item.base.BaseItem;
import com.ray.tank.item.base.Location;

public class Explode extends BaseItem {
    
    /**
     * ���α�ʾ���׶ε���ɫ
     */
    private static Color[] STATE_COLORS = {
            Color.CYAN,
            Color.WHITE,
            Color.YELLOW,
            Color.ORANGE,
            Color.RED,
    };
    private static int     radius         = Const.MaxExplodRadius; // ��ը����֡��
    private static int     EXPLOADE_SPEED = Const.explodSpeed;

    private double         size;
    private int            state;                                  // ״̬�������������е����̶ֳȵı�־

	private Explode(Location location) {
	    this.location = location.clone();
	    state = 0;
	    size = Const.size;
    }
	
	public static Explode get(Location location) {
	    return new Explode(location);
	}
	
    // ����
	public void draw(Graphics2D g2) {
	    int stateIdx = state / EXPLOADE_SPEED;
	    if (stateIdx > STATE_COLORS.length) stateIdx = STATE_COLORS.length;	    
	    
	    g2.setColor(STATE_COLORS[stateIdx]);
		Ellipse2D s = new Ellipse2D.Double(								//������״
				location.X()-state*size/5, location.Y()-state*size/5, state*size/2.5, state*size/2.5);
		g2.fill(s);														//�����״
	}
	
	// ���£���״̬�ݽ�
	public void update() {
		state += EXPLOADE_SPEED;
		if(state >= radius) lifeEnd();
	}

}
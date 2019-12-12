package com.rays.tank.item.other;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.*;

import com.rays.tank.common.Const;
import com.rays.tank.common.Global;
import com.rays.tank.item.base.BaseItem;
import com.rays.tank.item.base.HasLife;
import com.rays.tank.item.base.Location;
import com.rays.tank.item.base.crash.Collision;
import com.rays.tank.item.tank.BotTank;
import com.rays.tank.item.tank.PlaTank;
import com.rays.tank.item.tank.Tank;

public class BattleField {
    
    public Rectangle      bounds;   // �߽�

    private List<Bullet>      bullets;     // ���е��ӵ�����
    private List<Explode>     explodes;    // ���еı�ը����
    private List<Tank>        tanks;       // ����̹�˼���
    private List<Collision> tankCollisional; // ��ײ��⼯��
    private List<Collision> bulletCollisional; // ��ײ��⼯��
    
	public BattleField() {
	    Global.regBattleField(this);
	    
	    setBounds(Const.D_WIDTH,Const.D_HEIGTH);
	    
		tanks = new LinkedList<Tank>();
		bullets = new LinkedList<Bullet>();
		explodes = new LinkedList<Explode>();
		tankCollisional = new LinkedList<>();
		bulletCollisional = new LinkedList<>();
		
	}
	public BattleField(int x) {
		this();
		for(int i = 100; i < 500; i += 50) {
			addTank(new BotTank(50, i, 90, Color.YELLOW, 2));		//��ɫ��
			addTank(new BotTank(550, i, 270, Color.CYAN, 2));		//��ɫ��
			addTank(new BotTank(i, 550, 0, Color.RED, 2));		    //��ɫ��
	        addTank(new BotTank(i, 50, 180, Color.BLUE, 2));        //��ɫ��
		}
		
		addTank(new PlaTank(400, 300, 0, Const.playerColor, 1));
		
	}
	// 绘制方法
	public void draw(Graphics2D g2) {
		java.awt.Color c = g2.getColor();
		draw(tanks, g2);
		draw(bullets, g2);
		draw(explodes, g2);
		
		//提示信息
        g2.setColor(Color.BLACK);
        g2.drawString("坦克 : "+ tanks.size(), 30, 10);
        g2.drawString("子弹 : "+ bullets.size(), 30, 30);
        g2.drawString("爆炸 : "+ explodes.size(), 30, 50);
		
        g2.setColor(c);
	}
	
	// 刷新
	public void refresh() {
	    
		refresh(tanks);
		refresh(bullets);
		refresh(explodes);
		cleanDeadItem(bulletCollisional);
		cleanDeadItem(tankCollisional);
	}
	
	public <T extends HasLife> void cleanDeadItem(List<T> items) {
	       // ����
        for (Iterator<T> iterator = items.iterator(); iterator.hasNext();) {
            T collisional = iterator.next();
            if (!collisional.isAlive())
                iterator.remove();
        }

	}
	
	//�����б�
	private synchronized void draw(List<? extends BaseItem> list, Graphics2D g2){
		Iterator<? extends BaseItem> i = list.iterator();
		while(i.hasNext())
			i.next().draw(g2);
	}
	//���±�
	private synchronized <T extends BaseItem> void refresh(List<T> list) {
		Iterator<T> i = list.iterator();
		while(i.hasNext()) {
		    T u = i.next();
			if(!u.isAlive()) i.remove();
			else u.update();
		}
	}
	//��ӷ���
	public synchronized void createExplode(Location location){
		explodes.add(Explode.get(location));
	}
	
	//��ӷ���
	public synchronized void addTank(Tank t){
		tanks.add(t);
		tankCollisional.add(t.getCollision());
	}
	
	//��ӷ���
	public synchronized void addBullet(Bullet b){
		bullets.add(b);
		bulletCollisional.add(b.getCollision());
	}
	
    public List<Collision> getTanks() {
        return tankCollisional;
    }
    
	public List<Collision> getBullets() {
		return bulletCollisional;
	}
	
	public void setBounds(int width, int heigth) {
		bounds = new Rectangle(width, heigth);
	}
	
	/**
	 * �ж������Ƿ񳬹��߽�
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isInBounds(double x, double y) {
	    return bounds.contains(x, y);
	}
	
}
package com.ray.tank.item.other;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.*;

import com.ray.tank.item.base.BaseItem;
import com.ray.tank.item.base.Location;
import com.ray.tank.item.base.crash.Collision;
import com.ray.tank.item.tank.BotTank;
import com.ray.tank.item.tank.Tank;

public class BattleField {
    
    public Rectangle      bounds;   // �߽�

    private List<Bullet>      bullets;     // ���е��ӵ�����
    private List<Explode>     explodes;    // ���еı�ը����
    private List<Tank>        tanks;       // ����̹�˼���
    private List<Collision> tankCollisional; // ��ײ��⼯��
    private List<Collision> bulletCollisional; // ��ײ��⼯��
    
	public BattleField() {
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
		
	}
	//���Ʒ���
	public void draw(Graphics2D g2) {
		java.awt.Color c = g2.getColor();
		draw(tanks, g2);
		draw(bullets, g2);
		draw(explodes, g2);
		g2.setColor(c);
	}
	//���·���
	public void upDate() {
	    
		upDate(tanks);
		upDate(bullets);
		upDate(explodes);
		cleanDeadItem();
		
//		if (tanks.size() < 5) {
//		    addTank(new BotTank(550, 200, 270, Color.RED, 2));
//		}
	}
	
	public void cleanDeadItem() {
	       // ����
        for (Iterator<Collision> iterator = bulletCollisional.iterator(); iterator.hasNext();) {
            Collision collisional = iterator.next();
            if (!collisional.isAlive())
                iterator.remove();
        }
        
        // ����
        for (Iterator<Collision> iterator = tankCollisional.iterator(); iterator.hasNext();) {
            Collision collisional = iterator.next();
            if (!collisional.isAlive())
                iterator.remove();
        }
	}
	
	//�����б�
	private synchronized void draw(List<? extends BaseItem> list,Graphics2D g2){
		Iterator<? extends BaseItem> i = list.iterator();
		while(i.hasNext())
			i.next().draw(g2);
	}
	//���±�
	private synchronized void upDate(List<? extends BaseItem> list) {
		Iterator<? extends BaseItem> i = list.iterator();
		while(i.hasNext()) {
		    BaseItem u = i.next();
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
	
	//��ȡ�����ֵ����������ʱ��������-1
	public int getNumbers(int i) { 				
		if(i==0) return tanks.size();
		if(i==2) return bullets.size();
		if(i==3) return explodes.size();
		return -1;
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
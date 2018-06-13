package com.ray.tank.item.other;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.*;

import com.ray.tank.item.base.BaseItem;
import com.ray.tank.item.base.crash.Collision;
import com.ray.tank.item.tank.BotTank;
import com.ray.tank.item.tank.Tank;

public class BattleField {
    
    public Rectangle      bounds;   // 边界

    private List<Bullet>      bullets;     // 所有的子弹集合
    private List<Explode>     explodes;    // 所有的爆炸集合
    private List<Tank>        tanks;       // 所有坦克集合
    private List<Collision> tankCollisional; // 碰撞检测集合
    private List<Collision> bulletCollisional; // 碰撞检测集合
    
	public BattleField() {
		tanks = new LinkedList<Tank>();
		bullets = new LinkedList<Bullet>();
		explodes = new LinkedList<Explode>();
		tankCollisional = new LinkedList<>();
		bulletCollisional = new LinkedList<>();
	}
	public BattleField(int x) {
		this();
		for(int i = 50; i < 600; i += 50) {
			add(new BotTank(50, i, 90, Color.YELLOW, 2));		//黄色方
			add(new BotTank(550, i, 270, Color.CYAN, 2));		//青色方
			add(new BotTank(i, 550, 0, Color.RED, 2));		//红色方
	        add(new BotTank(i, 50, 180, Color.BLUE, 2));        //蓝色方
		}
	}
	//绘制方法
	public void draw(Graphics2D g2) {
		java.awt.Color c = g2.getColor();
		draw(tanks, g2);
		draw(bullets, g2);
		draw(explodes, g2);
		g2.setColor(c);
	}
	//更新方法
	public void upDate() {
		upDate(tanks);
		upDate(bullets);
		upDate(explodes);
		
		

		// 清理
        for (Iterator<Collision> iterator = bulletCollisional.iterator(); iterator.hasNext();) {
            Collision collisional = iterator.next();
            if (!collisional.isAlive())
                iterator.remove();
        }
        
        // 清理
        for (Iterator<Collision> iterator = tankCollisional.iterator(); iterator.hasNext();) {
            Collision collisional = iterator.next();
            if (!collisional.isAlive())
                iterator.remove();
        }
		
	}
	//绘制列表
	private synchronized void draw(List<? extends BaseItem> list,Graphics2D g2){
		Iterator<? extends BaseItem> i = list.iterator();
		while(i.hasNext())
			i.next().draw(g2);
	}
	//更新表
	private synchronized void upDate(List<? extends BaseItem> list) {
		Iterator<? extends BaseItem> i = list.iterator();
		while(i.hasNext()) {
			BaseItem u = i.next();
			if(!u.isAlive()) i.remove();
			else u.update();
		}
	}
	//添加方法
	public synchronized void add(Explode e){
		explodes.add(e);
	}
	//添加方法
	public synchronized void add(Tank t){
		tanks.add(t);
		tankCollisional.add(t.getCollision());
		t.setBattleField(this);
	}
	//添加方法
	public synchronized void add(Bullet b){
		bullets.add(b);
		bulletCollisional.add(b.getCollision());
		b.setBattleField(this);
	}
	//获取相关数值，输入有误时，将返回-1
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
	 * 判断物体是否超过边界
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isInBounds(double x, double y) {
	    return bounds.contains(x, y);
	}
	
}
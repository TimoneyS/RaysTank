package com.ray.tank.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ray.tank.config.Const;
import com.ray.tank.item.other.BattleField;
import com.ray.tank.item.tank.PlaTank;
@SuppressWarnings("serial")
public class TankPanel extends JPanel {
	JFrame frame;
	
	BattleField battleField;
	PlaTank playerTank;
	
	int flag = 1;
	
	//构造方法
	public TankPanel(JFrame frame) {
		battleField = new BattleField(0);
		playerTank = new PlaTank(400, 300, 0, Const.playerColor, 1);
		this.frame = frame;
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(Const.D_WIDTH,Const.D_HEIGTH));
	}
	
	//绘画方法
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    
	    
		if(battleField != null) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;	
			Color c = g.getColor();
			battleField.draw(g2);
			
			//提示信息
			g.setColor(Color.BLACK);
			g.drawString("坦克 : "+battleField.getNumbers(0), 30, 10);
			g.drawString("子弹 : "+battleField.getNumbers(2), 30, 30);
			g.drawString("爆炸 : "+battleField.getNumbers(3), 30, 50);
			
			g.setColor(c);
		}
		
		
	}
	public void upDate() {
		if(battleField != null);
			battleField.upDate();
	}
	//载入
	public void lunch () {
		//添加监听器
		frame.addKeyListener(new KeyAdapter() {
			public synchronized void keyPressed(KeyEvent e) {
				playerTank.keyPress(e);
			}
			public synchronized void keyReleased(KeyEvent e) {
				playerTank.keyReleased(e);
			}
			
			
		});
		
		battleField.setBounds(Const.D_WIDTH,Const.D_HEIGTH);
		battleField.addTank(playerTank);
		
		new Thread(new PanelTask()).start();
	}
	public void setPlayerTank(PlaTank tank) {
		playerTank = tank;
	}
	public BattleField getBattleField() {
		return battleField;
	}
	public PlaTank getPlayerTank() {
		return playerTank;
	}
	
	class PanelTask implements Runnable {
		public void run() {
			while(true) {
				repaint();
				upDate();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
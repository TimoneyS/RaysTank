package com.rays.tank.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.rays.tank.common.Const;
import com.rays.tank.item.other.BattleField;
@SuppressWarnings("serial")
public class TankPanel extends JPanel {
    
	BattleField battleField;
	
	// 构造方法
	public TankPanel(BattleField battleField2) {
		this.battleField = battleField2;
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(Const.D_WIDTH,Const.D_HEIGTH));
	}
	
	// 绘画方法
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
		if(battleField != null) {
			battleField.draw((Graphics2D)g);
		}
		
	}
	
}
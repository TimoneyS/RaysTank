package com.ray.tank.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ray.tank.common.Const;
import com.ray.tank.common.Global;
import com.ray.tank.item.other.BattleField;

@SuppressWarnings("serial")
public class TankFrame extends JFrame {
    
    TankPanel tankPanel;
    JPanel infoPanel;
    BattleField battleField;
    
	public TankFrame() {
	    
	    Global.regFrame(this);
	    
		setIconImage(new ImageIcon("tank/icon.png").getImage());
		setTitle("̹Tank War Ver 1.9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		battleField = new BattleField(0);
		tankPanel = new TankPanel(battleField);
		infoPanel = new JPanel();
		
		infoPanel.setPreferredSize(new Dimension(Const.D_WIDTH,50));
		
	}
	
	public void lunch() {
		add(tankPanel, BorderLayout.CENTER);
		add(infoPanel,BorderLayout.SOUTH);
		pack();
		setVisible(true);
		
		new Thread(() ->  {
		    while(true) {
		        
    		    tankPanel.repaint();
    		    
    		    if (Global.battleField != null)
    		        Global.battleField.refresh();
    		    
    		    try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
		    }
		}).start();
		
	}
	
}

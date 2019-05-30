package com.ray.tank.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ray.tank.common.Const;
import com.ray.tank.common.Global;
import com.ray.tank.item.base.Listenable;

@SuppressWarnings("serial")
public class TankFrame extends JFrame {
    
    TankPanel panel;
    JPanel infoPanel;
    List<Listenable> keyListeners;
    
	public TankFrame() {
	    
		setIconImage(new ImageIcon("tank/icon.png").getImage());
		setTitle("̹Tank War Ver 1.9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		panel = new TankPanel(this);
		infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(Const.D_WIDTH,50));
		
		Global.regFrame(this);
	}
	
	public void lunch() {
		panel.lunch();
		add(panel, BorderLayout.CENTER);
		add(infoPanel,BorderLayout.SOUTH);
		pack();
		setVisible(true);
		
		new Thread(() ->  {
		    repaint();
		    panel.upDate();
		    try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}).run();
		
	}
	
}

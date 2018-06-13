package com.ray.tank.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ray.tank.config.Const;

@SuppressWarnings("serial")
public class TankFrame extends JFrame {
    
	public TankFrame() {
	    
		setIconImage(new ImageIcon("tank/icon.png").getImage());
		setTitle("ÃπøÀ ¿ΩÁ Ver 1.9");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
	}
	
	public void lunch() {
		TankPanel panel = new TankPanel(this);
		panel.lunch();
		JPanel infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(Const.D_WIDTH,50));
		
		add(panel, BorderLayout.CENTER);
		add(infoPanel,BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}
	
}

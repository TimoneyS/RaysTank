package com.rays.tank.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rays.tank.common.Const;
import com.rays.tank.common.Global;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

    GamePanel gamePanel;
    JPanel infoPanel;
    BattleField battleField;
    ScheduledExecutorService scheduledExecutor;

    public GameFrame() {
    	Global.regFrame(this);

        setIconImage(new ImageIcon("tank/icon.png").getImage());
        setTitle("̹Tank War Ver 1.9");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        battleField = new BattleField();
        gamePanel = new GamePanel(battleField);
        infoPanel = new JPanel();

        infoPanel.setPreferredSize(new Dimension(Const.D_WIDTH, 50));
		scheduledExecutor = Executors.newScheduledThreadPool(1);
    }

    public void lunch() {
        add(gamePanel, BorderLayout.CENTER);
//        add(infoPanel, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        scheduledExecutor.scheduleWithFixedDelay(()-> gamePanel.repaint(), 0, 30, TimeUnit.MILLISECONDS);
    }
}

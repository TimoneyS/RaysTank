package com.rays.tank.main;

import com.rays.tank.common.Const;
import com.rays.tank.common.Global;
import com.rays.tank.view.GamePanel;
import com.rays.tank.view.TankMenuBar;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game extends JFrame {
    private GamePanel gamePanel;
    private JPanel infoPanel;
    private ScheduledExecutorService scheduledExecutor;

    public Game() {
    	Global.regFrame(this);
        setIconImage(new ImageIcon("tank/icon.png").getImage());
        setTitle("̹Tank War Ver 1.9");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gamePanel = new GamePanel();
        infoPanel = new JPanel();

        gamePanel.setPreferredSize(new Dimension(Const.D_WIDTH * 4 / 5 , Const.D_HEIGTH));
        infoPanel.setPreferredSize(new Dimension(Const.D_WIDTH * 1 / 5, Const.D_HEIGTH));
		scheduledExecutor = Executors.newScheduledThreadPool(1);

        setJMenuBar(new TankMenuBar());
        add(gamePanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);
    }

    public void lunch() {
        pack();
        setVisible(true);
        scheduledExecutor.scheduleWithFixedDelay(()-> gamePanel.repaint(), 0, 30, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        new Game().lunch();
    }
}

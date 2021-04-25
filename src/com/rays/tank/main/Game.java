package com.rays.tank.main;

import com.rays.tank.common.Context;
import com.rays.tank.common.ResourceLoader;
import com.rays.tank.controller.BattleFieldControl;
import com.rays.tank.model.BattleField;
import com.rays.tank.view.BattleFieldDrawer;
import com.rays.tank.view.BattleKeyListener;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game extends JFrame {
    private ScheduledExecutorService scheduledExecutor;
    private JPanel gamePanel;
    private BattleFieldDrawer battleFieldDrawer = new BattleFieldDrawer();
    private BattleKeyListener battleKeyListener = new BattleKeyListener();

    public Game() {
    	Context.regFrame(this);
        setIconImage(new ImageIcon("tank/icon.png").getImage());
        setTitle("̹Tank War Ver 1.9");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationByPlatform(true);

        gamePanel = new JPanel() {
            // 绘画方法
            @Override
            protected void paintComponent(Graphics g) {
                if (Context.battleField != null) {
                    g.drawImage(
                            battleFieldDrawer.getImage(Context.battleField),
                            0,
                            0,
                            null);
                    BattleFieldControl.update(Context.battleField);
                }
            }
        };
        gamePanel.setPreferredSize(new Dimension(Context.D_WIDTH, Context.D_HEIGHT));
        setContentPane(gamePanel);
        scheduledExecutor = Executors.newScheduledThreadPool(1);
    }

    public void lunch() {
        pack();
        setVisible(true);
        scheduledExecutor.scheduleWithFixedDelay(() -> gamePanel.repaint(), 0, 30, TimeUnit.MILLISECONDS);
        BattleField battleField = new BattleField(ResourceLoader.getClassPathResource("battleField_001.txt"));
        Context.regBattleField(battleField);
        addKeyListener(battleKeyListener);
    }

    public static void main(String[] args) {
        new Game().lunch();
    }
}
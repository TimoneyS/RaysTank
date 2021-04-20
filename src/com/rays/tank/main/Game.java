package com.rays.tank.main;

import com.rays.tank.common.Context;
import com.rays.tank.controller.BattleFieldControl;
import com.rays.tank.controller.TankControl;
import com.rays.tank.model.BattleField;
import com.rays.tank.view.BattleFieldDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game extends JFrame {
    private GamePanel gamePanel;
    private ScheduledExecutorService scheduledExecutor;

    public Game() {
    	Context.regFrame(this);
        setIconImage(new ImageIcon("tank/icon.png").getImage());
        setTitle("̹Tank War Ver 1.9");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationByPlatform(true);

        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(Context.D_WIDTH, Context.D_HEIGHT));
        setContentPane(gamePanel);
        scheduledExecutor = Executors.newScheduledThreadPool(1);
    }

    public void lunch() {
        pack();
        setVisible(true);
        scheduledExecutor.scheduleWithFixedDelay(() -> gamePanel.repaint(), 0, 30, TimeUnit.MILLISECONDS);
        BattleField battleField = new BattleField(
                getClass().getClassLoader().getResourceAsStream("battleField_001.txt"));
        Context.regBattleField(battleField);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                TankControl.handleKeyPress(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                TankControl.handleKeyReleased(e);
            }
        });
    }

    public static void main(String[] args) {
        new Game().lunch();
    }
}

class GamePanel extends JPanel {
    BattleFieldDrawer battleFieldDrawer = new BattleFieldDrawer();

    public GamePanel() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = e.getY() / Context.blockSize;
                int col = e.getX() / Context.blockSize;

                System.out.println(e.getX() + ", " + e.getY() + " -> " + row + ", " + col);
            }
        });
    }
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
}
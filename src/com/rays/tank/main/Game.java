package com.rays.tank.main;

import com.rays.tank.common.Const;
import com.rays.tank.common.Context;
import com.rays.tank.model.BattleField;
import com.rays.tank.view.TankMenuBar;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
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

        gamePanel = new GamePanel();
        gamePanel.setPreferredSize(new Dimension(Const.D_WIDTH * 4 / 5 , Const.D_HEIGTH));
        setJMenuBar(new TankMenuBar());
        setContentPane(gamePanel);

        scheduledExecutor = Executors.newScheduledThreadPool(1);
    }

    public void lunch() {
        pack();
        setVisible(true);
        scheduledExecutor.scheduleWithFixedDelay(()-> gamePanel.repaint(), 0, 30, TimeUnit.MILLISECONDS);
        BattleField battleField = new BattleField(
                getClass().getClassLoader().getResourceAsStream("battleField_001.txt"));
        Context.regBattleField(battleField);
    }

    public static void main(String[] args) {
        new Game().lunch();
    }
}

class GamePanel extends JPanel {
    // 绘画方法
    @Override
    protected void paintComponent(Graphics g) {
        if (Context.battleField != null) {
            g.drawImage(
                    Context.battleField.getImage(),
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    null);
        }
    }
}
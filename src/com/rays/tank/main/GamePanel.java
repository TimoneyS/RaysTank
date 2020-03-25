package com.rays.tank.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.rays.tank.common.Const;
import com.rays.tank.view.TankDrawer;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

    BattleField battleField;

    // 构造方法
    public GamePanel(BattleField battleField2) {
        this.battleField = battleField2;
//        setBackground(Color.GREEN);
        setPreferredSize(new Dimension(Const.D_WIDTH, Const.D_HEIGTH));
    }

    // 绘画方法
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (battleField != null) {
            battleField.getTankList().forEach(tank -> TankDrawer.draw((Graphics2D)g, tank));
        }
    }
}
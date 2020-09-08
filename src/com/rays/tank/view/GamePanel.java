package com.rays.tank.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.rays.tank.common.Const;
import com.rays.tank.model.BattleField;

public class GamePanel extends JPanel {

    private BattleField battleField;

    // 绘画方法
    @Override
    protected void paintComponent(Graphics g) {

//        BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
//        for (int i = 0; i < 200; i++) {
//            img.setRGB(i, i, 255);
//        }
//        g.drawImage(img, 0,0, null);

        if (battleField == null || battleField.getStatus() != Const.BF_ST_OPEN) {
            Drawer.drawRect(
                    g,
                    Color.ORANGE,
                    getWidth()/2, getHeight()/2, getWidth()/4,getHeight()/8);
        } else {

        }
//        if (battleField != null) {
//            battleField.getTankList().forEach(tank -> TankDrawer.draw((Graphics2D)g, tank));
//        }
    }
}
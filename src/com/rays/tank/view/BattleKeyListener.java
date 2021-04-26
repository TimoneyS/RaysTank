package com.rays.tank.view;

import com.rays.tank.common.Context;
import com.rays.tank.controller.TankControl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BattleKeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        if (e.getKeyCode() == KeyEvent.VK_P) {
            Context.battleField.boom();
        }

        TankControl.handleKeyPress(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        TankControl.handleKeyReleased(e);
    }
}

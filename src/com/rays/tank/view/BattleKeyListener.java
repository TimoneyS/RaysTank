package com.rays.tank.view;

import com.rays.tank.controller.TankControl;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BattleKeyListener extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        TankControl.handleKeyPress(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        TankControl.handleKeyReleased(e);
    }
}

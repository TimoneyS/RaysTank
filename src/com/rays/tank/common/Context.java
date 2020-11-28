package com.rays.tank.common;

import javax.swing.JFrame;

import com.rays.tank.model.BattleField;
import com.rays.tank.main.Game;

import java.util.concurrent.atomic.AtomicLong;

public class Context {
    public static JFrame frame = null;
    public static BattleField battleField = null;

    public static void regFrame(Game tankFrame) {
       frame = tankFrame;
    }
    
    public static void regBattleField(BattleField bf) {
        battleField = bf;
    }
    
}

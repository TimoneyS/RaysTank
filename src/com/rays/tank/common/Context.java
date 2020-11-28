package com.rays.tank.common;

import com.rays.tank.main.Game;
import com.rays.tank.model.BattleField;

import javax.swing.*;

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

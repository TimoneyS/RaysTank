package com.rays.tank.common;

import javax.swing.JFrame;

import com.rays.tank.main.BattleField;
import com.rays.tank.main.GameFrame;

public class Global {
        
    public static JFrame frame = null;
    public static BattleField battleField = null;

    public static void regFrame(GameFrame tankFrame) {
       frame = tankFrame;
    }
    
    public static void regBattleField(BattleField bf) {
        battleField = bf;
    }
    
}

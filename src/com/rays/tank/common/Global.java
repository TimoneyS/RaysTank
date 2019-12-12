package com.rays.tank.common;

import javax.swing.JFrame;

import com.rays.tank.item.other.BattleField;
import com.rays.tank.view.TankFrame;

public class Global {
        
    public static JFrame frame = null;
    public static BattleField battleField = null;

    public static void regFrame(TankFrame tankFrame) {
       frame = tankFrame;
    }
    
    public static void regBattleField(BattleField bf) {
        battleField = bf;
    }
    
}

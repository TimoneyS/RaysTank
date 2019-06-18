package com.ray.tank.common;

import java.awt.Color;

public class Const {
    
    public static final double  size            = 5;          // 尺寸

    public static final boolean canPlayerBeKill = false;      // 玩家是否无敌
    public static final Color   playerColor     = Color.pink; // 玩家颜色

    public static final double  bulletSpeed     = 45;         // 子弹速度
    public static final double  manmoveSpeed    = 8;          // 玩家移动速度
    public static final double  botmoveSpeed    = 3;          // AI移动速度
    
    public static final boolean aiFire          = true;       // AI是否开火
    public static final boolean aiMove          = false;       // AI是否移动
    public static final int     aiShootRate     = 100;        // AI射击频率

    public static final int     MaxExplodRadius = 30;         // 最大爆炸半径
    public static final int     explodSpeed     = 3;          // 爆炸扩展速度

    public static final int     D_HEIGTH        = 600;
    public static final int     D_WIDTH         = 800;
    
}

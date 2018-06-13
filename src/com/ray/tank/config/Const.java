package com.ray.tank.config;

import java.awt.Color;

public class Const {
    
    public static final double  size            = 5;          // 尺寸

    public static final boolean canPlayerBeKill = false;      // 玩家坦克可以死亡
    public static final Color   playerColor     = Color.pink; // 玩家坦克颜色

    public static final double  bulletSpeed     = 15;         // 发射子弹速度
    public static final double  manmoveSpeed    = 8;          // 移动速度
    public static final double  botmoveSpeed    = 3;          // 移动速度
    
    public static final boolean aiFire          = true;       // 电脑发射
    public static final boolean aiMove          = true;       // 电脑移动
    public static final int     aiShootRate     = 100;        // 数字越小越快

    public static final int     MaxExplodRadius = 30;         // 爆炸最大尺寸
    public static final int     explodSpeed     = 3;          // 爆炸的更新速度，数字越大越快

    public static final int     D_HEIGTH        = 600;
    public static final int     D_WIDTH         = 800;
    
}

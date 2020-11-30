package com.rays.tank.common;

import java.awt.Color;

public interface Const {
    int DIR_UP    = 0;
    int DIR_DOWN  = 2;
    int DIR_RIGHT = 1;
    int DIR_LEFT  = 3;

    int[][] DIRS = new int[][] {
            { 0, -1},
            { 1,  0},
            { 0,  1},
            {-1,  0}
    };

    int  size               = 10;          // 尺寸
    int  blockSize         = 50;          // 区块大小

    boolean canPlayerBeKill = false;      // 玩家是否无敌
    Color   playerColor     = Color.pink; // 玩家颜色

    double  bulletSpeed     = 45;         // 子弹速度
    double  manmoveSpeed    = 8;          // 玩家移动速度
    double  botmoveSpeed    = 3;          // AI移动速度
    
    boolean aiFire          = true;       // AI是否开火
    boolean aiMove          = false;       // AI是否移动
    int     aiShootRate     = 100;        // AI射击频率

    int     MaxExplodRadius = 30;         // 最大爆炸半径
    int     explodSpeed     = 3;          // 爆炸扩展速度

    int     D_HEIGTH        = 600;
    int     D_WIDTH         = 800;

    int BF_ST_INIT  = 0; // 战场状态 - 初始
    int BF_ST_OPEN  = 1; // 战场状态 - 运行
    int BF_ST_CLOSE = 2; // 战场状态 - 关闭
}

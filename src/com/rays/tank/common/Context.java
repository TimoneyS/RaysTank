package com.rays.tank.common;

import com.rays.tank.main.Game;
import com.rays.tank.model.BattleField;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Context {
    public static final int DIR_UP    = 0;
    public static final int DIR_DOWN  = 2;
    public static final int DIR_RIGHT = 1;
    public static final int DIR_LEFT  = 3;
    public static final int[][] DIRS = new int[][] {
            { 0, -1},
            { 1,  0},
            { 0,  1},
            {-1,  0}
    };
    public static final int  size               = 10;          // 尺寸
    public static final int  blockSize         = 50;          // 区块大小
    public static final boolean canPlayerBeKill = false;      // 玩家是否无敌
    public static final Color playerColor     = Color.pink; // 玩家颜色
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
    public static final int BF_ST_INIT  = 0; // 战场状态 - 初始
    public static final int BF_ST_OPEN  = 1; // 战场状态 - 运行
    public static final int BF_ST_CLOSE = 2; // 战场状态 - 关闭
    public static final boolean debug = true;
    public static JFrame frame = null;
    public static BattleField battleField = null;
    public static AtomicInteger sequence = new AtomicInteger(2);

    public static void regFrame(Game tankFrame) {
       frame = tankFrame;
    }
    
    public static void regBattleField(BattleField bf) {
        battleField = bf;
    }

    public static int nextSeq() {
        return sequence.getAndIncrement();
    }
}

package com.rays.tank.common;

import com.rays.tank.main.Game;
import com.rays.tank.model.BattleField;
import com.rays.tank.model.Tank;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Context {
    public static final int UP = 0;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 1;
    public static final int[][] DIRS = new int[][]{
            {0, -1},
            {1, 0},
            {0, 1},
            {-1, 0}
    };

    public static final int D_WIDTH = 1050;
    public static final int D_HEIGHT = 700;
    public static final int blockSize = 50;         // 区块大小
    public static final int bulletSpeed = 10;         // 子弹速度
    public static final int manMoveSpeed = 8;          // 玩家移动速度
    public static final int botMoveSpeed = 3;          // AI移动速度
    public static final boolean canPlayerBeKill = false;      // 玩家是否无敌
    public static final boolean aiFire = false;       // AI是否开火
    public static final boolean aiMove = true;      // AI是否移动
    public static final boolean debug = true;

    public static JFrame frame = null;
    public static BattleField battleField = null;
    public static Tank plaTank = null;
    public static AtomicInteger sequence = new AtomicInteger(2);

    public static int[] toRowAndCol(int xPix, int yPix) {
        return new int[]{yPix / blockSize, xPix / blockSize};
    }

    public static void regFrame(Game tankFrame) {
        frame = tankFrame;
    }

    public static void regBattleField(BattleField bf) {
        battleField = bf;
        plaTank = battleField.getTankMap().get(0);
    }

    public static int nextSeq() {
        return sequence.getAndIncrement();
    }
}

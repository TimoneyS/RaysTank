package com.rays.tank.common;

import com.rays.tank.model.BattleField;
import com.rays.tank.model.Tank;
import com.rays.tank.model.XY;

import java.util.concurrent.atomic.AtomicInteger;

public class Context {
    public static final int D_WIDTH = 1050;
    public static final int D_HEIGHT = 700;
    public static final int blockSize = 50;         // 区块大小
    public static final int bulletSpeed = 14;         // 子弹速度
    public static final int manMoveSpeed = 4;          // 玩家移动速度
    public static final int botMoveSpeed = 3;          // AI移动速度
    public static final boolean canPlayerBeKill = false;      // 玩家是否无敌
    public static final boolean aiFire = false;       // AI是否开火
    public static final boolean aiMove = false;      // AI是否移动
    public static final boolean debug = true;
    public static final boolean AI_COULD_DIE = true;

    public static BattleField battleField = null;
    public static Tank plaTank = null;
    public static AtomicInteger sequence = new AtomicInteger(2);

    public static XY toRowAndCol(XY xy) {
        return XYUtil.create(xy.getY() / blockSize, xy.getX() / blockSize);
    }

    public static void regBattleField(BattleField bf) {
        battleField = bf;
        plaTank = battleField.getTankMap().get(0);
    }

    public static int nextSeq() {
        return sequence.getAndIncrement();
    }

    public static boolean isValidRowCol(int[] rc) {
        return rc[0] >= 0 && rc[1] >= 0
                && rc[0] < battleField.getFields().length
                && rc[1] < battleField.getFields()[0].length;
    }
}

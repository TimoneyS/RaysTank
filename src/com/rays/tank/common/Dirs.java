package com.rays.tank.common;

public class Dirs {
    public static final int UP = 0;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 1;
    private static final int[][] DIRS = new int[][]{
            {0, -1},
            {1, 0},
            {0, 1},
            {-1, 0}
    };

    public static int[] get(int dirIndex) {
        return DIRS[dirIndex];
    }

    public static int size() {
        return DIRS.length;
    }
}

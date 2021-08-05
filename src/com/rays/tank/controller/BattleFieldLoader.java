package com.rays.tank.controller;

import com.rays.tank.common.Context;
import com.rays.tank.model.BattleField;
import com.rays.tank.model.XY;

import java.io.InputStream;
import java.util.Scanner;

public class BattleFieldLoader {
    public static BattleField parseBattleField(InputStream inputStream) {
        BattleField battleField;
        try(Scanner sc = new Scanner(inputStream)) {
            int row = sc.nextInt();
            int column = sc.nextInt();
            Context.blockSize = Math.min(Context.D_WIDTH / column, Context.D_HEIGHT / row);
            sc.nextLine();
            battleField = new BattleField(row, column);
            for (int i = 0; i < row; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < column; j++) {
                    char fieldChar = line.charAt(j);
                    switch (fieldChar) {
                        case 'p' :
                            battleField.setPlayerStarter(XY.of(j, i).multiply(Context.blockSize));
                            break;
                        case 'e' :
                            battleField.enemyStarter.add(XY.of(j, i).multiply(Context.blockSize));
                            break;
                        default : battleField.setField(i, j, line.charAt(j) - '0');
                    }
                }
            }
        }
        return battleField;
    }
}

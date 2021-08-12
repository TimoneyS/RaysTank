package com.rays.tank.controller;

import com.rays.tank.common.Context;
import com.rays.tank.common.ResourceLoader;
import com.rays.tank.model.BattleField;
import com.rays.tank.model.Boom;
import com.rays.tank.model.Bullet;
import com.rays.tank.model.Tank;
import com.rays.tank.model.XY;

import java.io.InputStream;
import java.util.Scanner;

public class BattleFieldControl {

    public static void loadMap(String resourceName) {
        BattleField battleField = parseBattleField(ResourceLoader.getClassPathResource(resourceName));
        Context.regBattleField(battleField);
    }

    public static void update(BattleField battleField) {
        battleField.flush();
        Tank playerTank = battleField.getTankMap().get(0);
        if (playerTank == null || playerTank.isNotActive()) {
            playerTank = new Tank(battleField.getPlayerStarter(), 0);
            battleField.getTankMap().put(0, playerTank);
        }

        if (battleField.getTankMap().size() < 4 && battleField.enemyLeft > 0) {
            XY enemyStart = battleField.enemyStarter.get((int) (Math.random() * battleField.enemyStarter.size()));
            Tank enemyTank = new Tank(enemyStart,3);
            enemyTank.setId(Context.nextSeq());
            enemyTank.setBot(true);
            enemyTank.setSpeed(2);
            battleField.enemyLeft --;
            battleField.addTank(enemyTank);
        }
        battleField.getTankMap().values().forEach(TankControl::move);
        battleField.getBulletMap().values().forEach(BulletControl::move);
        battleField.getBoomMap().values().forEach(Boom::update);
        battleField.getBulletMap().values().forEach(bullet -> {
            battleField.getTankMap().values().forEach(tank -> {
                if (tank.getXy().maxDist(bullet.getXy()) < 30) {
                    Boom boom = new Boom(Context.nextSeq(), tank.getXy().getX(), tank.getXy().getY(), 0);
                    battleField.addBoom(boom);
                    if (Context.AI_COULD_DIE) {
                        tank.growUp();
                    }
                    bullet.growUp();
                }
            });
        });

        battleField.getBulletMap().values()
                .stream()
                .filter(Bullet::isNotActive)
                .forEach(bullet -> {
            Boom boom = new Boom(Context.nextSeq(), bullet.getXy().getX(), bullet.getXy().getY(), 0);
            battleField.addBoom(boom);
        });
        battleField.clearNoActives();
    }

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

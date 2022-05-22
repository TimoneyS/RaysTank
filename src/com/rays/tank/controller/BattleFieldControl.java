package com.rays.tank.controller;

import com.rays.tank.common.Context;
import com.rays.tank.model.BattleField;
import com.rays.tank.model.Boom;
import com.rays.tank.model.Bullet;
import com.rays.tank.model.Tank;
import com.rays.tank.model.XY;

import java.io.InputStream;
import java.util.Scanner;

/**
 * 战场状态控制层
 *  负责战场中各模型状态的变更
 *
 */
public class BattleFieldControl {

    public static void loadMap(String resourceName) {
        BattleField battleField = parseBattleField(BattleFieldControl.class.getClassLoader().getResourceAsStream(resourceName));
        Context.regBattleField(battleField);
    }

    public static void update(BattleField battleField) {
        //将一些新生的对象加入进来
        battleField.flush();
        Tank playerTank = battleField.getTank(Context.PLAYER_ID);
        reCreatePlayerTank(battleField, playerTank);
        tryCreateEnemy(battleField);
        battleField.getTankMap().values().forEach(TankControl::move);
        battleField.getBulletMap().values().forEach(BulletControl::move);
        battleField.getBoomMap().values().forEach(Boom::update);
        bulletMove(battleField);
        battleField.clearNoActives();
    }

    private static void bulletMove(BattleField battleField) {
        for (Bullet bullet : battleField.getBulletMap().values()) {
            for (Tank tank : battleField.getTankMap().values()) {
                if (tank.getXy().maxDist(bullet.getXy()) >= 30) {
                    continue;
                }
                if (tank.getId() == bullet.getParentId()) {
                    continue;
                }
                if (tank.getTeam() == bullet.getTeam()) {
                    continue;
                }

                Boom boom = new Boom(Context.nextSeq(), tank.getXy().getX(), tank.getXy().getY(), 0);
                battleField.addBoom(boom);
                if (Context.AI_COULD_DIE) {
                    tank.growUp();
                }
                bullet.growUp();
            }
        }

        battleField.getBulletMap().values()
                .stream()
                .filter(Bullet::isNotActive)
                .forEach(bullet -> {
                    Boom boom = new Boom(Context.nextSeq(), bullet.getXy().getX(), bullet.getXy().getY(), 0);
                    battleField.addBoom(boom);
                });
    }

    private static void tryCreateEnemy(BattleField battleField) {
        if (Math.random() * 10 > 3) {
            return;
        }
        if (battleField.getTankMap().size() >= 4 || battleField.enemyLeft <= 0) {
            return;
        }
        XY enemyStart = battleField.enemyStarter.get((int) (Math.random() * battleField.enemyStarter.size()));
        Tank enemyTank = new Tank(enemyStart,3, Context.ENEMY_TEAM);
        enemyTank.setId(Context.nextSeq());
        enemyTank.setBot(true);
        enemyTank.setSpeed(2);
        battleField.enemyLeft --;
        battleField.addTank(enemyTank);
    }

    private static void reCreatePlayerTank(BattleField battleField, Tank playerTank) {
        if (playerTank == null || playerTank.isNotActive()) {
            playerTank = new Tank(battleField.getPlayerStarter(), 0, Context.PLAYER_TEAM);
            battleField.getTankMap().put(0, playerTank);
        }
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

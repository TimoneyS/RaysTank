package com.rays.tank.controller;

import com.rays.tank.common.Context;
import com.rays.tank.common.ResourceLoader;
import com.rays.tank.common.XYUtil;
import com.rays.tank.model.BattleField;
import com.rays.tank.model.Boom;
import com.rays.tank.model.Bullet;
import com.rays.tank.model.Tank;
import com.rays.tank.model.XY;

public class BattleFieldControl {

    public static void loadMap(String resourceName) {
        BattleField battleField = BattleFieldLoader
                .parseBattleField(ResourceLoader.getClassPathResource(resourceName));
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
                if ((XYUtil.maxDist(tank.getXy(), bullet.getXy()) < 30)) {
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
}

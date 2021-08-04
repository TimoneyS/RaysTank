package com.rays.tank.controller;

import com.rays.tank.common.Context;
import com.rays.tank.common.ResourceLoader;
import com.rays.tank.common.XYUtil;
import com.rays.tank.model.BattleField;
import com.rays.tank.model.Boom;
import com.rays.tank.model.Bullet;

public class BattleFieldControl {

    public static void loadMap(String resourceName) {
        BattleField battleField = BattleFieldLoader
                .parseBattleField(ResourceLoader.getClassPathResource(resourceName));
        Context.regBattleField(battleField);
    }

    public static void update(BattleField battleField) {
        battleField.flush();

        battleField.getTankMap().values().forEach(TankControl::move);
        battleField.getBulletMap().values().forEach(BulletControl::move);
        battleField.getBoomMap().values().forEach(Boom::update);

        battleField.getBulletMap().values().forEach(bullet -> {
            battleField.getTankMap().values().forEach(tank -> {
                if (tank.getId() > 1 && (XYUtil.maxDist(tank.getXy(), bullet.getXy()) < 30)) {
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

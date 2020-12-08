package com.rays.tank.controller;

import com.rays.tank.common.Context;
import com.rays.tank.model.BattleField;
import com.rays.tank.model.Boom;
import com.rays.tank.model.Bullet;

public class BattleFieldControl {
    public static void update(BattleField battleField) {
        battleField.flush();

        battleField.getTankMap().values().forEach(TankControl::move);
        battleField.getBulletMap().values().forEach(BulletControl::move);
        battleField.getBoomMap().values().forEach(Boom::update);

        battleField.getBulletMap().values().forEach(bullet -> {
            battleField.getTankMap().values().forEach(tank -> {
                if (tank.getId() > 1 && (Math.abs(tank.getX() - bullet.getX()) + Math.abs(tank.getY() - bullet.getY()) < 30)) {
                    Boom boom = new Boom(Context.nextSeq(), tank.getX(), tank.getY(), 0);
                    battleField.addBoom(boom);
                    tank.destroy();
                    bullet.destroy();
                }
            });
        });

        battleField.getBulletMap().values()
                .stream()
                .filter(Bullet::isNotActive)
                .forEach(bullet -> {
            Boom boom = new Boom(Context.nextSeq(), bullet.getX(), bullet.getY(), 0);
            battleField.addBoom(boom);
        });
        battleField.clearNoActives();
    }
}

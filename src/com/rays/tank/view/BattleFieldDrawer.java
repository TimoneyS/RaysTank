package com.rays.tank.view;

import com.rays.tank.common.Context;
import com.rays.tank.common.FieldType;
import com.rays.tank.model.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BattleFieldDrawer {
    private BufferedImage image = new BufferedImage(Context.D_WIDTH, Context.D_HEIGHT, BufferedImage.TYPE_INT_RGB);
    private Graphics graphics = image.createGraphics();
    private BattleField battleField;
    private Color BG_COLOR = Color.LIGHT_GRAY;

    public Image getImage(BattleField battleField) {
        this.battleField = battleField;
        Color c = graphics.getColor();
        clear();
        if (battleField.isGameOver()) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            graphics.setColor(Color.RED);
//            graphics.drawString("你赢了", 100, 100);
        } else {
            drawInfo();
            drawTanks();
            drawBullets();
            drawBooms();
            drawGround();
        }
        graphics.setColor(c);
        return image;
    }

    private void drawInfo() {
        graphics.setColor(Color.RED);
        graphics.drawString("剩余敌人: " + battleField.enemyLeft, 50, 50);
    }

    private void drawGround() {
        int[][] ground = battleField.getFields();
        for (int row = 0; row < ground.length; row ++) {
            for (int col = 0; col < ground[row].length; col ++) {
                if (ground[row][col] == FieldType.ground) {
                    continue;
                }

                Image image = null;
                if (ground[row][col] == FieldType.WALL_1) {
                    image = Images.imgWall;
                } else if (ground[row][col] == FieldType.WALL_2) {
                    image = Images.imgWall_2;
                } else if (ground[row][col] == FieldType.WALL_3) {
                    image = Images.imgWall_3;
                }

                if (image !=null) {
                    DrawUtil.drawImage(
                            graphics, image, 0,
                            col * Context.blockSize + Context.blockSize / 2,
                            row * Context.blockSize + Context.blockSize / 2,
                            Context.blockSize);
                }
            }
        }
    }

    private void clear() {
        graphics.setColor(BG_COLOR);
        graphics.fillRect(0,0, image.getWidth(), image.getHeight());
    }

    private void drawBooms() {
        battleField.getBoomMap().values().forEach(this::drawBoom);
    }

    private void drawBullets() {
        battleField.getBulletMap().values().forEach(this::drawBullet);
    }

    private void drawTanks() {
        battleField.getTankMap().values().forEach(this::drawTank);
    }

    private void drawGridLine() {
        if (Context.debug) {
            graphics.setColor(Color.LIGHT_GRAY);
            for (int i = 0; i < Context.D_WIDTH; i += Context.blockSize) {
                graphics.drawLine(i, 0, i, Context.D_HEIGHT);
            }
            for (int i = 0; i < Context.D_HEIGHT; i += Context.blockSize) {
                graphics.drawLine(0, i, Context.D_WIDTH, i);
            }
        }
    }
    private void drawBoom(Boom boom) {
        DrawUtil.drawImage(
                graphics,
                Images.imgBoomArr[boom.getStatus() % Images.imgBoomArr.length],
                boom.getDirection(), boom.getX(), boom.getY(), Context.blockSize);
    }

    private void drawBullet(Bullet bullet) {
        DrawUtil.drawImage(graphics, Images.imgBullet, bullet.getDirection(), bullet.getX(), bullet.getY(), Context.blockSize);
    }

    private void drawTank(Tank tank) {
        Image tankImage;

        int slow = 3;

        if (tank.isBot()) {
            int index = (tank.getMoveStatus() / slow) % (Images.imgTankEmyArr.length);
            tankImage = Images.imgTankEmyArr[index];
        } else {
            int index = (tank.getMoveStatus() / slow) % (Images.imgTankPlaArr.length);
            tankImage = Images.imgTankPlaArr[index];
        }
        DrawUtil.drawImage(graphics, tankImage, tank.getDirection(), tank.getX(), tank.getY(), Context.blockSize);
    }
}

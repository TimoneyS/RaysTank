package com.rays.tank.view;

import com.rays.tank.common.Context;
import com.rays.tank.model.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class BattleFieldDrawer {
    private BufferedImage image = new BufferedImage(Context.D_WIDTH, Context.D_HEIGTH, BufferedImage.TYPE_INT_RGB);
    private Graphics graphics = image.createGraphics();
    private BattleField battleField;
    private Color BG_COLOR = Color.DARK_GRAY;

    public static void drawImage(Graphics graphics, Image image, int direction, int x, int y, int blockSize) {
        Graphics2D g2 = (Graphics2D) graphics;
        AffineTransform affineTransform = g2.getTransform();
        g2.rotate(Math.PI * 2 * direction * 90 / 360, x, y);
        graphics.drawImage(image, x - blockSize / 2, y - blockSize / 2, blockSize, blockSize, null);
        g2.setTransform(affineTransform);
    }

    public Image getImage(BattleField battleField) {
        this.battleField = battleField;
        Color c = graphics.getColor();
        clear();
        drawGridLine();
        drawTanks();
        drawBullets();
        drawBooms();
        drawGround();
        graphics.setColor(c);
        return image;
    }

    private void drawGround() {
        int[][] ground = battleField.getGround();
        for (int row = 0; row < ground.length; row ++) {
            for (int col = 0; col < ground[row].length; col ++) {
                if (ground[row][col] == 1) {
                    drawImage(
                            graphics, Images.imgWall, 0,
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
                graphics.drawLine(i, 0, i, Context.D_HEIGTH);
            }
            for (int i = 0; i < Context.D_HEIGTH; i += Context.blockSize) {
                graphics.drawLine(0, i, Context.D_WIDTH, i);
            }
        }
    }
    private void drawBoom(Boom boom) {
        BattleFieldDrawer.drawImage(
                graphics,
                Images.imgBoomArr[boom.getStatus() % Images.imgBoomArr.length],
                boom.getDirection(), boom.getX(), boom.getY(), Context.blockSize);
    }

    private void drawBullet(Bullet bullet) {
        BattleFieldDrawer.drawImage(graphics, Images.imgBullet, bullet.getDirection(), bullet.getX(), bullet.getY(), Context.blockSize);
    }

    private void drawTank(Tank tank) {
        Image tankImage;
        if (tank.isBot()) {
            tankImage = Images.imgTankEmyArr[tank.getMoveStatus() % Images.imgTankEmyArr.length];
        } else {
            tankImage = Images.imgTankPlaArr[tank.getMoveStatus() % Images.imgTankEmyArr.length];
        }
        BattleFieldDrawer.drawImage(graphics, tankImage, tank.getDirection(), tank.getX(), tank.getY(), Context.blockSize);
    }
}

package com.rays.tank.model;

import com.rays.tank.common.Context;
import com.rays.tank.view.DrawUtil;
import com.rays.tank.view.Images;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class BattleField {
    private BufferedImage image = new BufferedImage(Context.D_WIDTH, Context.D_HEIGTH, BufferedImage.TYPE_INT_RGB);
    private Graphics graphics = image.createGraphics();
    private Map<Integer, Tank> tankMap = new HashMap<>();
    private Map<Integer, Bullet> bulletMap = new HashMap<>();
    private Map<Integer, Boom>   boomMap = new HashMap<>();

    public BattleField(InputStream inputStream) {
        BattleFieldLoader.load(inputStream, this);
    }

    public Map<Integer, Tank> getTankMap() {
        return tankMap;
    }

    public Map<Integer, Bullet> getBulletMap() {
        return bulletMap;
    }

    public Map<Integer, Boom> getBoomMap() {
        return boomMap;
    }

    public synchronized Image getImage() {
        Color c = graphics.getColor();
        graphics.fillRect(0,0, image.getWidth(), image.getHeight());
        drawTanks();
        drawBullets();
        drawGridLine();
        drawBooms();
        graphics.setColor(c);
        return image;
    }

    private void drawBooms() {
        for (Boom bullet : boomMap.values()) {
            drawBoom(bullet);
        }
    }

    private void drawBullets() {
        for (Bullet bullet : bulletMap.values()) {
            drawBullet(bullet);
        }
    }

    private void drawTanks() {
        for (Tank tank : tankMap.values()) {
            drawTank(tank);
        }
    }

    private void drawGridLine() {
        if (Context.debug) {
            graphics.setColor(Color.BLACK);
            for (int i = 0; i < Context.D_WIDTH; i += Context.blockSize) {
                graphics.drawLine(i, 0, i, Context.D_HEIGTH);
            }
            for (int i = 0; i < Context.D_HEIGTH; i += Context.blockSize) {
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
        if (tank.isBot()) {
            tankImage = Images.imgTankEmyArr[tank.getMoveStatus() % Images.imgTankEmyArr.length];
        } else {
            tankImage = Images.imgTankPlaArr[tank.getMoveStatus() % Images.imgTankEmyArr.length];
        }
        DrawUtil.drawImage(graphics, tankImage, tank.getDirection(), tank.getX(), tank.getY(), Context.blockSize);
    }

    public void update() {



    }
}
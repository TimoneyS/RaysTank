package com.rays.tank.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {
    private static ClassLoader classLoader = Images.class.getClassLoader();
    public static Image[] imgTankPlaArr;
    public static Image[] imgTankEmyArr;
    public static Image[] imgBoomArr;
    public static Image   imgBullet;
    public static Image   imgWall;

    static {
        try {
            imgBullet = loadImage("img/bullet.png");
            imgWall   = loadImage("img/wall.png");
            imgTankPlaArr = new Image[5];
            for (int i = 0; i < imgTankPlaArr.length; i++) {
                String name = "img/tank_pla_" + (i + 1) + ".png";
                imgTankPlaArr[i] = loadImage(name);
            }
            imgTankEmyArr = new Image[5];
            for (int i = 0; i < imgTankEmyArr.length; i++) {
                String name = "img/tank_emy_" + (i + 1) + ".png";
                imgTankEmyArr[i] = loadImage(name);
            }
            imgBoomArr = new Image[5];
            for (int i = 0; i < imgBoomArr.length; i++) {
                String name = "img/boom" + (i + 1) + ".png";
                imgBoomArr[i] = loadImage(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage loadImage(String name) throws IOException {
        return ImageIO.read(classLoader.getResourceAsStream(name));
    }
}

package com.rays.tank.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {
    private static ClassLoader classLoader = Images.class.getClassLoader();
    public static Image[] imgTankPlaArr;
    public static Image[] imgTankEmyArr;
    public static Image   imgBullet;

    static {
        try {
            imgBullet = loadImage("img/bullet.png");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage loadImage(String name) throws IOException {
        return ImageIO.read(classLoader.getResourceAsStream(name));
    }
}

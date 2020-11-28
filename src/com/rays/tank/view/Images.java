package com.rays.tank.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Images {
    public static Image[] imgTankPlaArr;
    public static Image[] imgTankEmyArr;

    static {
        ClassLoader classLoader = Images.class.getClassLoader();
        try {
            imgTankPlaArr = new Image[6];
            for (int i = 0; i < 6; i++) {
                String name = "img/tank_pla_" + (i + 1) + ".png";
                imgTankPlaArr[i] = ImageIO.read(classLoader.getResourceAsStream(name));;
            }
            imgTankEmyArr = new Image[6];
            for (int i = 0; i < 6; i++) {
                String name = "img/tank_emy_" + (i + 1) + ".png";
                imgTankEmyArr[i] = ImageIO.read(classLoader.getResourceAsStream(name));;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

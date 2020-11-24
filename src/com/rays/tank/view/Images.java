package com.rays.tank.view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Images {

    public static Image imgTankPla;

    static {
        try {
            imgTankPla = ImageIO.read(new File(
                    Images.class.getClassLoader().getResource("tank_pla.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


    }
}

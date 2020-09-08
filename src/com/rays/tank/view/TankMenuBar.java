package com.rays.tank.view;

import javax.swing.*;

public class TankMenuBar extends JMenuBar {

    public TankMenuBar() {

        JMenu fileMenu = new JMenu("File");
        JMenuItem open = new JMenuItem("open");
        JMenuItem save = new JMenuItem("save");
        fileMenu.add(open);
        fileMenu.add(save);

        add(fileMenu);
    }
}

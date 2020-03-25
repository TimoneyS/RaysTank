package com.rays.tank.main;

import com.rays.tank.model.Tank;

import java.awt.*;
import java.util.*;
import java.util.List;

public class BattleField {
    List<Tank> tankList;

    public BattleField() {
        tankList = new ArrayList<>();
        Tank tt = new Tank();
        tt.setX(200);
        tt.setY(200);
        tt.setSize(10);
        tankList.add(tt);
    }

    public List<Tank> getTankList() {
        return tankList;
    }

    public void setTankList(List<Tank> tankList) {
        this.tankList = tankList;
    }
}
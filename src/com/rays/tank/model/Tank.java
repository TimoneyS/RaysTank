package com.rays.tank.model;


import java.awt.*;

public class Tank extends BaseObject {

    private int direction;
    private int weelState;
    private Color color;

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getWeelState() {
        return weelState;
    }

    public void setWeelState(int weelState) {
        this.weelState = weelState;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void move() {
        weelState ++;
        weelState %= 10;
    }
}

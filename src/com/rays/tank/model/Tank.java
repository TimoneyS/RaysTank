package com.rays.tank.model;


import java.awt.*;

public class Tank extends BaseObject {

    private double direction;
    private double weelState;
    private Color color;

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public double getWeelState() {
        return weelState;
    }

    public void setWeelState(double weelState) {
        this.weelState = weelState;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

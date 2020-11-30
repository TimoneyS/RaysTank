package com.rays.tank.model;

public class Boom extends BaseObject {
    int age = 0;
    int status = 0;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void update() {
        if (age / 2 > 4) {
            destroy();
        } else {
            status = age / 2;
            age ++;
        }
    }
}

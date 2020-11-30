package com.rays.tank.model;

public class Boom extends BaseObject {
    int status = 0;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void update() {
        if (status < 4) {
            status ++;
        } else {
            destroy();
        }
    }
}

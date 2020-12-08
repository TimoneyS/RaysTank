package com.rays.tank.model;

import java.util.HashSet;
import java.util.Set;

public class Grid {
    private int type;
    private Set<Integer> coveredObj = new HashSet<>();

    public void removeCovered(int id) {
        coveredObj.remove(id);
    }

    public void addCovered(int id) {
        coveredObj.add(id);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Set<Integer> getCoveredObj() {
        return coveredObj;
    }

    public void setCoveredObj(Set<Integer> coveredObj) {
        this.coveredObj = coveredObj;
    }
}

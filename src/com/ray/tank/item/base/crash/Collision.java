package com.ray.tank.item.base.crash;

import java.util.List;

import com.ray.tank.item.base.HasLife;
import com.ray.tank.item.base.Location;

/**
 * Åö×²¼ì²â½Ó¿Ú
 * @author rays1
 *
 */
public interface Collision extends HasLife {
    
    static final int BULLET      = 1;
    static final int TANK        = 3;
    static final int BOT_TANK    = 4;
    static final int PLAYER_TANK = 5;
    /**
     * Åö×²¼ì²â
     * @param target
     * @return
     */
    public boolean isCrash(Collision target);
    
    /**
     * ´¦ÀíÅö×²
     * @return
     */
    public void doCrash(Collision target);

    /**
     * Åö×²°ë¾¶
     * @return
     */
    public double getRadius();

    /**
     * ×ø±ê
     * @return
     */
    public Location getLocation();

    
    boolean crash(Collision collosional);
    void crash(List<? extends Collision> list);
    
    public int getType();
    
    public int getCollosionalType();
    
    public int getGroup();
    
}
package com.rays.tank.model.base.crash;

import java.util.List;

import com.rays.tank.model.base.HasLife;
import com.rays.tank.model.base.Location;

/**
 * ��ײ���ӿ�
 * @author rays1
 *
 */
public interface Collision extends HasLife {
    
    static final int BULLET      = 1;
    static final int TANK        = 3;
    static final int BOT_TANK    = 4;
    static final int PLAYER_TANK = 5;
    /**
     * ��ײ���
     * @param target
     * @return
     */
    public boolean isCrash(Collision target);
    
    /**
     * ������ײ
     * @return
     */
    public void doCrash(Collision target);

    /**
     * ��ײ�뾶
     * @return
     */
    public double getRadius();

    /**
     * ����
     * @return
     */
    public Location getLocation();

    
    boolean crash(Collision collosional);
    void crash(List<? extends Collision> list);
    
    public int getType();
    
    public int getCollosionalType();
    
    public int getGroup();
    
}
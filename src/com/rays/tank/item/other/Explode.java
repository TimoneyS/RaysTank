package com.rays.tank.item.other;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import com.rays.tank.common.Const;
import com.rays.tank.item.base.BaseItem;
import com.rays.tank.item.base.Location;

public class Explode extends BaseItem {
    
    /**
     * 依次表示各阶段的颜色
     */
    private static Color[] STATE_COLORS = {
            Color.CYAN,
            Color.WHITE,
            Color.YELLOW,
            Color.ORANGE,
            Color.RED,
    };
    private static int     radius         = Const.MaxExplodRadius; // 爆炸持续帧数
    private static int     EXPLOADE_SPEED = Const.explodSpeed;

    private double         size;
    private int            state;                                  // 状态，描述动画进行到何种程度的标志

    private Explode(Location location) {
        this.location = location.clone();
        state = 0;
        size = Const.size;
    }
    
    public static Explode get(Location location) {
        return new Explode(location);
    }
    
    // 绘制
    public void draw(Graphics2D g2) {
        int stateIdx = state / EXPLOADE_SPEED;
        if (stateIdx >= STATE_COLORS.length) stateIdx = STATE_COLORS.length-1;      
        
        g2.setColor(STATE_COLORS[stateIdx]);
        Ellipse2D s = new Ellipse2D.Double(                             //建立形状
                location.X()-state*size/5, location.Y()-state*size/5, state*size/2.5, state*size/2.5);
        g2.fill(s);                                                     //填充形状
    }
    
    // 更新，将状态递进
    public void update() {
        state += EXPLOADE_SPEED;
        if(state >= radius) lifeEnd();
    }

}
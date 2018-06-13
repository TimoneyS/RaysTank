package com.ray.tank.item.tank;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import com.ray.tank.common.DrawUtil;
import com.ray.tank.config.Const;
import com.ray.tank.item.base.CollisionalItem;
import com.ray.tank.item.base.Location;
import com.ray.tank.item.base.crash.Collision;
import com.ray.tank.item.base.crash.CollisionalSupport;
import com.ray.tank.item.base.move.MoveSupport;
import com.ray.tank.item.other.BattleField;

public abstract class Tank extends CollisionalItem {

    protected double      direction;
    protected double      weelState;    // 履带动态效果
    protected int         score;        // 得分
    protected int         group;
    protected Color       color;        // 颜色
    protected Collision   tankCollision;
    protected TankMove    tankMove;
    
    public abstract void fire();
    public abstract int getType();
    
    public Tank(double x, double y, double direction,Color teamColor, int group) {
        location = new Location(x, y);
        this.direction = direction;
        this.color = teamColor;
        this.group = group;
        alive     = true;
        tankCollision = new TankCollision();
        tankMove      = new TankMove();
        weelState = 0;
    }

    @Override
    public void draw(Graphics2D g2){                        //绘制
        AffineTransform odlTramsform = g2.getTransform();   //旋转参数备份
        
        double x = location.X();
        double y = location.Y();
        
        double size = Const.size;
        double rad = direction;
        
        // 名称和得分
        g2.drawString("tank kill " + getScore(), (int)x, (int)y);
        
        g2.rotate(rad, x, y);                               //设置旋转的角度和中心点
        
        //主舱室
        RoundRectangle2D rbody = new  RoundRectangle2D.Double(
                 x-3*size, y-3*size, 6*size, 6*size, 1*size, 1*size);
        DrawUtil.drawShape(g2, color, rbody);
        
        // 轮子
        Rectangle2D rweell = new Rectangle2D.Double(x-5*size, y-5*size, 2*size, 10*size);
        Rectangle2D rweelr = new Rectangle2D.Double(x+3*size, y-5*size, 2*size, 10*size);
        DrawUtil.drawShape(g2, Color.LIGHT_GRAY, rweelr);
        DrawUtil.drawShape(g2, Color.LIGHT_GRAY, rweell);
        
        // 履带
        double y2;
        for(int i = 0; i < 10; i += 2) {
            // y2 = y + size*(weelState/10 + 2*i);
            y2 = y + size*i + weelState/5*size - 3 * size;
            g2.draw(new Line2D.Double(x-5*size, y2, x-3*size, y2));
            g2.draw(new Line2D.Double(x+5*size, y2, x+3*size, y2));
        }
        
        // 炮管
        g2.setColor(Color.DARK_GRAY);
        Rectangle2D rfire = new Rectangle2D.Double(x-0.4*size, y-8*size, 1*size, 8*size);
        g2.fill(rfire);
        
        // 炮台
        g2.setColor(Color.DARK_GRAY);
        Ellipse2D rfilePlat = new Ellipse2D.Double(x-1.5*size, y-1.5*size, 3*size, 3*size);
        
        g2.fill(rfilePlat);
        
        g2.setTransform(odlTramsform); //恢复备份的旋转参数
    }
    
    /**
     * 坦克移动
     * @param moveSpeed
     */
    public void move() {
        moveStateChange();                                          // 履带变化
        location.save();
        tankMove.move();
    }
    
    public void scoreAdd(int point) {
        score += point;
    }
    
    public int getScore() {
        return score;
    }
    
    public void moveStateChange() {
        weelState -= 1;
        weelState = weelState%10;
    }
    
    public Collision getCollision() {
        return tankCollision;
    }
    
    public int getGroup() {
        return group;
    }
    
    protected class TankMove extends MoveSupport {

        @Override
        public double getDirection() {
            return direction;
        }

        @Override
        public Location getLocation() {
            return location;
        }

        @Override
        public BattleField getBattfield() {
            return battleField;
        }
        
    }
    
    // 碰撞处理
    protected class TankCollision extends CollisionalSupport {

        @Override
        public void doCrash(Collision target) {
            if (target.getType() == Collision.BULLET)
                lifeEnd();
            else
                location.load();
        }

        @Override
        public double getRadius() {
            return Const.size*5;
        }

        @Override
        public Location getLocation() {
            return location;
        }

        @Override
        public int getType() {
            return Tank.this.getType();
        }

        @Override
        public int getCollosionalType() {
            return 0;
        }

        @Override
        public boolean isAlive() {
            return alive;
        }

        @Override
        public int getGroup() {
            return group;
        }
        
    }
    
}

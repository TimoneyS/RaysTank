package com.ray.tank.item.tank;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import com.ray.tank.common.Const;
import com.ray.tank.common.DrawUtil;
import com.ray.tank.item.base.CollisionalItem;
import com.ray.tank.item.base.Location;
import com.ray.tank.item.base.crash.Collision;
import com.ray.tank.item.base.crash.CollisionalSupport;
import com.ray.tank.item.base.move.MoveSupport;

public abstract class Tank extends CollisionalItem {

    protected double      direction;
    protected double      weelState;    // �Ĵ���̬Ч��
    protected int         score;        // �÷�
    protected int         group;
    protected Color       color;        // ��ɫ
    protected Collision   tankCollision;
    protected TankMove    tankMove;
    
    public abstract void fire();
    public abstract int getType();
    
    public Tank(double x, double y, double direction,Color teamColor, int group) {
        location = new Location(x, y);
        this.direction = direction;
        this.color = teamColor;
        this.group = group;
        tankCollision = new TankCollision();
        tankMove      = new TankMove();
        weelState = 0;
    }

    @Override
    public void draw(Graphics2D g2){                        //����
        AffineTransform odlTramsform = g2.getTransform();   //��ת��������
        
        double x = location.X();
        double y = location.Y();
        
        double size = Const.size;
        double rad = direction;
        
        // ���ƺ͵÷�
        g2.drawString("tank kill " + getScore(), (int)x, (int)y);
        
        g2.rotate(rad, x, y);                               //������ת�ĽǶȺ����ĵ�
        
        //������
        RoundRectangle2D rbody = new  RoundRectangle2D.Double(
                 x-3*size, y-3*size, 6*size, 6*size, 1*size, 1*size);
        DrawUtil.drawShape(g2, color, rbody);
        
        // ����
        Rectangle2D rweell = new Rectangle2D.Double(x-5*size, y-5*size, 2*size, 10*size);
        Rectangle2D rweelr = new Rectangle2D.Double(x+3*size, y-5*size, 2*size, 10*size);
        DrawUtil.drawShape(g2, Color.LIGHT_GRAY, rweelr);
        DrawUtil.drawShape(g2, Color.LIGHT_GRAY, rweell);
        
        // �Ĵ�
        double y2;
        for(int i = 0; i < 10; i += 2) {
            // y2 = y + size*(weelState/10 + 2*i);
            y2 = y + size*i + weelState/5*size - 3 * size;
            g2.draw(new Line2D.Double(x-5*size, y2, x-3*size, y2));
            g2.draw(new Line2D.Double(x+5*size, y2, x+3*size, y2));
        }
        
        // �ڹ�
        g2.setColor(Color.DARK_GRAY);
        Rectangle2D rfire = new Rectangle2D.Double(x-0.4*size, y-8*size, 1*size, 8*size);
        g2.fill(rfire);
        
        // ��̨
        g2.setColor(Color.DARK_GRAY);
        Ellipse2D rfilePlat = new Ellipse2D.Double(x-1.5*size, y-1.5*size, 3*size, 3*size);
        
        g2.fill(rfilePlat);
        
        g2.setTransform(odlTramsform); //�ָ����ݵ���ת����
    }
    
    /**
     * ̹���ƶ�
     * @param moveSpeed
     */
    public void move() {
        moveStateChange();                                          // �Ĵ��仯
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
    
    @Override
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

    }
    
    // ��ײ����
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

package com.ray.tank.item.base;

import java.awt.event.KeyEvent;

/**
 * ��������
 * @author rays1
 *
 */
public interface Listenable {
    
    /**
     * �������¼���
     * @param e
     */
	public void keyPress(KeyEvent e);
	
	/**
	 * �����������
	 * @param e
	 */
	public void keyReleased(KeyEvent e);
	
}

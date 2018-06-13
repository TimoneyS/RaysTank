package com.ray.tank.item.base;

import java.awt.event.KeyEvent;

/**
 * 按键监听
 * @author rays1
 *
 */
public interface Listenable {
    
    /**
     * 按键按下监听
     * @param e
     */
	public void keyPress(KeyEvent e);
	
	/**
	 * 按键弹起监听
	 * @param e
	 */
	public void keyReleased(KeyEvent e);
	
}

package com.ray.tank.main;

import com.ray.tank.common.Const;
import com.ray.tank.item.tank.PlaTank;
import com.ray.tank.view.TankFrame;

public class Game {
	public static void main(String[] args) {
		new TankFrame().lunch();
		
		PlaTank playerTank = new PlaTank(400, 300, 0, Const.playerColor, 1);
		
	}
}

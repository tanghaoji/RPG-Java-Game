package com.fantasyfly.rain.entity.mob;

import java.util.ArrayList;
import java.util.List;

import com.fantasyfly.rain.entity.Entity;
import com.fantasyfly.rain.entity.projectile.Projectile;
import com.fantasyfly.rain.entity.projectile.WizardProjectile;
import com.fantasyfly.rain.graphics.Sprite;

public abstract class Mob extends Entity { //anything moving

	protected Sprite sprite; // protected only available in Mob and its subclass
	protected int dir = 2; // 0 is North
	protected boolean moving = false;
	protected boolean walking = false;
		
	public void move(int xa, int ya){ // to control how pixels are translated, xa, ya how it moves
		// -1, 0, 1
		if (xa != 0 && ya != 0) { // move on both axes
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;
		
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}	
	}
	
	public void update() {
	}
	
	protected void shoot(int x, int y, double dir) {
		//dir = Math.toDegrees(dir);
		Projectile p = new WizardProjectile(x, y, dir);
		level.addProjectile(p);
	}
	
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
	
	public void render() {
	}
}

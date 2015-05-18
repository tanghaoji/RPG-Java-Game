package com.fantasyfly.rain.entity.projectile;

import com.fantasyfly.rain.graphics.Screen;
import com.fantasyfly.rain.graphics.Sprite;

public class WizardProjectile extends Projectile {

	public static final int FIRE_RATE = 10; // the time b/w the projectiles, higher is slower
	
	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = random.nextInt(50) + 100;
		speed = 2;
		damage = 20;
		sprite = Sprite.projectile_wizard;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}
	
	public void update() {
		if (level.tileCollision(x, y, nx, ny, 7)) remove();
		move();
	}
	
	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) remove();
	}
	
	private double distance() {
		double dist = Math.sqrt((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y));
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x - 6, (int) y - 6, this);
	}

}

package com.fantasyfly.rain.entity;

import java.util.Random;

import com.fantasyfly.rain.graphics.Screen;
import com.fantasyfly.rain.level.Level;

public abstract class Entity { // abstract class means it's a template
	
	public int x, y; // location of a particular entity
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update() {
	}
	
	public void render(Screen screen) {
	}
	
	public void remove() {
		// remove from level
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}

}

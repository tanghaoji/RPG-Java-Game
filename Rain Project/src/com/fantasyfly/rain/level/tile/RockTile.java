package com.fantasyfly.rain.level.tile;

import com.fantasyfly.rain.graphics.Screen;
import com.fantasyfly.rain.graphics.Sprite;

public class RockTile extends Tile {

	public RockTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) { // x, y is tile coordinates, not in pixels
		screen.renderTile(x << 4, y << 4, this.sprite);
	}
	
	public boolean solid() { //need to be override, otherwise it's false
		return true;
	}

}

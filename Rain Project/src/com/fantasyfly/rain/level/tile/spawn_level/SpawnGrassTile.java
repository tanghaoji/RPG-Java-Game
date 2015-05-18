package com.fantasyfly.rain.level.tile.spawn_level;

import com.fantasyfly.rain.graphics.Screen;
import com.fantasyfly.rain.graphics.Sprite;
import com.fantasyfly.rain.level.tile.Tile;

public class SpawnGrassTile extends Tile {

	public SpawnGrassTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) { // x, y is tile coordinates, not in pixels
		screen.renderTile(x << 4, y << 4, this.sprite);
	}

}

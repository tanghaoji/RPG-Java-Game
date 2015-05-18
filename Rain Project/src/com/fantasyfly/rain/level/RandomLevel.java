package com.fantasyfly.rain.level;

import java.util.Random;

public class RandomLevel extends Level {

	private static final Random random = new Random();

	public RandomLevel(int width, int height) {
		super(width, height); // to the Level (super class constructor)

	}

	protected void generateLevel() { //override
		for (int y = 0; y < height; y++) { // the way the loop is set up is for advance generate
			for (int x = 0; x < width; x++) {
				tilesInt[x + y * width] = random.nextInt(4); // 0 1 2 3 random tile id, tile array, creates the whole map with random tiles
			}
		}
	}

}

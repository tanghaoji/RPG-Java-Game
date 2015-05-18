package com.fantasyfly.rain.graphics;

public class Sprite {
	
	public final int SIZE; // we want a universal class to handle every size of sprite in the game
	private int x,y; // coordinates
	private int width, height; // to make sure it doesn't mutate outside
	public int[] pixels;
	private SpriteSheet sheet;
	
	// create a new static instance of Sprite class
	// every instance of Sprite class has unique non-static variable
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite voidSprite = new Sprite(16, 0); // black color
	
	//Spawn Level Sprites here:
	public static Sprite spawn_grass = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_hedge = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_water = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
	public static Sprite spawn_floor = new Sprite(16, 1, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_wall1 = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
	public static Sprite spawn_wall2 = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
	
	//Player Sprites here:
	public static Sprite player_forward = new Sprite(32, 0, 7, SpriteSheet.tiles);
	public static Sprite player_back = new Sprite(32, 0, 4, SpriteSheet.tiles);
	public static Sprite player_left = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_right = new Sprite(32, 0, 6, SpriteSheet.tiles);
	
	public static Sprite player_forward_1 = new Sprite(32, 1, 7, SpriteSheet.tiles);
	public static Sprite player_forward_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);
	public static Sprite player_back_1 = new Sprite(32, 1, 4, SpriteSheet.tiles);
	public static Sprite player_back_2 = new Sprite(32, 2, 4, SpriteSheet.tiles);
	public static Sprite player_left_1 = new Sprite(32, 1, 5, SpriteSheet.tiles);
	public static Sprite player_left_2 = new Sprite(32, 2, 5, SpriteSheet.tiles);
	public static Sprite player_right_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_right_2 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	
	//Projectile Sprites here:
	public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) { // x, y is columns and rows
		this.SIZE =size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int width, int height, int colour) { // rectangular sprite
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColour(colour);
	}
	
	public Sprite(int size, int colour) {
		this.SIZE = size;
		this.height =size;
		this.width = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);
	}
	
	private void setColour(int colour) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = colour;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}

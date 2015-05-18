package com.fantasyfly.rain.graphics;

import java.util.Random;

import com.fantasyfly.rain.entity.projectile.Projectile;

public class Screen { // Screen class draws pixels onto the screen
	
	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;
	
	public int xOffset, yOffset;
	
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	
	private Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i ++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}
	
	public void renderTile(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < sprite.SIZE; y++) { // 0 <= y <= 15
			int ya = y + yp; // ya: absolute y position of a pixel on the sprite, yp: y offset (set the screen position)
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break; // only display what you can see on the screen
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x + y * sprite.SIZE];
				pixels[xa + ya * width] = col; // xa -> screen, x -> sprite
			}
		}
	}
	
	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) { // 0 <= y <= 15
			int ya = y + yp; // ya: absolute y position of a pixel on the sprite, yp: y offset (set the screen position)
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break; // only display what you can see on the screen
				if (xa < 0) xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSprite().SIZE];
				if (col != 0x00FFFFFF) {
					pixels[xa + ya * width] = col; // xa -> screen, x -> sprite
				}
			}
		}
	}
	
	public void renderPlayer(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) { // 0 <= y <= 15
			int ya = y + yp; // ya: absolute y position of a pixel on the sprite, yp: y offset (set the screen position)
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break; // only display what you can see on the screen
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x + y * 32];
				if (col != 0x00000000) pixels[xa + ya * width] = col; // xa -> screen, x -> sprite
			}
		}
	}
	
	public void setOffset(int xOffest, int yOffset) {
		this.xOffset = xOffest;
		this.yOffset = yOffset;
	}

}

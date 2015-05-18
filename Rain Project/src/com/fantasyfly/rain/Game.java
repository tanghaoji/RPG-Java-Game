package com.fantasyfly.rain;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.fantasyfly.rain.entity.mob.Player;
import com.fantasyfly.rain.graphics.Screen;
import com.fantasyfly.rain.graphics.Sprite;
import com.fantasyfly.rain.input.Keyboard;
import com.fantasyfly.rain.input.Mouse;
import com.fantasyfly.rain.level.Level;
import com.fantasyfly.rain.level.TileCoordinate;

public class Game extends Canvas implements Runnable { // Runnable: to run the thread Canvas: for drawing
	private static final long serialVersionUID = 1L;

	private static int width = 300;
	private static int height = width / 16 * 9;
	private static int scale = 3;
	public static String title = "Rain";

	private Thread thread; // process, do multiple things simultaneously
	private JFrame frame; // window
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;
	
	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // create an image
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); // convert image into integer array and access to the image

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale); // create Dimension object
		setPreferredSize(size); // set size

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn; // setup a level(map) in tile coordinates
		TileCoordinate playerSpawn = new TileCoordinate(13, 19);
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		player.init(level);
		
		addKeyListener(key);
		
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	public static int getWindowWidth() {
		return width * scale;
	}
	
	public static int getWindowHeight() {
		return height * scale;
	}

	public synchronized void start() { // prevent memory consistency errors, no overlaps
		running = true;
		thread = new Thread(this, "Display"); // the new thread will contain this Game class, attached to this game object
		thread.start(); // automatically go to the run method
	}

	public synchronized void stop() { // stops a thread, shut down the Game properly
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		requestFocus(); //get focus to the screen
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		while (running) { // game loop
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1){
				update();
				updates++;
				delta--;
			}
			
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) { //do this once per second, fps counter
				timer += 1000;
				//System.out.println(updates + "ticks, " + frames + " fps");
				frame.setTitle(title + "  |  " + updates + "ticks, " + frames + " fps");
				updates = 0;
				frames = 0;
				
			}
		}
		stop();
	}
	

	public void update() {
		key.update();
		player.update();
		level.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy(); // canvas
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		player.render(screen);
		
		Sprite sprite = new Sprite(60, height, 0xff);
		//screen.renderSprite(width - 60, 0, sprite, false);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		/////////
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		/////////
		g.dispose(); // remove graphics
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game(); // since main is static
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game); // add game component to frame, fill window with game, since game is subclass of canvas
		game.frame.pack(); // pack the size of the frame to the size of game
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}

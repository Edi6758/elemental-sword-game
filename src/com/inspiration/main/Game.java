package com.inspiration.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.inspiration.entities.BulletShoot;
import com.inspiration.entities.Entity;
import com.inspiration.entities.Player;
import com.inspiration.entities.Weapon;
import com.inspiration.entities.enemy.Enemy;
import com.inspiration.entities.enemy.Enemy10;
import com.inspiration.entities.enemy.Enemy2;
import com.inspiration.entities.enemy.Enemy3;
import com.inspiration.entities.enemy.Enemy4;
import com.inspiration.entities.enemy.Enemy5;
import com.inspiration.entities.enemy.Enemy6;
import com.inspiration.entities.enemy.Enemy7;
import com.inspiration.entities.enemy.Enemy8;
import com.inspiration.entities.enemy.Enemy9;
import com.inspiration.graficos.Spritesheet;
import com.inspiration.graficos.UI;
import com.inspiration.world.Camera;
import com.inspiration.world.World;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener {

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	public static final int SCALE = 3;

	public static int CUR_LEVEL = 1, MAX_LEVEL = 3;
	public BufferedImage image;

	public static List<Entity> entities;


	public static List<BulletShoot> bullets;
	
	public static List<Weapon> weapons;

	//Lista de inimigos
	public static List<Enemy> enemies;
	public static List<Enemy2> enemies2;
	public static List<Enemy3> enemies3;
	public static List<Enemy4> enemies4;
	public static List<Enemy5> enemies5;
	public static List<Enemy6> enemies6;
	public static List<Enemy7> enemies7;
	public static List<Enemy8> enemies8;
	public static List<Enemy9> enemies9;
	public static List<Enemy10> enemies10;
	
	public static Spritesheet spritesheet;

	public static World world;

	public static Player player;

	public static Random rand;

	public UI ui;

	public static String gameState = "NORMAL";
	private boolean showMessageGameOver = true;
	private int framesGameOver = 0;
	private boolean restartGame = false;

	public Menu menu;

	public Game() {
		Sound.musicBackground.loop();
		rand = new Random();
		addKeyListener(this);
		addMouseListener(this);
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		ui = new UI();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		
		//Renderizando inimigos no mundo
		enemies = new ArrayList<Enemy>();
		enemies2 = new ArrayList<Enemy2>();
		enemies3 = new ArrayList<Enemy3>();
		enemies4 = new ArrayList<Enemy4>();
		enemies5 = new ArrayList<Enemy5>();
		enemies6 = new ArrayList<Enemy6>();
		enemies7 = new ArrayList<Enemy7>();
		enemies8 = new ArrayList<Enemy8>();
		enemies9 = new ArrayList<Enemy9>();
		enemies10 = new ArrayList<Enemy10>();

		bullets = new ArrayList<BulletShoot>();
		weapons = new ArrayList<Weapon>();

		//Arquivo de sprites do jogo
		spritesheet = new Spritesheet("/spritesheet.png");
		
		//Player
		player = new Player(0, 0, 16, 16, spritesheet.getSprite(0, 0, 16, 16));
		entities.add(player);
		//Mapa
		world = new World("/level1.png");

		menu = new Menu();
	}

	public void initFrame() {
		frame = new JFrame("Elemental Sword");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		Game game = new Game();
		game.start();
	}

	public void tick() {
		if (gameState == "NORMAL") {
			this.restartGame = false;
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}

			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).tick();
			}

			if (enemies.size() == 0 && enemies2.size() == 0 
					&& enemies3.size() == 0 && enemies6.size() == 0 && enemies7.size() == 0 
					&& enemies8.size() == 0 && enemies9.size() == 0 && enemies10.size() == 0 ) {
				// Proximo level
				CUR_LEVEL++;

				if (CUR_LEVEL > MAX_LEVEL) {
					CUR_LEVEL = 1;
				}
				Sound.levelupEffect.play();
				String newWorld = "level" + CUR_LEVEL + ".png";
				World.restartGame(newWorld);
			}
		} else if (gameState == "GAME_OVER") {
			this.framesGameOver++;
			if (this.framesGameOver == 30) {
				this.framesGameOver = 0;
				if (this.showMessageGameOver)
					this.showMessageGameOver = false;
				else
					this.showMessageGameOver = true;
			}

			if (restartGame) {
				this.restartGame = false;
				this.gameState = "NORMAL";
				CUR_LEVEL = 1;
				String newWorld = "level" + CUR_LEVEL + ".png";
				World.restartGame(newWorld);
			}
		} else if (gameState == "MENU") {
			//
			menu.tick();
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(19, 19, 19));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		world.render(g);
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}

		ui.render(g);

		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.setColor(Color.black);
		g.drawString("Munição: " + player.ammo, 600, 18);
		if (gameState == "GAME_OVER") {
			Sound.musicBackground.stop();;
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(0, 0, 0, 100));
			g2.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);

			g.setFont(new Font("arial", Font.BOLD, 50));
			g.setColor(Color.white);
			g.drawString("Game Over", (Game.WIDTH * SCALE) / 2 - 100, (Game.HEIGHT * SCALE) / 2);
			g.setFont(new Font("arial", Font.BOLD, 30));
			g.setColor(Color.white);
			if (showMessageGameOver)
				g.drawString("Pressione Enter para reiniciar", (Game.WIDTH * SCALE) / 2 - 175,
						(Game.HEIGHT * SCALE) / 2 + 40);
		} else if (gameState == "MENU") {
			menu.render(g);
		}
		bs.show();

	}

	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}

		stop();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = true;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = true;

			if (gameState == "MENU") {
				menu.up = true;
			}

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = true;

			if (gameState == "MENU") {
				menu.down = true;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_X) {
			player.shoot = true;
			Sound.arrowDemageEffect.play();
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			player.right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			player.up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			player.down = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.restartGame = true;
			if (gameState == "MENU") {
				menu.enter = true;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gameState = "MENU";
			menu.pause = true;
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Sound.arrowDemageEffect.play();
		player.mouseShoot = true;
		player.mx = (e.getX() / 3);
		player.my = (e.getY() / 3);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

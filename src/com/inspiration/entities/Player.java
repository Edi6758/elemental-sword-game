package com.inspiration.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.inspiration.graficos.Spritesheet;
import com.inspiration.main.Game;
import com.inspiration.main.Sound;
import com.inspiration.world.Camera;
import com.inspiration.world.World;

public class Player extends Entity {

	public boolean right, up, left, down;
	public int right_dir = 0, left_dir = 1;
	public int dir = right_dir;
	public double speed = 1.4;

	private int frames = 0, maxFrames = 10, index = 0, maxIndex = 3;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;

	private BufferedImage playerDemage;

	public static boolean hasGun = false;
	public static boolean gunNumber1=false, gunNumber2 =false;

	public int ammo = 0;

	public boolean isDemage = false;

	private int DemageFrames = 0;

	public boolean shoot = false, mouseShoot = false;

	public double life = 100, maxLife = 100;
	public int mx, my;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];

		playerDemage = Game.spritesheet.getSprite(4 * 16, 0, 16, 16);

		for (int i = 0; i < 4; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(0 + (i * 16), 0, 16, 16);
		}

		for (int i = 0; i < 4; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(0 + (i * 16), 16, 16, 16);
		}

	}

	public void tick() {
		moved = false;
		if (right && World.isFree((int) (x + speed), this.getY())) {
			moved = true;
			dir = right_dir;
			x += speed;
		} else if (left && World.isFree((int) (x - speed), this.getY())) {
			moved = true;
			dir = left_dir;
			x -= speed;
		}
		if (up && World.isFree(this.getX(), (int) (y - speed))) {
			moved = true;
			y -= speed;
		} else if (down && World.isFree(this.getX(), (int) (y + speed))) {
			moved = true;
			y += speed;
		}

		if (moved) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxIndex) {
					index = 0;
				}
			}
		}

		checkCollisionLifePack();
		checkCollisionAmmo();
		checkCollisionGun();
		checkCollisionGun2();
		
		checkCollisionDemagePack();

		if (isDemage) {
			this.DemageFrames++;
			if (this.DemageFrames == 8) {
				this.DemageFrames = 0;
				isDemage = false;
			}
		}

		if (shoot) {
			shoot = false;
			if (hasGun && ammo > 0) {

				ammo--;
				// Criar bala e atirar

				int dx = 0;
				int px = 0;
				int py = 2;
				if (dir == right_dir) {
					px = 5;
					dx = 1;
				} else {
					px = -5;
					dx = -1;
				}
				BulletShoot bullet = new BulletShoot(this.getX() + px, this.getY() + py, 3, 3, null, dx, 0);
				Game.bullets.add(bullet);
			}
		}

		if (mouseShoot) {

			mouseShoot = false;

			if (hasGun && ammo > 0) {

				ammo--;
				// Criar bala e atirar

				int dx = 0;
				int px = 0;
				int py = 2;
				if (dir == right_dir) {
					px = 5;
					dx = 1;
				} else {
					px = -5;
					dx = -1;
				}
				BulletShoot bullet = new BulletShoot(this.getX() + px, this.getY() + py, 3, 3, null, dx, 0);
				Game.bullets.add(bullet);
			}
		}

		if (life <= 0) {
			Sound.gameoverEffect.play();
			// Game over
			life = 0;
			Game.gameState = "GAME_OVER";
		}

		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH / 2), 0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT / 2), 0, World.HEIGHT * 16 - Game.HEIGHT);

	}

	public void checkCollisionAmmo() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (atual instanceof Bullet) {
				if (Entity.isColidding(this, atual)) {
					ammo += 5;
					Sound.arrowEffect.play();
					Game.entities.remove(atual);
				}
			}
		}
	}
	
	//Colisão com as armas

	public void checkCollisionGun() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (atual instanceof Weapon) {
				if (Entity.isColidding(this, atual)) {
					hasGun = true;
					gunNumber1 = true;
					Sound.bowEffect.play();
					System.out.print("Pegou a arma");
					Game.entities.remove(atual);
				}
			}
		}
	}

	public void checkCollisionGun2() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (atual instanceof Weapon2) {
				if (Entity.isColidding(this, atual)) {
					hasGun = true;
					gunNumber2 = true;
					Sound.bowEffect.play();
					System.out.print("Pegou a arma 2");
					Game.entities.remove(atual);
				}
			}
		}
	}

	public void checkCollisionLifePack() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (atual instanceof Lifepack ) {
				if (Entity.isColidding(this, atual)) {
					life += 10;
					if (life >= 100)
						life = 100;
					Sound.eatingEffect.play();
					Game.entities.remove(atual);
					return;
				}
			}
		}
	}
	
	public void checkCollisionDemagePack() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (atual instanceof Demagepack ) {
				if (Entity.isColidding(this, atual)) {
					life -= 30;
					Sound.demageEffect.play();
					Game.entities.remove(atual);
					return;
				}
			}
		}
	}

	public void render(Graphics g) {
		if (!isDemage) {
			if (dir == right_dir) {
				g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				if (hasGun && gunNumber1) {
					// Desenhar arma pra direita.
					g.drawImage(Entity.GUN_RIGHT, this.getX() - Camera.x + 6, this.getY() - Camera.y + 3, null);
					gunNumber2 = false;
				}else if(hasGun && gunNumber2) {
					g.drawImage(Entity.GUN2_RIGHT, this.getX() - Camera.x + 6, this.getY() - Camera.y + 3, null);
					gunNumber1 = false;
				}
			} else if (dir == left_dir) {
				g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

				if (hasGun && gunNumber1) {
					// Desenhar arma pra direita.
					g.drawImage(Entity.GUN_LEFT, this.getX() - Camera.x - 6, this.getY() - Camera.y + 3, null);
					gunNumber2 = false;
				}else if(hasGun && gunNumber2) {
					g.drawImage(Entity.GUN2_LEFT, this.getX() - Camera.x - 6, this.getY() - Camera.y + 3, null);
					gunNumber1 = false;
				}
			}
		} else {
			g.drawImage(playerDemage, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}

	}

}

package com.inspiration.entities.enemy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.inspiration.entities.BulletShoot;
import com.inspiration.entities.Entity;
import com.inspiration.main.Game;
import com.inspiration.main.Sound;
import com.inspiration.world.Camera;
import com.inspiration.world.World;

public class Enemy3 extends Entity {

	private double speed = 0.6;
	private int frames = 0, maxFrames = 20, index = 0, maxIndex = 1;

	private int maskx = 5, masky = 5, maskw = 64, maskh = 32;

	private BufferedImage[] leftEnemy, rightEnemy;

	public int right_dir = 0, left_dir = 1;
	public int dir = right_dir;

	private int life = 10;

	private boolean isDemage = false;
	private int demageFrames = 10, demageCurrent = 0;

	public Enemy3(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);

		leftEnemy = new BufferedImage[2];
		leftEnemy[0] = Game.spritesheet.getSprite(16*16, 4*16, 64, 32);
		leftEnemy[1] = Game.spritesheet.getSprite(16*16, 6*16, 64, 32);

		rightEnemy = new BufferedImage[2];
		rightEnemy[0] = Game.spritesheet.getSprite(16*16,8*16, 64, 32);
		rightEnemy[1] = Game.spritesheet.getSprite(16*16, 10*16, 64, 32);

	}

	public void tick() {

		if (isColiddingWithPlayer() == false) {
			if ((int) x < Game.player.getX() && World.isFree((int) (x + speed), this.getY())
					&& !isColidding((int) (x + speed), this.getY())) {
				dir = right_dir;
				x += speed;
			} else if ((int) x > Game.player.getX() && World.isFree((int) (x - speed), this.getY())
					&& !isColidding((int) (x - speed), this.getY())) {
				dir = left_dir;
				x -= speed;
			}

			if ((int) y < Game.player.getY() && World.isFree(this.getX(), (int) (y + speed))
					&& !isColidding(this.getX(), (int) (y + speed))) {
				y += speed;

			} else if ((int) y > Game.player.getY() && World.isFree(this.getX(), (int) (y - speed))
					&& !isColidding(this.getX(), (int) (y - speed))) {
				y -= speed;

			}

		} else {
			if (Game.rand.nextInt(100) < 10) {
				Sound.demageEffect.play();
				Game.player.life -= Game.rand.nextInt(80);
				Game.player.isDemage = true;

			}

			System.out.println("Vida " + Game.player.life);
		}
		frames++;
		if (frames == maxFrames) {
			frames = 0;
			index++;
			if (index > maxIndex) {
				index = 0;
			}

		}
		collidingBullet();

		if (life <= 0) {
			destroySelf();
			return;
		}

		if (isDemage) {
			this.demageCurrent++;
			if (this.demageCurrent == this.demageFrames) {
				this.demageCurrent = 0;
				this.isDemage = false;
			}
		}

	}

	public void destroySelf() {
		Game.enemies3.remove(this);
		Game.entities.remove(this);
	}

	public void collidingBullet() {
		for (int i = 0; i < Game.bullets.size(); i++) {
			Entity e = Game.bullets.get(i);
			if (e instanceof BulletShoot) {

				if (Entity.isColidding(this, e)) {
					Sound.hurtEffect.play();
					isDemage = true;
					life -= 4;
					Game.bullets.remove(i);
					return;
				}

			}
		}

	}

	public boolean isColiddingWithPlayer() {
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx, this.getY() + masky, maskw, maskh);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16);

		return enemyCurrent.intersects(player);
	}

	//Colis�o do inimigo com o player
	public boolean isColidding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext + maskx, ynext + masky, maskw, maskh);
		for (int i = 0; i < Game.enemies3.size(); i++) {
			Enemy3 e = Game.enemies3.get(i);
			if (e == this)
				continue;
			Rectangle targetEnemy = new Rectangle(e.getX() + maskx, e.getY() + masky, maskw, maskh);
			if (enemyCurrent.intersects(targetEnemy)) {
				return true;
			}

		}
		return false;
	}

	public void render(Graphics g) {

		if (!isDemage) {
			if (dir == right_dir) {
				g.drawImage(rightEnemy[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			} else if (dir == left_dir) {
				g.drawImage(leftEnemy[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		} else {
			g.drawImage(Entity.ENEMY_FEEDBACK3, this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}

}

package com.inspiration.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.inspiration.main.Game;
import com.inspiration.world.Camera;

public class BulletShoot extends Entity {

	private double dx;
	private double dy;
	private double spd = 2;

	private int life = 50, curlife = 0;

	public int right_dir = 0, left_dir = 1;
	public int dir = right_dir;

	public static BufferedImage bullets;

	public BulletShoot(int x, int y, int width, int height, BufferedImage sprite, double dx, double dy) {
		super(x, y, width, height, sprite);
		this.dx = dx;
		this.dy = dy;
		// TODO Auto-generated constructor stub
		bullets = Game.spritesheet.getSprite(0*16, 9 * 16, 16, 16);;

	}

	public void tick() {
		x += dx * spd;
		x += dy * spd;
		curlife++;
		if (curlife == life) {
			Game.bullets.remove(this);
			return;
		}
	}

	public void render(Graphics g) {

		g.drawImage(bullets, this.getX() - Camera.x, this.getY() - Camera.y, null);

	}

}

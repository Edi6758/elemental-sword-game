package com.inspiration.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.inspiration.main.Game;
import com.inspiration.world.Camera;

public class Entity {

	//Frutas de vida da fazenda(fase 01)
	public static BufferedImage LIFEPACK_EN = Game.spritesheet.getSprite(0*16, 7 * 16, 16, 16);
	public static BufferedImage LIFEPACK2_EN = Game.spritesheet.getSprite(1*16, 7 * 16, 16, 16);
	public static BufferedImage LIFEPACK3_EN = Game.spritesheet.getSprite(2*16, 7 * 16, 16, 16);
	
	//Frutas da moretea(fase 01)
	public static BufferedImage DEMAGEPACK_EN = Game.spritesheet.getSprite(0*16, 10 * 16, 16, 16);
	
	//Munição de madeira pra arco!
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(18 * 16, 16 * 19, 16, 16);
	public static BufferedImage WEAPON2_EN = Game.spritesheet.getSprite(17 * 16, 16 * 19, 16, 16);
	
	//Arco inicial
	public static BufferedImage GUN_LEFT = Game.spritesheet.getSprite(19 * 16, 16 * 19, 16, 16);
	public static BufferedImage GUN_RIGHT = Game.spritesheet.getSprite(18 * 16, 16 * 19, 16, 16);
	
	public static BufferedImage GUN2_LEFT = Game.spritesheet.getSprite(17 * 16, 16 * 19, 16, 16);
	public static BufferedImage GUN2_RIGHT = Game.spritesheet.getSprite(16 * 16, 16 * 19, 16, 16);

	//Flecha de madeira
	public static BufferedImage BULLET_EN = Game.spritesheet.getSprite(0*16, 8 * 16, 16, 16);

	//Inimigos
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(0 * 16, 17*16, 16, 16);

	public static BufferedImage ENEMY_FEEDBACK = Game.spritesheet.getSprite(19*16, 0*17, 16, 16);
	public static BufferedImage ENEMY_FEEDBACK3 = Game.spritesheet.getSprite(16*16, 12*16, 64, 32);
	public static BufferedImage ENEMY_FEEDBACK6 = Game.spritesheet.getSprite(16*16, 0, 16, 16);
	public static BufferedImage ENEMY_FEEDBACK7 = Game.spritesheet.getSprite(16*16, 3*16, 16, 16);

	protected double x;
	protected double y;
	protected int width;
	protected int height;
	private int maskx = 0, masky = 0;
	private int mwidth = width, mheight = height;

	private BufferedImage sprite;

	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;

		this.maskx = 0;
		this.masky = 0;
		this.mwidth = width;
		this.mheight = height;
	}

	public void setMask(int maskx, int masky, int mwidth, int mheight) {
		this.maskx = maskx;
		this.masky = masky;
		this.mwidth = mwidth;
		this.mheight = mheight;
	}

	public void setX(int newX) {
		this.x = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	public int getX() {
		return (int) this.x;
	}

	public int getY() {
		return (int) this.y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void tick() {

	}

	public static boolean isColidding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx, e1.getY() + e1.masky, e1.mwidth, e1.mheight);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx, e2.getY() + e2.masky, e2.mwidth, e2.mheight);
		



		return e1Mask.intersects(e2Mask);
	}

	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
}

package com.inspiration.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.inspiration.entities.Bullet;
import com.inspiration.entities.Demagepack;
import com.inspiration.entities.Entity;
import com.inspiration.entities.Lifepack;
import com.inspiration.entities.Player;
import com.inspiration.entities.Weapon;
import com.inspiration.entities.Weapon2;
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
import com.inspiration.main.Game;
import com.inspiration.world.floor.FloorTile;
import com.inspiration.world.home.HomeTile;
import com.inspiration.world.home.HomeTile;
import com.inspiration.world.wall.WallTile;

public class World {

	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;

	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			for (int xx = 0; xx < map.getWidth(); xx++) {
				for (int yy = 0; yy < map.getHeight(); yy++) {

					int pixelAtual = pixels[xx + (yy * map.getWidth())];

					if (Game.CUR_LEVEL == 1) {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR1);
					} else if (Game.CUR_LEVEL == 2) {
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR24);
					}
					// chao

					if (pixelAtual == 0xFF000000) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR1);

					} else if (pixelAtual == 0xFF0A0A0A) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR2);

					} else if (pixelAtual == 0xFF282020) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR3);

					} else if (pixelAtual == 0xFF262626) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR4);

					} else if (pixelAtual == 0xFF443737) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR5);

					} else if (pixelAtual == 0xFF383838) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR6);

					}

					// Paredes

					else if (pixelAtual == 0xFF257E00) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL1);

					} else if (pixelAtual == 0xFF2F9E00) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL2);

					} else if (pixelAtual == 0xFF567800) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL3);

					} else if (pixelAtual == 0xFF6D9900) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL4);

					} else if (pixelAtual == 0xFF007F7F) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL5);

					} else if (pixelAtual == 0xFF873A00) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL6);

					} else if (pixelAtual == 0xFF421D00) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL7);

					} else if (pixelAtual == 0xFF3F2612) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL8);

					}

					// Casa
					else if (pixelAtual == 0xFFE52200) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new HomeTile(xx * 16, yy * 16, Tile.TILE_HOME1);

					} else if (pixelAtual == 0xFF818E54) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new HomeTile(xx * 16, yy * 16, Tile.TILE_HOME2);

					} else if (pixelAtual == 0xFF627F03) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new HomeTile(xx * 16, yy * 16, Tile.TILE_HOME3);

					} else if (pixelAtual == 0xFFBA2200) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new HomeTile(xx * 16, yy * 16, Tile.TILE_HOME4);

					} else if (pixelAtual == 0xFF97AD54) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new HomeTile(xx * 16, yy * 16, Tile.TILE_HOME5);

					} else if (pixelAtual == 0xFFB3B1A5) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new HomeTile(xx * 16, yy * 16, Tile.TILE_HOME6);

					} else if (pixelAtual == 0xFF7A1000) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new HomeTile(xx * 16, yy * 16, Tile.TILE_HOME7);

					} else if (pixelAtual == 0xFF82AA00) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new HomeTile(xx * 16, yy * 16, Tile.TILE_HOME8);

					} else if (pixelAtual == 0xFFADD32E) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new HomeTile(xx * 16, yy * 16, Tile.TILE_HOME9);

					} else if (pixelAtual == 0xFFBED86A) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new HomeTile(xx * 16, yy * 16, Tile.TILE_HOME10);

					}

					// CHAO FASE 2-5

					else if (pixelAtual == 0xFF000009) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR21);

					} else if (pixelAtual == 0xFF002D2D) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR22);

					} else if (pixelAtual == 0xFF206060) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR23);

					} else if (pixelAtual == 0xFF090009) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR24);

					} else if (pixelAtual == 0xFF060003) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR25);

					} else if (pixelAtual == 0xFF0A0A2A) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR26);

					} else if (pixelAtual == 0xFF061C03) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR27);

					}

					// PAREDE FASE 2-5
					else if (pixelAtual == 0xFF442C60) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL21);

					} else if (pixelAtual == 0xFF260026) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL22);

					} else if (pixelAtual == 0xFF560056) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL23);

					} else if (pixelAtual == 0xFF562756) {
						// Floor
						tiles[xx + (yy * WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL24);

					}

					// PLAYER

					else if (pixelAtual == 0xFF0026FF) {
						// Player
						Game.player.setX(xx * 16);
						Game.player.setY(yy * 16);
					}
					
					//ENEMY

					else if (pixelAtual == 0xFFC40300) {
						// Enemy
						Enemy en = new Enemy(xx * 16, yy * 16, 16, 16, Entity.ENEMY_EN);
						Game.entities.add(en);
						Game.enemies.add(en);

					} else if (pixelAtual == 0xFF7F0000) {
						// Enemy2
						Enemy2 en2 = new Enemy2(xx * 16, yy * 16, 16, 16, Entity.ENEMY_EN);
						Game.entities.add(en2);
						Game.enemies2.add(en2);

					} else if (pixelAtual == 0xFF7C3E73) {
						// Enemy3
						Enemy3 en3 = new Enemy3(xx * 16, yy * 16, 64, 64, Entity.ENEMY_EN);
						Game.entities.add(en3);
						Game.enemies3.add(en3);
						
					}else if (pixelAtual == 0xFF331733) {
						// Enemy4
						Enemy4 en4 = new Enemy4(xx * 16, yy * 16, 16, 16, Entity.ENEMY_EN);
						Game.entities.add(en4);
						Game.enemies4.add(en4);
					}
					
					else if (pixelAtual == 0xFF513B6B) {
						// Enemy5
						Enemy5 en5 = new Enemy5(xx * 16, yy * 16, 16, 16, Entity.ENEMY_EN);
						Game.entities.add(en5);
						Game.enemies5.add(en5);
					}
					
					else if (pixelAtual == 0xFFB205C1) {
						// Enemy6
						Enemy6 en6 = new Enemy6(xx * 16, yy * 16, 16, 16, Entity.ENEMY_EN);
						Game.entities.add(en6);
						Game.enemies6.add(en6);
					}
					
					else if (pixelAtual == 0xFFBF15C1) {
						// Enemy7
						Enemy7 en7 = new Enemy7(xx * 16, yy * 16, 16, 16, Entity.ENEMY_EN);
						Game.entities.add(en7);
						Game.enemies7.add(en7);
					}
					
					else if (pixelAtual == 0xFFCC15C1) {
						// Enemy8
						Enemy8 en8 = new Enemy8(xx * 16, yy * 16, 16, 16, Entity.ENEMY_EN);
						Game.entities.add(en8);
						Game.enemies8.add(en8);
					}
					
					else if (pixelAtual == 0xFFDD15C1) {
						// Enemy9
						Enemy9 en9 = new Enemy9(xx * 16, yy * 16, 16, 16, Entity.ENEMY_EN);
						Game.entities.add(en9);
						Game.enemies9.add(en9);
					}
					
					else if (pixelAtual == 0xFFBEE5C1) {
						// Enemy10
						Enemy10 en10 = new Enemy10(xx * 16, yy * 16, 16, 16, Entity.ENEMY_EN);
						Game.entities.add(en10);
						Game.enemies10.add(en10);
					}

					else if (pixelAtual == 0xFFC4BD00) {
						// Weapon
						Game.entities.add(new Weapon(xx * 16, yy * 16, 16, 16, Entity.WEAPON_EN));
					}
					
					else if (pixelAtual == 0xFFD5BD00) {
						// Weapon2
						Game.entities.add(new Weapon2(xx * 16, yy * 16, 16, 16, Entity.WEAPON2_EN));
					}

					else if (pixelAtual == 0xFFFF5959) {
						// Life pack
						Lifepack pack = new Lifepack(xx * 16, yy * 16, 16, 16, Entity.LIFEPACK_EN);
						Game.entities.add(pack);
					}

					else if (pixelAtual == 0xFFB2D68D) {
						// Life pack 2
						Lifepack pack2 = new Lifepack(xx * 16, yy * 16, 16, 16, Entity.LIFEPACK2_EN);
						Game.entities.add(pack2);
					}
					
					else if (pixelAtual == 0xFFCF94FC) {
						// Life pack 3
						Lifepack pack3 = new Lifepack(xx * 16, yy * 16, 16, 16, Entity.LIFEPACK3_EN);
						Game.entities.add(pack3);
					}
					
					else if (pixelAtual == 0xFFFFF4FC) {
						// DemagePack 3
						Demagepack demage3 = new Demagepack(xx * 16, yy * 16, 16, 16, Entity.DEMAGEPACK_EN);
						Game.entities.add(demage3);
					}

					else if (pixelAtual == 0xFF898989) {
						// Bullet
						Game.entities.add(new Bullet(xx * 16, yy * 16, 16, 16, Entity.BULLET_EN));
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Colisão com o mundo!
	public static boolean isFree(int xnext, int ynext) {
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;

		int x2 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;

		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext + TILE_SIZE - 1) / TILE_SIZE;

		int x4 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (ynext + TILE_SIZE - 1) / TILE_SIZE;

		return !((tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile)
				|| (tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile) ||

				// Home

				(tiles[x1 + (y1 * World.WIDTH)] instanceof HomeTile)
				|| (tiles[x2 + (y2 * World.WIDTH)] instanceof HomeTile)
				|| (tiles[x3 + (y3 * World.WIDTH)] instanceof HomeTile)
				|| (tiles[x4 + (y4 * World.WIDTH)] instanceof HomeTile) 

		);

	}

	// Recomeçando o jogo
	public static void restartGame(String level) {
		Game.entities = new ArrayList<Entity>();

		// Reposicionando os inimigos
		Game.enemies = new ArrayList<Enemy>();
		Game.enemies2 = new ArrayList<Enemy2>();
		Game.enemies3 = new ArrayList<Enemy3>();
		Game.enemies4 = new ArrayList<Enemy4>();
		Game.enemies5 = new ArrayList<Enemy5>();
		Game.enemies6 = new ArrayList<Enemy6>();
		Game.enemies7 = new ArrayList<Enemy7>();
		Game.enemies8 = new ArrayList<Enemy8>();
		Game.enemies9 = new ArrayList<Enemy9>();
		Game.enemies10 = new ArrayList<Enemy10>();
		Game.spritesheet = new Spritesheet("/spritesheet.png");
		Game.player = new Player(0, 0, 16, 16, Game.spritesheet.getSprite(32, 0, 16, 16));
		Game.entities.add(Game.player);
		Game.world = new World("/" + level);
		return;
	}

	public void render(Graphics g) {

		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;

		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);

		for (int xx = xstart; xx <= xfinal; xx++) {
			for (int yy = ystart; yy <= yfinal; yy++) {
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}
}

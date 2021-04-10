package com.inspiration.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.inspiration.main.Game;

public class Tile {

	// Chão
	public static BufferedImage TILE_FLOOR1 = Game.spritesheet.getSprite(0, 16*15, 16, 16); // Chao c/branco #000000
	public static BufferedImage TILE_FLOOR2 = Game.spritesheet.getSprite(0, 16*16, 16, 16); // Chao c/verde #0A0A0A
	public static BufferedImage TILE_FLOOR3 = Game.spritesheet.getSprite(0, 16*17, 16, 16); // Chao flor azul #282020
	public static BufferedImage TILE_FLOOR4 = Game.spritesheet.getSprite(0, 16*18, 16, 16); // Chao com grama #262626
	public static BufferedImage TILE_FLOOR5 = Game.spritesheet.getSprite(0, 16*19, 16, 16); // Chao com flor azul2
																								// #443737
	public static BufferedImage TILE_FLOOR6 = Game.spritesheet.getSprite(16*1, 16*19, 16, 16); // Chao com girassol
																								// #383838

	// Parede
	public static BufferedImage TILE_WALL1 = Game.spritesheet.getSprite(16*1, 16*15, 16, 16);// Arvore maça1 #257E00
	public static BufferedImage TILE_WALL2 = Game.spritesheet.getSprite(16*1, 16*16, 16, 16); // Arvore maça2 #2F9E00
	public static BufferedImage TILE_WALL3 = Game.spritesheet.getSprite(16*1, 16*17, 16, 16); // Arvore pera1 #567800
	public static BufferedImage TILE_WALL4 = Game.spritesheet.getSprite(16*1, 16*18, 16, 16); // Arvore pera2 #6D9900
	public static BufferedImage TILE_WALL5 = Game.spritesheet.getSprite(16*2, 16*16, 16, 16); // caixa dagua #007F7F
	public static BufferedImage TILE_WALL6 = Game.spritesheet.getSprite(16*2, 16*17, 16, 16); // cerca horizontal
																									// #873A00
	public static BufferedImage TILE_WALL7 = Game.spritesheet.getSprite(16*2, 16*18, 16, 16);  // cerca vertical
																									// #421D00
	public static BufferedImage TILE_WALL8 = Game.spritesheet.getSprite(16*2, 16*19, 16, 16); ; // Cerca completa
																									// #3F2612

	// Casa
	public static BufferedImage TILE_HOME1 = Game.spritesheet.getSprite(0, 16 * 2, 16, 16);// Telhado esquerdo
																								// #E52200
	public static BufferedImage TILE_HOME2 = Game.spritesheet.getSprite(0, 16 * 5, 16, 16);// Telhado casa esquerdo
																								// #818E54
	public static BufferedImage TILE_HOME3 = Game.spritesheet.getSprite(0, 16 * 6, 16, 16);// casa esquerdo #627F03
	public static BufferedImage TILE_HOME4 = Game.spritesheet.getSprite(0, 16 * 3, 16, 16); // Telhado meio #BA2200
	public static BufferedImage TILE_HOME5 = Game.spritesheet.getSprite(16*1, 16 * 5, 16, 16); // Telhado casa janela
																									// meio #97AD54
	public static BufferedImage TILE_HOME6 = Game.spritesheet.getSprite(16*1, 16 * 6, 16, 16); // casa porta #B3B1A5
	public static BufferedImage TILE_HOME7 = Game.spritesheet.getSprite(16*3, 16 * 2, 16, 16); // Telhado direito
																									// #7A1000
	public static BufferedImage TILE_HOME8 = Game.spritesheet.getSprite(16*3, 16 * 5, 16, 16);; // Telhado casa direito
																									// #82AA00
	public static BufferedImage TILE_HOME9 = Game.spritesheet.getSprite(16*3, 16 * 6, 16, 16); // Casa direito #ADD32E
	public static BufferedImage TILE_HOME10 = Game.spritesheet.getSprite(16*2, 16 * 6, 16, 16); // casa meio #BED86A
	
	//chao level 2
	public static BufferedImage TILE_FLOOR21 = Game.spritesheet.getSprite(16*3, 16*15, 16, 16); // Chao c/sangue 000009
	public static BufferedImage TILE_FLOOR22 = Game.spritesheet.getSprite(16*3, 16*16, 16, 16); // Chao c/caveira 002D2D
	public static BufferedImage TILE_FLOOR23 = Game.spritesheet.getSprite(16*3, 16*17, 16, 16); // Chao c/osso 206060
	public static BufferedImage TILE_FLOOR24 = Game.spritesheet.getSprite(16*3, 16*18, 16, 16); // Chao normal 090009
	public static BufferedImage TILE_FLOOR25 = Game.spritesheet.getSprite(16*3, 16*19, 16, 16); // Chaocom flor da morte 060003																						// #443737
	public static BufferedImage TILE_FLOOR26 = Game.spritesheet.getSprite(16*4, 16*19, 16, 16); // Chao 02 c/ flor da morte 0a0a2a
	public static BufferedImage TILE_FLOOR27 = Game.spritesheet.getSprite(16*2, 16*15, 16, 16); // Chao com espada 061C03

	// Parede level 2
	public static BufferedImage TILE_WALL21 = Game.spritesheet.getSprite(16*4, 16*15, 16, 16); // Arvore podre 1 442C60
	public static BufferedImage TILE_WALL22 = Game.spritesheet.getSprite(16*4, 16*16, 16, 16);  // Arvore podre 2 260026
	public static BufferedImage TILE_WALL23 = Game.spritesheet.getSprite(16*4, 16*17, 16, 16);  // Arvore amaldiçoada 1 560056
	public static BufferedImage TILE_WALL24 = Game.spritesheet.getSprite(16*4, 16*18, 16, 16);  // Arvore amaldiçoada 2 562756

	private BufferedImage sprite;
	private int x, y;

	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public void render(Graphics g) {
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}

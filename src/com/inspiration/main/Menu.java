package com.inspiration.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import com.inspiration.graficos.ImageInsp;
import com.inspiration.graficos.Spritesheet;

public class Menu {
	


	public String[] options = { "novo jogo", "carregar jogo", "sair" };

	public int currentOption = 0;
	public int maxOption = options.length - 1;

	public boolean up, down, enter;
	
	private ImageInsp imageInsp;
	
	public boolean pause = false;
	


	public void tick() {
		if (up) {
			up = false;
			currentOption--;
			if (currentOption < 0) {
				currentOption = maxOption;
			}
		}
		if (down) {
			down = false;
			currentOption++;
			if (currentOption > maxOption) {
				currentOption = 0;
			}
		}
		if (enter) {
			enter = false;
			if (options[currentOption] == "novo jogo" || options[currentOption] == "continuar") {
				Game.gameState = "NORMAL";
				pause = false;
			} else if (options[currentOption] == "sair") {
				System.exit(1);
			}
		}
	}

	public void render(Graphics g) {
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("arial", Font.BOLD, 36));
		g.drawString("ElementalSword", (Game.WIDTH * Game.SCALE) / 2 - 110, (Game.HEIGHT * Game.SCALE) / 2 - 130);

		// opcoes de menu
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 25));
		if (pause == false) {
			g.drawString("novo jogo", (Game.WIDTH * Game.SCALE) / 2 - 50, 200);
		} else {
			g.drawString("continuar", (Game.WIDTH * Game.SCALE) / 2 - 50, 200);
		}

		g.setColor(Color.GRAY);
		g.drawString("carregar jogo", (Game.WIDTH * Game.SCALE) / 2 - 50, 240);

		g.setColor(Color.WHITE);
		g.drawString("sair", (Game.WIDTH * Game.SCALE) / 2 - 50, 280);

		if (options[currentOption] == "novo jogo") {
			g.setColor(Color.YELLOW);
			g.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 90, 200);
		} else if (options[currentOption] == "carregar jogo") {
			g.setColor(Color.GRAY);
			g.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 90, 240);
		} else if (options[currentOption] == "sair") {
			g.setColor(Color.YELLOW);
			g.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 90, 280);
		}
	}

}

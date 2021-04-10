package com.inspiration.graficos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.inspiration.entities.Player;
import com.inspiration.main.Game;

public class UI {

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(8, 4, 50, 8);
		g.setColor(Color.green);
		g.fillRect(8, 4, (int) ((Game.player.life / Game.player.maxLife) * 50), 8);
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 8));
		g.drawString((int) Game.player.life + "/" + (int) Game.player.maxLife, 11, 11);
	}

}

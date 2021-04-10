package com.inspiration.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageInsp {

	private BufferedImage imageInsp;

	public ImageInsp(String path) {

		try {
			imageInsp = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public BufferedImage getSprite(int x, int y, int width, int height) {
		return imageInsp.getSubimage(x, y, width, height);
	}
}

package com.worms.seaworm;

import com.worms.seaworm.lib.Texture;

public class Textures {
	public static Texture SNAKE_HEAD;
	public static Texture SNAKE_BODY;
	
	public static Texture WATER_DROP;
	
	public static void setTextures() {
		SNAKE_HEAD = Texture.loadTexture("worm/head.png");
		SNAKE_BODY = Texture.loadTexture("worm/body.png");
		WATER_DROP = Texture.loadTexture("water_drop.png");
	}
}

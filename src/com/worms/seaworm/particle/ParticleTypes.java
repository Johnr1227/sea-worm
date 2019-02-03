package com.worms.seaworm.particle;

import com.worms.seaworm.Textures;
import com.worms.seaworm.lib.Color;
import com.worms.seaworm.lib.Texture;

public enum ParticleTypes {
	SPLASH(-2, 6, -1, -6, 5, 30, Textures.WATER_DROP, new Color(0.0f,0.5f, 0.7f), 10,40);
	
	public float minVelX;
	public float maxVelX;
	
	public float minVelY;
	public float maxVelY;
	
	public int minScale;
	public int maxScale;
	
	public int minCount;
	public int maxCount;
	
	public Texture texture;
	public Color color;
	
	ParticleTypes(float minVelX, float maxVelX, float minVelY, float maxVelY, int minScale, int maxScale, Texture texture, Color color, int minCount, int maxCount) {
		this.minVelX = minVelX;
		this.maxVelX = maxVelX;
		this.minVelY = minVelY;
		this.maxVelY = maxVelY;
		this.minScale = minScale;
		this.maxScale = maxScale;
		this.texture = texture;
		this.color = color;
		this.minCount = minCount;
		this.maxCount = maxCount;
	}
}

package com.worms.seaworm.particle;

import com.worms.seaworm.lib.Color;
import com.worms.seaworm.lib.Texture;
import com.worms.seaworm.lib.Vertex;

public class Particle {
	public Texture texture;
	public Vertex pos;
	
	public float velX;
	public float velY;
	
	public int ticksExisted = 0;
	
	public int scale;
	
	private float drag;
	private float gravity;
	
	public Color color;
	
	public Particle(Vertex pos, float velX, float velY, Texture texture, Color color, int scale, float drag, float gravity) {
		this.pos = pos;
		this.velX = velX;
		this.velY = velY;
		this.texture = texture;
		this.color = color;
		this.scale = scale;
		this.drag = drag;
		this.gravity = gravity;
	}
	public void tick() {
		this.pos.x += (int)velX;
		this.pos.y += (int)velY;
		this.velX *= drag;
		this.velY += gravity;
		this.ticksExisted++;
	}
}

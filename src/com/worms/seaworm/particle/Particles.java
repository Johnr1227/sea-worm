package com.worms.seaworm.particle;

import java.util.ArrayList;
import java.util.Random;

import com.worms.seaworm.lib.Color;
import com.worms.seaworm.lib.Renderer;
import com.worms.seaworm.lib.Texture;
import com.worms.seaworm.lib.Vertex;

public class Particles {
	
	public static ArrayList<Particle> particles = new ArrayList<>();
	
	public static float defaultDrag = 1f;
	public static float defaultGravity = 0.25f;
	
	public static int particleLifespan = 200;
	
	public static void makeParticle(Particle p) {
		particles.add(p);
	}
	public static void makeParticle(Vertex pos, float velX, float velY, int scale, Texture texture) {
		particles.add(new Particle(pos.clone(),velX,velY,texture,new Color(1,1,1),scale,defaultDrag,defaultGravity));
	}
	public static void makeParticle(Vertex pos, float velX, float velY, int scale, Texture texture, Color color) {
		particles.add(new Particle(pos.clone(),velX,velY,texture,color,scale,defaultDrag,defaultGravity));
	}
	public static void makeParticles(Vertex pos, float minVelX, float maxVelX, float minVelY, float maxVelY, int minScale, int maxScale, Texture texture, Color color, int count) {
		Random random = new Random();
		for(int i = 0; i < count; i++) {
			makeParticle(pos.clone(),random.nextFloat()*(maxVelX-minVelX)+minVelX,random.nextFloat()*(maxVelY-minVelY)+minVelY,(int)(random.nextFloat()*(maxScale-minScale)+minScale),texture,color);
		}
	}
	public static void spawn(ParticleTypes type, Vertex pos) {
		makeParticles(pos,type.minVelX,type.maxVelX,type.minVelY,type.maxVelY,type.minScale,type.maxScale,type.texture,type.color,(int)(type.minCount+Math.random()*(type.maxCount-type.minCount)));
	}
	public static void tickParticles() {
		ArrayList<Integer> indecesToDelete = new ArrayList<>();
		for(int i = 0; i < particles.size(); i++)  {
			Particle p = particles.get(i); 
			p.tick();
			if(p.ticksExisted > particleLifespan) {
				indecesToDelete.add(i-indecesToDelete.size());
			}
		}
		for(int i : indecesToDelete) {
			particles.remove(i);
		}
	}
	public static void renderParticles() {
		for(Particle p : particles)  {
			float slope = Math.abs(p.velY / p.velX);
			double angleRad = Math.atan(slope);
//			Renderer.drawColoredTexture(p.pos.x, p.pos.y, p.pos.x+p.scale, p.pos.y+p.scale, p.texture, p.color.r, p.color.g, p.color.b);
			Renderer.drawColoredRotatedTexture(p.pos.x, p.pos.y, p.pos.x+p.scale, p.pos.y+p.scale, p.texture, p.color.r, p.color.g, p.color.b,(float)Math.toDegrees(angleRad));
		}
	}
}

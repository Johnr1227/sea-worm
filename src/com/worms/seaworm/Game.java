package com.worms.seaworm;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;

import com.worms.seaworm.lib.Renderer;
import com.worms.seaworm.lib.Vertex;
import com.worms.seaworm.particle.Particles;
import com.worms.seaworm.worm.Worm;

public class Game extends Renderer {
	public long window;
	
	// Wave variables
	private int minHeight = 200;
	private float speed = 1f;
	private float speed2 = 2f;
	private float frequency = 1f;
	private float frequency2 = 0.5f;
	private int amplitude = 20;
	private int amplitude2 = 50;
	private int q = 15;

	private Worm worm = new Worm(new Vertex(100,0), 100, 10, 100);

	public Game(long window) {
		this.window = window;
	}

	public void tick() {
		worm.tick();
		Particles.tickParticles();
	}

	public void draw() {
		minHeight = 200 + (int) (Math.sin(Math.toRadians(Main.TICKS / 100)) * 50);
		
		worm.render();
		
		Particles.renderParticles();
		
		glDisable(GL_TEXTURE_2D);
		for (int x = 0; x < Main.WINDOW_WIDTH; x += q) {
			glBegin(GL_POLYGON);
			glColor4f(0, 0.2f, 1f, 0.5f);
			glVertex2i(x, Main.WINDOW_HEIGHT);
			glVertex2i(x + q, Main.WINDOW_HEIGHT);
			glVertex2i(x + q, wave(Main.TICKS, x + q));
			glVertex2i(x, wave(Main.TICKS, x));
			glEnd();
		}
	}

	public int wave(int time, int x) {
		return ((int) (Math.sin(Math.toRadians(x * frequency + speed * Main.TICKS))
				* (amplitude * Math.sin(Math.toRadians(speed * Main.TICKS))) + minHeight)
				+ (int) (Math.sin(Math.toRadians(x * frequency2 + speed2 * Main.TICKS))
						* (amplitude2 * Math.sin(Math.toRadians(speed2 * Main.TICKS))) + minHeight))
				/ 2;
	}

	public int getCursorPosX() {
		DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(window, posX, null);
		return (int) posX.get(0);
	}

	public int getCursorPosY() {
		DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
		glfwGetCursorPos(window, null, posY);
		return (int) posY.get(0);
	}
}

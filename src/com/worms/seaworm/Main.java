package com.worms.seaworm;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;

import com.worms.seaworm.audio.Audio;
import com.worms.seaworm.audio.Sounds;

public class Main {

	public static long window;

	public static int WINDOW_WIDTH = 0;
	public static int WINDOW_HEIGHT = 0;
	
	public static int MOUSEX;
	public static int MOUSEY;

	public static int TICKS = 0;

	public static Game game;

	public static void main(String[] args) {
		if (!glfwInit()) {
			throw new IllegalStateException("Failed to initialize GLFW!");
		}
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		WINDOW_WIDTH = 1280;
		WINDOW_HEIGHT = 720;

		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		window = glfwCreateWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "Sea Worm", 0, 0);
		if (window == 0) {
			throw new IllegalStateException("Failed to create window!");
		}
		glfwSetWindowPos(window, (videoMode.width() - WINDOW_WIDTH) / 2, (videoMode.height() - WINDOW_WIDTH) / 2);

		glfwShowWindow(window);

		glfwMakeContextCurrent(window);

		GL.createCapabilities();
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glOrtho(0, WINDOW_WIDTH, WINDOW_HEIGHT, 0, 1, -1);

		Textures.setTextures();

		// set background color
		glClearColor(0.3f, 0.6f, 1f, 1f);

		Audio.init();
		Sounds.init();

		try {
			// Wait for a second
			Thread.sleep(1000);
		} catch (InterruptedException ignored) {
		}

		game = new Game(window);

		glfwSetWindowSizeCallback(window, new GLFWWindowSizeCallback() {

			@Override
			public void invoke(long window, int width, int height) {
				WINDOW_WIDTH = width;
				WINDOW_HEIGHT = height;
				glOrtho(0, WINDOW_WIDTH, WINDOW_HEIGHT, 0, 1, -1);

			}
		});
		
		Sounds.MUSIC.play();
		while (!glfwWindowShouldClose(window)) {
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT);
			game.tick();
			TICKS++;
			game.draw();
			MOUSEX = game.getCursorPosX();
			MOUSEY = game.getCursorPosY();
			glfwSwapBuffers(window);
		}
		Audio.destroy();
		glfwTerminate();
	}

}

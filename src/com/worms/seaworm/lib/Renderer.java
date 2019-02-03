package com.worms.seaworm.lib;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
	
	public static void drawTexture(int x, int y, int x2, int y2, Texture tex) {
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_TEXTURE);
		glLoadIdentity();	
		
		tex.bind();
		
		glBegin(GL_QUADS);
		glColor3f(1, 1, 1);
		glTexCoord2i(0, 1);
		glVertex2i(x, y);
		
		glTexCoord2i(1, 1);
		glVertex2i(x2, y);
		
		glTexCoord2i(1, 0);
		glVertex2i(x2, y2);
		
		glTexCoord2i(0, 0);
		glVertex2i(x, y2);
		
		glEnd();
	}
	public static void drawColoredTexture(int x, int y, int x2, int y2, Texture tex, float r, float g, float b) {
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_TEXTURE);
		glLoadIdentity();
		
		tex.bind();
		
		glBegin(GL_QUADS);
		glColor3f(r, g, b);
		glTexCoord2i(0, 1);
		glVertex2i(x, y);
		
		glTexCoord2i(1, 1);
		glVertex2i(x2, y);
		
		glTexCoord2i(1, 0);
		glVertex2i(x2, y2);
		
		glTexCoord2i(0, 0);
		glVertex2i(x, y2);
		
		glEnd();
	}
	public static void drawColoredRotatedTexture(int x, int y, int x2, int y2, Texture tex, float r, float g, float b, float rot) {
		glEnable(GL_TEXTURE_2D);
		glMatrixMode(GL_TEXTURE);
		glLoadIdentity();
		glTranslatef(0.5f,0.5f,0.0f);
		glRotatef(rot,0,0,1);
		glTranslatef(-0.5f,-0.5f,0.0f);
		
		tex.bind();
		
		glBegin(GL_QUADS);
		glColor3f(r, g, b);
		glTexCoord2i(0, 1);
		glVertex2i(x, y);
		
		glTexCoord2i(1, 1);
		glVertex2i(x2, y);
		
		glTexCoord2i(1, 0);
		glVertex2i(x2, y2);
		
		glTexCoord2i(0, 0);
		glVertex2i(x, y2);
		
		glEnd();
	}
	public static void drawRect(int x, int y, int x2, int y2, float r, float g, float b) {
		glDisable(GL_TEXTURE_2D);
		glBegin(GL_QUADS);
		glColor3f(r, g, b);
		glTexCoord2i(0, 1);
		glVertex2i(x, y);
		
		glTexCoord2i(1, 1);
		glVertex2i(x2, y);
		
		glTexCoord2i(1, 0);
		glVertex2i(x2, y2);
		
		glTexCoord2i(0, 0);
		glVertex2i(x, y2);
		
		glEnd();
	}
}

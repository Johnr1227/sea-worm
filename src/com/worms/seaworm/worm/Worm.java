package com.worms.seaworm.worm;

import static com.worms.seaworm.lib.Renderer.drawColoredTexture;

import java.util.ArrayList;

import com.worms.seaworm.Main;
import com.worms.seaworm.Textures;
import com.worms.seaworm.audio.Sounds;
import com.worms.seaworm.lib.Vertex;
import com.worms.seaworm.particle.ParticleTypes;
import com.worms.seaworm.particle.Particles;

public class Worm {

	public Vertex headPos;
	public int length;
	public int headSize;

	public ArrayList<WormSegment> segments = new ArrayList<>();

	public boolean surfaced = false;

	public Worm(Vertex headPos, int length, int numSegments, int headSize) {
		this.headPos = headPos;
		this.length = length;
		this.headSize = headSize;
		for (int i = 1; i < numSegments; i++) {
			segments.add(new WormSegment(getX() - i * (length / numSegments), getY()));
		}
	}

	public void tick() {
		headPos.y = Math.max(Main.MOUSEY, Main.game.wave(Main.TICKS, getX())) - headSize / 2;
		if (surfaced) {
			if (Main.MOUSEY > Main.game.wave(Main.TICKS, getX())) {
				surfaced = false;
			}
		} else if (Main.MOUSEY < Main.game.wave(Main.TICKS, getX())) {
			surfaced = true;
			Sounds.SPLASH.play();
			Particles.spawn(ParticleTypes.SPLASH, Vertex.addVertices(headPos, (int) (headSize * 0.9), headSize / 2));
		}

		int lastY = getY();
		for (WormSegment s : segments) {
			if (s.getY() > lastY) {
				s.pos.y += Math.floor((lastY - s.getY()) * 0.3f);
			} else {
				s.pos.y += Math.ceil((lastY - s.getY()) * 0.3f);
			}
			s.tick(this);
			lastY = s.pos.y;
		}
	}

	public void render() {
		for (int i = segments.size() - 1; i >= 0; i--) {
			WormSegment s = segments.get(i);
			drawColoredTexture(s.getX(), s.getY(), s.getX() + headSize, s.getY() + headSize, Textures.SNAKE_BODY, 0.1f,
					0.9f, 0.3f);
		}
		drawColoredTexture(getX(), getY(), getX() + headSize, getY() + headSize, Textures.SNAKE_HEAD, 0.1f, 0.9f, 0.3f);
	}

	public int getX() {
		return headPos.x;
	}

	public int getY() {
		return headPos.y;
	}
}

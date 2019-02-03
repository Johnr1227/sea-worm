package com.worms.seaworm.worm;

import com.worms.seaworm.Main;
import com.worms.seaworm.lib.Vertex;

public class WormSegment {
	public Vertex pos;
	
	public WormSegment(int x, int y) {
		this.pos = new Vertex(x,y);
	}
	
	public void tick(Worm worm) {
		this.pos.y = Math.max(this.pos.y, Main.game.wave(Main.TICKS, getX())-worm.headSize/2);
	}
	
	public int getX() {
		return pos.x;
	}
	public int getY() {
		return pos.y;
	}
}

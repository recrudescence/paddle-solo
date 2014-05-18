package paddle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle {
	public static final int WIDTH = 60;
	public static final int HEIGHT = 10;
	
	private MainGame game;
	private int x = 0;
	private int xdir = 0;
	
	public Paddle(MainGame game) {
		this.game = game;;
	}

	public void paint(Graphics2D g) {
		g.fillRect(x, game.getHeight() - 75, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(x, game.getHeight() - 75, WIDTH, 4);
		g.setColor(Color.WHITE);
		g.fillRect(x, game.getHeight() - 75, WIDTH, 2);
	}
	
	public void move() {
		if (x + xdir > 0 && x + xdir < game.getWidth() - WIDTH) {
			x = x + xdir;
		}
	}
	
	public void forceMove(int x) {
		this.x = x;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, game.getHeight() - 75, WIDTH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		xdir = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			xdir = -game.speed;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			xdir = game.speed;
		}
	}
	
	public int getTop() {
		return game.getHeight() - 75;
	}
}

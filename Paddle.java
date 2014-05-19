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
	
	/*
	 * Constructor allows for accessing of game details via MainGame object
	 */
	public Paddle(MainGame game) {
		this.game = game;;
	}

	/*
	 * Basically builds the paddle, with some detail on top.
	 */
	public void paint(Graphics2D g) {
		g.fillRect(x, game.getHeight() - 75, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(x, game.getHeight() - 75, WIDTH, 4);
		g.setColor(Color.WHITE);
		g.fillRect(x, game.getHeight() - 75, WIDTH, 2);
	}
	
	/*
	 * Move the paddle, if you're within the game frame.
	 */
	public void move() {
		if (x + xdir > 0 && x + xdir < game.getWidth() - WIDTH) {
			x = x + xdir;
		}
	}
	
	// TODO: finish middle spawning paddle
	public void forceMove(int x) {
		this.x = x;
	}
	
	/*
	 * Bounding box for paddle, for collision detection.
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, game.getHeight() - 75, WIDTH, HEIGHT);
	}

	/*
	 * If there is no key being pressed then we're not going in a direction.
	 */
	public void keyReleased(KeyEvent e) {
		xdir = 0;
	}

	/*
	 * If we're pressing left, go left! Right, go right!
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			xdir = -game.speed;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			xdir = game.speed;
		}
	}
	
	/*
	 * Return the y value of the top of the paddle.
	 * TODO: get the value FROM somewhere, instead of this.
	 */
	public int getTop() {
		return game.getHeight() - 75;
	}
}

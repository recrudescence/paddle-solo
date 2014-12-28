package paddle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	// basically too many instantiations right here
	public static final int DIAMETER = 30;
	
	// there could definitely be some optimization of flow
	private MainGame game;
	private boolean change = false;
	private boolean collided = false;
	private int x = 0;
	private int y = 0;
	private int xdir = 1;
	private int ydir = 1;
	
	// I shall keep that in mind for future projects
	Random rand = new Random();
	Color color = Color.BLUE;
	
	/*
	 * Constructor allows for accessing of game details via MainGame object
	 */
	public Ball(MainGame game) {
		this.game = game;
	}

	/*
	 * Overrides. If there's a change in direction, changes color!
	 * If there is no paddle thwack, plays a boop sound. Then, repaints.
	 */
	public void paint(Graphics2D g) {
		if (change) {
			if (!collided) {
				Sound.BOOP.play();
			}
			color = new Color(rand.nextInt(255), 
								rand.nextInt(255), 
								rand.nextInt(255));
			g.setColor(color);
			change = false;
			collided = false;
		}
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	/*
	 * Accessor for color.
	 */
	public Color getColor() {
		return this.color;
	}
	
	/*
	 * Moves. The thing. Depending on which wall the ball hits.
	 * Game speed represents whether the ball is going left/right/up/down.
	 * Positive x means right, positive y means down. Change variable to
	 * signify whether or not we make a BOOP sound later. If we hit the
	 * paddle, make a BEEP sound!
	 */
	public void move() {
		// 
		if (x + xdir < 0) {
			xdir = game.speed;
			change = true;
		}
		if (x + xdir > game.getWidth() - DIAMETER) {
			xdir = -game.speed;
			change = true;
		}
		if (y + ydir < 0) {
			ydir = game.speed;
			change = true;
		}
		// hitting bottom edge = game over
		if (y + ydir > game.getHeight() - DIAMETER) {
			game.gameOver();
		}
		// collision checks if ball touches paddle
		if (collision()) {
			Sound.BEEP.play();
			y = game.paddle.getTop() - DIAMETER;
			ydir = -(game.speed++);						// reverse! reverse! reverse!
			change = true;
			collided = true;
		}
		// move in direction
		x = x + xdir;
		y = y + ydir;
	}

	/*
	 * Returns a rectangle bounding the ball for collision handling
	 */
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	
	/*
	 * Do the ball and paddle intersect? If so, we're colliding.
	 */
	public boolean collision() {
		return game.paddle.getBounds().intersects(getBounds());
	}

	// hi calvin
}

package paddle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	public static final int DIAMETER = 30;
	
	private MainGame game;
	boolean change = false;
	boolean collided = false;
	private int x = 0;
	private int y = 0;
	private int xdir = 1;
	private int ydir = 1;
	Random rand = new Random();
	Color color = Color.BLUE;
	
	public Ball(MainGame game) {
		this.game = game;
	}

	public void paint(Graphics2D g) {
		if (change) {
			if (!collided) {
				Sound.BOOP.play();
			}
			color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
			g.setColor(color);
			change = false;
			collided = false;
		}
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void move() {
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
		if (y + ydir > game.getHeight() - DIAMETER) {
			game.gameOver();
		}
		if (collision()) {
			Sound.BEEP.play();
			y = game.paddle.getTop() - DIAMETER;
			ydir = -(game.speed++);
			change = true;
			collided = true;
		}
		x = x + xdir;
		y = y + ydir;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	
	public boolean collision() {
		return game.paddle.getBounds().intersects(getBounds());
	}
}

package paddle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainGame extends JPanel {
	Ball ball = new Ball(this);
	Paddle paddle = new Paddle(this);
	int speed = 1;
	
	public MainGame() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				paddle.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				paddle.keyPressed(e);
			}
		});
		setFocusable(true);
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("PaddleSolo");
		frame.setSize(400, 600);
		MainGame game = new MainGame();
		frame.add(game);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(ball.getColor());
		ball.paint(g2d);
		paddle.paint(g2d);
		
		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Helvetica", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore()), 10, 30);
		
	}
	
	private void move() {
		ball.move();
		paddle.move();
	}
	
	public void gameOver() {
		try {
		    Thread.sleep(300);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		Sound.DEATH.play();
		JOptionPane.showMessageDialog(this, "Game Over!\r\nScore: " + getScore(), "Game Over!", JOptionPane.OK_OPTION);
		System.exit(ABORT);
	}
	
	public int getScore() {
		return speed-1;
	}
}

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

/*
 * Okay so I'll just comment everything for clarity!
 */
public class MainGame extends JPanel {
	Ball ball = new Ball(this);
	Paddle paddle = new Paddle(this);
	// the speed at which the ball/paddle moves
	int speed = 1;
	
	/*
	 * Constructor. Adds a KeyListener, which listens for key inputs.
	 * Interesting thing -- first time I've come across anonymous classes,
	 * or a class definition within an expression. The KeyListener calls
	 * to methods within a Paddle object.
	 */
	public MainGame() {
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				paddle.keyPressed(e);
			}

			public void keyReleased(KeyEvent e) {
				paddle.keyReleased(e);
			}
		});
		// gives this Component (the game) the ability to be focused on. Allows the KeyListener to actually work.
		setFocusable(true);
	}
	
	/*
	 * Main method -- the InterruptedException throw is for Thread.sleep(),
	 * which tells the whole game Thread to hold up for like, 10ms, so various
	 * functions can be carried out. 
	 */
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("PaddleSolo");				// the actual window
		frame.setSize(400, 600);								// size of window
		MainGame game = new MainGame();							// instantiate!
		frame.add(game);										// stick it in the frame
		frame.setVisible(true);									// yes, let us see everything pls
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// when we close the window, quit the program
		Sound.BG1.loop();										// marvelous background music :)
		
		while (true) {											// for now, we don't need to not be playing
			game.move();										// move what needs to be moved (see move())
			game.repaint();										// update graphics
			Thread.sleep(10);									// 10ms is enough to let everything (i.e. graphics) finish their thing
		}
	}
	
	/*
	 * draw errthang (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		super.paint(g);											// empty errthang out so we don't 'smear'
		Graphics2D g2d = (Graphics2D) g;						// nts: look this up
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,	// for prettyness
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(ball.getColor());							// set the color to the color of the Ball (which gets randomized)
		ball.paint(g2d);
		paddle.paint(g2d);
		
		// for score
		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Helvetica", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore()), 10, 30);
		
	}
	
	/*
	 * move. things. basically.
	 */
	private void move() {
		ball.move();
		paddle.move();
	}
	
	/*
	 * for the end.
	 */
	public void gameOver() {
		// serious time right now, so hol'up.
		Sound.BG1.stop();
		
		// pause stuff for a sec, just so people can process their loss
		try {
		    Thread.sleep(300);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		// aite now we can really hammer in their demise
		Sound.DEATH.play();
		JOptionPane.showMessageDialog(this, "Game Over!\r\nScore: " + getScore(),
										"Game Over!", JOptionPane.OK_OPTION);
		System.exit(ABORT);
	}
	
	/*
	 * accessor/getter for score, which is really just the added speed.
	 */
	public int getScore() {
		return speed-1;
	}
}

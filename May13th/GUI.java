package Game2048;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GUI {
	private int height = 485;
	private int width = 320;
	private int gameBoardSize = 150;
	private int marginSize = 16;
	private Color background = Color.lightGray;
	private MyFrame frame;
	private Game game;
	private GameBoard gameBoard;
	private Map numberTiles;
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public GUI() {
		game = new Game();
		frame = new MyFrame();
		frame.setFocusable(true);
		frame.addKeyListener(new MyFrame());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setNumberTiles();
		gameBoard = new GameBoard();
		
		//Panel
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout());
		northPanel.setPreferredSize(new Dimension(width,50));
		
		JLabel gameLabel = new JLabel("2048",SwingConstants.CENTER);
		gameLabel.setFont(new Font("Serif",Font.BOLD,20));
		
		northPanel.add(gameLabel);
		northPanel.add(new JLabel("Score: ", SwingConstants.CENTER));
		northPanel.add(new JLabel("High score: ",SwingConstants.CENTER));
		northPanel.setBackground(background);
		
		JPanel westBuffer = new JPanel();
		westBuffer.setPreferredSize(new Dimension(marginSize, gameBoardSize));
		westBuffer.setBackground(background);
		
		JPanel eastBuffer = new JPanel();
		eastBuffer.setPreferredSize(new Dimension(marginSize, gameBoardSize));
		eastBuffer.setBackground(background);
		
		JPanel southBuffer = new JPanel();
		southBuffer.setPreferredSize(new Dimension(width, gameBoardSize));
		southBuffer.setBackground(background);
		
		//add panel to frame
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);
		frame.getContentPane().add(westBuffer, BorderLayout.WEST);
		frame.getContentPane().add(eastBuffer, BorderLayout.EAST);
		frame.getContentPane().add(southBuffer, BorderLayout.SOUTH);
		frame.getContentPane().add(gameBoard, BorderLayout.CENTER);
		
		frame.getContentPane().setPreferredSize(new Dimension(width,height));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public void setNumberTiles() {
		numberTiles = new Hashtable();
		
		numberTiles.put(0, new ImageIcon("tiles.empty.png"));
		numberTiles.put(1, new ImageIcon("tiles/empty.png"));
		numberTiles.put(2, new ImageIcon("tiles/tile02.png"));
		numberTiles.put(4, new ImageIcon("tiles/tile04.png"));
		numberTiles.put(8, new ImageIcon("tiles/tile08.png"));
		numberTiles.put(16, new ImageIcon("tiles/tile16.png"));
		numberTiles.put(32, new ImageIcon("tiles/tile32.png"));
		numberTiles.put(64, new ImageIcon("tiles/tile64.png"));
		numberTiles.put(128, new ImageIcon("tiles/tile128.png"));
		numberTiles.put(256, new ImageIcon("tiles/tile256.png"));
		numberTiles.put(512, new ImageIcon("tiles/tile512.png"));
		numberTiles.put(1024, new ImageIcon("tiles/tile1024.png"));
		numberTiles.put(2048, new ImageIcon("tiles/tile2048.png"));

	}

	class GameBoard extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(new Color(20,20,20));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			int[][] board = game.getGameBoard();
			
			for(int y=1; y<5; y++) {
				for(int x=1; x<5;x++){
					int X = (8*x) + (64*(x-1));
					int Y = (8*y) + (64*(y-1));
						
					int thisNumber = board[y-1][x-1];
						
					if(numberTiles.containsKey(thisNumber)) {
						ImageIcon thisTile = (ImageIcon) numberTiles.get(thisNumber);
						thisTile.paintIcon(this, g, X, Y);
					}	
				}
			}
		}
	}

	class MyFrame extends JFrame implements KeyListener{

		private static final long serialVersionUID = 1L;

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_UP) {
				game.pushUp();
				game.addNewNumber();
				gameBoard.repaint();
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				game.pushDown();
				game.addNewNumber();
				gameBoard.repaint();
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				game.pushLeft();
				game.addNewNumber();
				gameBoard.repaint();
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				game.pushRight();
				game.addNewNumber();
				gameBoard.repaint();
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

	}


}

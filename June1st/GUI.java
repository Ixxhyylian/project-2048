package Game2048;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class GUI {
	private int height = 485;
	private int width = 320;
	private int gameBoardSize = 150;
	private int marginSize = 18;
	private Color background = Color.PINK;
	private MyFrame frame;
	private Game game;
	private GameBoard gameBoard;
	private Map numberTiles;
	
	private JLabel score;
	private JButton undo;
	
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
		
		//North Panel
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout());
		northPanel.setPreferredSize(new Dimension(width,50));
		
		JLabel gameLabel = new JLabel("2048",SwingConstants.CENTER);
		gameLabel.setFont(new Font("Serif",Font.BOLD,35));
		northPanel.add(gameLabel);
		
		score = new JLabel("Score: "+game.getScore(), SwingConstants.CENTER);
		northPanel.add(score);
		
		
		northPanel.setBackground(background);
		
		//West Panel
		JPanel westBuffer = new JPanel();
		westBuffer.setPreferredSize(new Dimension(marginSize, gameBoardSize));
		westBuffer.setBackground(background);
		
		//East Panel
		JPanel eastBuffer = new JPanel();
		eastBuffer.setPreferredSize(new Dimension(marginSize, gameBoardSize));
		eastBuffer.setBackground(background);
		
		//South Panel
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
		
		numberTiles.put(0, new ImageIcon("C:\\\\Users\\\\ADMIN\\\\eclipse-workspace\\\\2048\\\\src\\\\tiles/empty.png"));
		numberTiles.put(2, new ImageIcon("C:\\\\Users\\\\ADMIN\\\\eclipse-workspace\\\\2048\\\\src\\\\tiles/tile02.png"));
		numberTiles.put(4, new ImageIcon("C:\\\\Users\\\\ADMIN\\\\eclipse-workspace\\\\2048\\\\src\\\\tiles/tile04.png"));
		numberTiles.put(8, new ImageIcon("C:\\\\Users\\\\ADMIN\\\\eclipse-workspace\\\\2048\\\\src\\\\tiles/tile08.png"));
		numberTiles.put(16, new ImageIcon("C:\\\\Users\\\\ADMIN\\\\eclipse-workspace\\\\2048\\\\src\\\\tiles/tile16.png"));
		numberTiles.put(32, new ImageIcon("C:\\\\Users\\\\ADMIN\\\\eclipse-workspace\\\\2048\\\\src\\\\tiles/tile32.png"));
		numberTiles.put(64, new ImageIcon("C:\\\\Users\\\\ADMIN\\\\eclipse-workspace\\\\2048\\\\src\\\\tiles/tile64.png"));
		numberTiles.put(128, new ImageIcon("C:\\\\Users\\\\ADMIN\\\\eclipse-workspace\\\\2048\\\\src\\\\tiles/tile128.png"));
		numberTiles.put(256, new ImageIcon("C:\\\\Users\\\\ADMIN\\\\eclipse-workspace\\\\2048\\\\src\\\\tiles/tile256.png"));
		numberTiles.put(512, new ImageIcon("C:\\\\Users\\\\ADMIN\\\\eclipse-workspace\\\\2048\\\\src\\\\tiles/tile512.png"));
		numberTiles.put(1024, new ImageIcon("C:\\\\Users\\\\ADMIN\\\\eclipse-workspace\\\\2048\\\\src\\\\tiles/tile1024.png"));
		numberTiles.put(2048, new ImageIcon("C:\\\\Users\\\\ADMIN\\\\eclipse-workspace\\\\2048\\\\src\\\\tiles/tile2048.png"));

	}

	class GameBoard extends JPanel{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			int[][] board = game.getGameBoard();
			
			for(int y=1; y<5; y++) {
				for(int x=1; x<5;x++){
					int X = (8*x) + (64*(x-1));
					int Y = (8*y) + (64*(y-1));
						
					int thisNumber = board[y-1][x-1];
					System.out.println("thisNumber at board["+(y-1)+"]["+(x-1)+"]: "+thisNumber);
						
					if(numberTiles.containsKey(thisNumber)) {
						ImageIcon thisTile = (ImageIcon) numberTiles.get(thisNumber);
						thisTile.paintIcon(this, g, X, Y);
						System.out.println(thisTile);
					}	
				}
			}
		}
	}
	
	class LoseBoard extends JPanel{
		Font FeedbackFont = new Font("SansSerif",0,40);
		
		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			g.setFont(FeedbackFont);
			g.setColor(Color.RED);
			g.drawString("Game Over!",40, 150);
		}
	}
	
	class WinBoard extends JPanel{

		Font FeedbackFont = new Font("SansSerif",0,40);
		
		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(Color.black);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			g.setFont(FeedbackFont);
			g.setColor(Color.RED);
			g.drawString("You Win!",40, 150);
		}
	}

	class MyFrame extends JFrame implements KeyListener{

		private static final long serialVersionUID = 1L;

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
			if(game.getGameState() == GameState.Continue) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					game.pushUp();					
					game.checkState();
					game.printArray();
					gameBoard.repaint();
					
					updateScore();
				}
			
				else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					game.pushDown();					
					game.checkState();
					game.printArray();
					gameBoard.repaint();
					updateScore();
				}
			
				else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					game.pushLeft();					
					game.checkState();
					game.printArray();
					gameBoard.repaint();
					updateScore();
				}
			
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					game.pushRight();
					game.checkState();
					game.printArray();
					gameBoard.repaint();
					updateScore();
				}
				
				else if(e.getKeyCode() == KeyEvent.VK_U) {
					game.undo();
					game.printArray();
					gameBoard.repaint();
					updateScore();
				}
				else if(e.getKeyCode() == KeyEvent.VK_R) {
					game.redo();
					game.printArray();
					gameBoard.repaint();
					updateScore();
				}
			}
			System.out.println(game.getGameState());
			GameState state = game.getGameState();
			if(state == GameState.Gameover) {
				frame.getContentPane().remove(gameBoard);
				frame.getContentPane().add(new LoseBoard(), BorderLayout.CENTER);
				
				frame.getContentPane().validate();
			}
			else if(state == GameState.Win){
				frame.getContentPane().remove(gameBoard);
				frame.getContentPane().add(new WinBoard(), BorderLayout.CENTER);
				
				frame.getContentPane().validate();
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
		public void updateScore() {
			score.setText("Score: "+game.getScore());
		}
	}
	

}

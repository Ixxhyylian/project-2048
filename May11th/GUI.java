package Game2048;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GUI {
	private int height = 500;
	private int width = 400;
	private int gameBoardSize = 50;
	private int marginSize = 16;
	private Color background = Color.lightGray;
	private JFrame frame;
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public GUI() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameBoard gameBoard = new GameBoard();
		
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
	
	class GameBoard extends JPanel{
		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(new Color(20,20,20));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
	}

}

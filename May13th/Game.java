package Game2048;


import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	private int[][] gameBoard;
	private Random r = new Random();
	
	
	public Game() {
		gameBoard = new int[4][4];
		addNewNumber();
		addNewNumber();
	}
	
	public int[][] getGameBoard() {
		return gameBoard;
	}
	
	public void printArray() {
		for(int[] i : gameBoard) {
			System.out.format("%d%6d%6d%6d",i[0],i[1],i[2],i[3]);
			System.out.println();
		}
		System.out.println();
	}
	
	public void addNewNumber() {
		ArrayList<Integer> arrX = new ArrayList<>();
		ArrayList<Integer> arrY = new ArrayList<>();
		for( int x=0; x<4; x++) {
			for(int y=0; y<4; y++) {
				if(gameBoard[x][y] == 0) {
					arrX.add(new Integer(x));
					arrY.add(new Integer(y));
				}
			}
		}
		System.out.println("ArrayX:"+arrX);
		int choice = r.nextInt(arrX.size());
		System.out.println("Choice:"+choice);
		int numberChooser = r.nextInt(10);
		System.out.println("Number chooser:"+numberChooser);
		int newNumber = 1;
		
		if(numberChooser == 0) {
			newNumber = 2;
		}
		int x = arrX.get(choice);
		int y = arrY.get(choice);
		gameBoard[x][y] = newNumber;
	}
	
	public void pushUp() {
		System.out.println("Pushing up!!");
		boolean[] combined = {false, false, false, false};
		for(int y=0; y<4; y++) {
			for( int x=1; x<4; x++) {
				if(gameBoard[x][y] != 0) {
					int value = gameBoard[x][y];
					int X = x-1;
					
					while( (X>=0) && (gameBoard[X][y] == 0) ) {
						X--;
					}
					
					if(X == -1) {
						gameBoard[x][y] = 0;
						gameBoard[0][y] = value;
					}
					
					else if(gameBoard[X][y] != value) {
						gameBoard[x][y] = 0;
						gameBoard[X+1][y] = value;
					}
					
					else{
						if(combined[X]) {
							gameBoard[x][y] = 0;
							gameBoard[X+1][y] = value;
						}
						else {
							gameBoard[x][y] = 0;
							gameBoard[X][y] *= 2;
							combined[X] = true;
						}
					}
				}
			}
		}
	}
	
	public void pushDown() {
		System.out.println("Pushing down!!");
		boolean[] combined = {false, false, false, false};
		for(int y=0; y<4; y++) {
			for( int x=2; x>-1; x--) {
				if(gameBoard[x][y] != 0) {
					int value = gameBoard[x][y];
					int X = x+1;
					
					while( (X<=3) && (gameBoard[X][y] == 0) ) {
						X++;
					}
					
					if(X == 4) {
						gameBoard[x][y] = 0;
						gameBoard[3][y] = value;
					}
					
					else if(gameBoard[X][y] != value) {
						gameBoard[x][y] = 0;
						gameBoard[X-1][y] = value;
					}
					
					else{
						if(combined[X]) {
							gameBoard[x][y] = 0;
							gameBoard[X-1][y] = value;
						}
						else {
							gameBoard[x][y] = 0;
							gameBoard[X][y] *= 2;
							combined[X] = true;
						}
					}
				}
			}
		}
	}
	
	public void pushLeft() {
		System.out.println("Pushing left!!");
		boolean[] combined = {false, false, false, false};
		for(int x=0; x<4; x++) {
			for( int y=1; y<4; y++) {
				if(gameBoard[x][y] != 0) {
					int value = gameBoard[x][y];
					int Y = y-1;
					
					while( (Y>=0) && (gameBoard[x][Y] == 0) ) {
						Y--;
					}
					
					if(Y == -1) {
						gameBoard[x][y] = 0;
						gameBoard[x][0] = value;
					}
					
					else if(gameBoard[x][Y]!= value) {
						gameBoard[x][y] = 0;
						gameBoard[x][Y+1] = value;
					}
					
					else{
						if(combined[Y]) {
							gameBoard[x][y] = 0;
							gameBoard[x][Y+1] = value;
						}
						else {
							gameBoard[x][y] = 0;
							gameBoard[x][Y] *= 2;
							combined[Y] = true;
						}
					}
				}
			}
		}
	}
	
	public void pushRight() {
		System.out.println("Pushing right!!");
		boolean[] combined = {false, false, false, false};
		for(int x=0; x<4; x++) {
			for( int y=2; y>-1; y--) {
				if(gameBoard[x][y] != 0) {
					int value = gameBoard[x][y];
					int Y = y+1;
					
					while( (Y<=3) && (gameBoard[x][Y] == 0) ) {
						Y++;
					}
					
					if(Y == 4) {
						gameBoard[x][y] = 0;
						gameBoard[x][3] = value;
					}
					
					else if(gameBoard[x][Y]!= value) {
						gameBoard[x][y] = 0;
						gameBoard[x][Y-1] = value;
					}
					
					else{
						if(combined[Y]) {
							gameBoard[x][y] = 0;
							gameBoard[x][Y-1] = value;
						}
						else {
							gameBoard[x][y] = 0;
							gameBoard[x][Y] *= 2;
							combined[Y] = true;
						}
					}
				}
			}
		}
	}

}

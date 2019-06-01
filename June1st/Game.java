package Game2048;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;


public class Game {
	
	private int[][] gameBoard;
	private Random r = new Random();
	private GameState state;
	private int score;
	
    private Stack<int[][]> undo = new Stack<>();
    private Stack<int[][]> redo = new Stack<>();
    
	public Game() {
		gameBoard = new int[4][4];
		addNewNumber();
		addNewNumber();
		state = GameState.Continue;
		undo.push(gameBoard);
		System.out.println("Undo: "+undo);
	}
	
	public int[][] getGameBoard() {
		return gameBoard;
	}
	
	public GameState getGameState() {
		return state;
	}
	
	public int getScore() {
		return score;
	}
	
	public void printArray() {
		for(int[] i : gameBoard) {
			System.out.format("%d%6d%6d%6d",i[0],i[1],i[2],i[3]);
			System.out.println();
		}
		System.out.println();
	}


    public void addNewNumber() {
    	if(checkBoardFull()) {
    		return;
    	}
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
    	System.out.println("ArrayY:"+arrY);
    	int choice = r.nextInt(arrX.size());
    	System.out.println("Choice:"+choice);
    	int numberChooser = r.nextInt(10);
    	System.out.println("Number chooser:"+numberChooser);
    	int newNumber = 2;
	
    	if(numberChooser == 0) {
    		newNumber = 4;
    	}
    	int x = arrX.get(choice);
    	int y = arrY.get(choice);
    	gameBoard[x][y] = newNumber;
    	
    	undo.push(gameBoard);
    }

	
	public void pushUp() {
		System.out.println("Pushing up!!");
		for(int y=0; y<4; y++) {
			boolean[] combined = {false, false, false, false};
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
							//gameBoard[x][y] = 0;
						}
						else { //combine 2 numbers
							gameBoard[x][y] = 0;
							gameBoard[X][y] *= 2;
							score += gameBoard[X][y];
							combined[X] = true;
							//gameBoard[x][y] = 0;
						}
					}
				}
			}
		}
		addNewNumber();
	}

	
	public void pushDown() {
		System.out.println("Pushing down!!");
		for(int y=0; y<4; y++) {
			boolean[] combined = {false, false, false, false};
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
							score += gameBoard[X][y];
							combined[X] = true;
						}
					}
				}
			}
		}
		addNewNumber();
	}
	
	public void pushLeft() {
		System.out.println("Pushing left!!");
		for(int x=0; x<4; x++) {
			boolean[] combined = {false, false, false, false};
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
							score += gameBoard[x][Y];
							combined[Y] = true;
						}
					}
				}
			}
		}
		addNewNumber();
	}
	
	public void pushRight() {
		System.out.println("Pushing right!!");
		for(int x=0; x<4; x++) {
			boolean[] combined = {false, false, false, false};
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
							score += gameBoard[x][Y];
							combined[Y] = true;
						}
					}
				}
			}
		}
		addNewNumber();
	}
	
	public void checkState() {
		if(checkFor2048())
			state = GameState.Win;
		else if(checkBoardFull()) {
			if(checkHasMoves()) {
				state = GameState.Continue;
			}
			else
				state = GameState.Gameover;
		}
		else {
			state = GameState.Continue;
		}
	}
	
	public boolean checkFor2048() {
		for(int x=0; x<4; x++) {
			for(int y=0; y<4; y++) {
				if(gameBoard[x][y] == 2048)
					return true;
			}
		}
		return false;
	}
	
	public boolean checkBoardFull() {
		for(int x=0; x<4; x++) {
			for(int y=0; y<4; y++) {
				if(gameBoard[x][y] == 0)
					return false;
			}
		}
		return true;
	}
	
	public boolean checkHasMoves() {
		for(int x=0; x<4; x++) {
			for(int y=0; y<4; y++) {
				if(x == 0){
					if(y != 0) {
						if(gameBoard[x][y] == gameBoard[x][y-1])
							return true;
					}
				}
				else {
					if(y != 0) {
						if(gameBoard[x][y] == gameBoard[x][y-1])
							return true;
					}
					if(gameBoard[x][y] == gameBoard[x-1][y])
						return true;
				}
			}
		}
		return false;
	}
	
    
	public void redo() {
        if (!redo.isEmpty()) {
            gameBoard = redo.pop();
            undo.push(gameBoard);
        }
    }


    public void undo() {
        if (!undo.isEmpty() && undo.size() > 1) {
        	gameBoard = undo.get(undo.size() - 1);
            redo.push(undo.pop());
        }
    }
}

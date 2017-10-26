public class Board {
	
	char emptySpace = ' ';
	
	int row;
	int col;
	
	char playerOne;
	char playerTwo;
	char o;
	char t;
	
	int p;
	//int r;
	int c;

	char[][] board;
	
	//creates board of default size
	public Board() {
		board = new char[6][7];
		
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[0].length; j++){
				board[i][j] = emptySpace;
			}
		}
		
		playerOne = 'G';
		playerTwo = 'R';
	}
	
	//creates board of custom size
	public Board(int row, int col) {
		
		this.row = row;
		this.col = col;
		
		if (row <= 0 || col <= 0){
			row = 6;
			col = 7;
		}
		
		board = new char[row][col];
		
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[0].length; j++){
				board[i][j] = emptySpace;
			}
		}
		
		playerOne = 'G';
		playerTwo = 'R';
		
	}

	//returns number of rows
	public int getNumRows() {
		if (row <= 0 || col <= 0) {
			row = 6;
			col = 7;
		}
		return row;
	}
	
	//returns number of columns
	public int getNumCols() {
		if (row <= 0 || col <= 0) {
			row = 6;
			col = 7;
		}
		return col;
	}
	
	//returns char representing player 1
	public char getPlayerOne(){
		return playerOne;
	}
	
	//returns char representing player 2
	public char getPlayerTwo(){
		return playerTwo;
	}

	//sets char presenting player 1
	public void setPlayerOne(char o){
		this.playerOne = o; 
	}
	
	//sets char representing player 2
	public void setPlayerTwo(char t){
		this.playerTwo = t;
	}
	
	//returns the char representing token at location row, col, returns '\0' if indices are invalid 
	public char getToken(int row, int col){
		if (row >= board.length || row < 0 || col < 0 || col >= board[0].length) {
			return '\0';
		} 
		
		return board[row][col];
	}

	
	//returns true if a token is still able to be placed onto the board, false otherwise
	public boolean canPlay(){		
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[0].length; j++) {
				if (board[0][i] == emptySpace){
					return true;
				}
			}
		}
		return false;
	}
	
	//places the appropriate token for player p in column c. returns true if successful, false otherwise.
	public boolean play(int p, int c){
		
		if (p != 1 && p != 2){
			return false;
		}
		
		if (getToken(0, c) != emptySpace) {
			return false;
		}
		
		if (c > board[0].length) {
			return false;
		}
		
		if (canPlay() == false) {
			return false;
		}
		
		char token = 'a';
		
		if (p == 1){
			token = getPlayerOne();
		} else if (p == 2){
			token = getPlayerTwo();
		}
		
		for (int i = board.length-1; i >= 0; i--) {
			if (board[i][c] == emptySpace){
				board[i][c] = token;
				return true;
			
			}	
		}
		return false;
	}
	
	//checks for horizontal winner
	public int checkHorizontal(){
		int counter1 = 0;
		int counter2 = 0;
		
		for (int i = 0; counter1 < 3 && counter2 < 3 && i < board.length; i++){
			counter1 = 0;
			counter2 = 0;
			for (int j = 0; j < board[0].length-1; j++){
				if (board[i][j] == getPlayerOne() && board[i][j+1] == getPlayerOne()){
					counter1++;
					if (counter1 == 3) {
						return 1;
					}
				} else if (board[i][j] == getPlayerTwo() && board[i][j+1] == getPlayerTwo()) {
					counter2++;
					if (counter2 == 3) {
						return 2;
					}
				} else {
					counter1 = 0;
					counter2 = 0;
				}
			}	
		}
		
		return -1;
	}
	
	//checks for vertical winner
	public int checkVertical(){
		int counter1 = 0;
		int counter2 = 0;	
		for (int i = 0; counter1 < 3 && counter2 < 3 && i < board[0].length; i++){
			for (int j = 0; j < board.length-1; j++){
				if (board[j][i] == getPlayerOne() && board[j+1][i] == getPlayerOne()){
					counter1++;
					if (counter1 == 3) {
						return 1;
					} 
				} else if (board[j][i] == getPlayerTwo() && board[j+1][i] == getPlayerTwo()) {
					counter2++;
					if (counter2 == 3) {
						return 2;
					}
				} else {
					counter1 = 0;
					counter2 = 0;
				}
			}	
		}
		return -1;
	}

	//checks for diagonal winner left to right
	public int checkLeftRightDiag(){
		int counter1 = 0;
		int counter2 = 0;
		
		int i = 0;
		int j = 0;
		int firstRow = 0;
		int firstCol = 0;

		for (firstRow = 0; firstRow < board.length-3 && counter1 < 3 && counter2 < 3; firstRow++) {
			counter1 = 0;
			counter2 = 0;
			for (i = firstRow, j = 0; i < board.length-1 && j < board[0].length-1; i++, j++) {
				if (board[i][j] == getPlayerOne() && board[i+1][j+1] == getPlayerOne()) {
					counter1++;
					if (counter1 >= 3) {
						return 1;
					}
				} else if (board[i][j] == getPlayerTwo() && board[i+1][j+1] == getPlayerTwo()) {
					counter2++;
					if (counter2 >= 3) {
						return 2;
					}
				} else {
					counter1 = 0;
					counter2 = 0;
				}
			}
		}
		
		
		for (firstCol = 1; firstCol < board[0].length-3 && counter1 < 3 && counter2 < 3; firstCol++) {
			counter1 = 0;
			counter2 = 0;
			for (i = 0, j = firstCol; i < board.length-1 && j < board[0].length-1 && j > 0; i++, j++) {
				if (board[i][j] == getPlayerOne() && board[i+1][j+1] == getPlayerOne()) {
					counter1++;
					if (counter1 >= 3) {
						return 1;
					}
				} else if (board[i][j] == getPlayerTwo() && board[i+1][j+1] == getPlayerTwo()) {
					counter2++;
					if (counter2 >= 3) {
						return 2;
					}
				} else {
					counter1 = 0;
					counter2 = 0;
				}
			}
		}
		
		return -1;
		
	}
	
	//checks for diagonal winner right to left
	public int checkRightLeftDiag(){
		int counter1 = 0;
		int counter2 = 0;
		
		int i = 0;
		int j = 0;
		int firstRow;
		int firstCol;

		for (firstRow = 0; firstRow < board.length-3 && counter1 < 3 && counter2 < 3; firstRow++) {
			counter1 = 0;
			counter2 = 0;
			for (i = firstRow, j = board[0].length-1; i < board.length-1 && j > 0; i++, j--) {
				if (board[i][j] == getPlayerOne() && board[i+1][j-1] == getPlayerOne()) {
					counter1++;
					if (counter1 >= 3) {
						return 1;
					}
				} else if (board[i][j] == getPlayerTwo() && board[i+1][j-1] == getPlayerTwo()) {
					counter2++;
					if (counter2 >= 3) {
						return 2;
					}
				} else {
					counter1 = 0;
					counter2 = 0;
				}
			}
		}
		
		
		for (firstCol = board[0].length-2; firstCol > 2 && counter1 < 3 && counter2 < 3; firstCol--) {
			counter1 = 0;
			counter2 = 0;
			for (i = 0, j = firstCol; i < board.length-1 && j > 0; i++, j--) {
				if (board[i][j] == getPlayerOne() && board[i+1][j-1] == getPlayerOne()) {
					counter1++;
					if (counter1 >= 3) {
						return 1;
					}
				} else if (board[i][j] == getPlayerTwo() && board[i+1][j-1] == getPlayerTwo()) {
					counter2++;
					if (counter2 >= 3) {
						return 2;
					}
				} else {
					counter1 = 0;
					counter2 = 0;
				}
			}
		}
		
		return -1;
		
	}
	
	//returns either the ID of the player who has won (1 or 2) 
	//OR 0 if the game has ended in a tie 
	//OR -1 if nobody has won yet
	public int isFinished(){
		
		int winner = 0;

		if (checkHorizontal() == 1 || checkHorizontal() == 2) {
			winner = checkHorizontal();
			return winner;
		} 
		
		if (checkVertical() == 1 || checkVertical() == 2) {
			winner = checkVertical();
			return winner;
		}

		if (checkLeftRightDiag() == 1 || checkLeftRightDiag() == 2) {
			winner = checkLeftRightDiag();
			return winner;
		} 

		if (checkRightLeftDiag() == 1 || checkRightLeftDiag() == 2) {
			winner = checkRightLeftDiag();
			return winner;
		} 
		
		if (canPlay() == false && winner == 0) {
			return 0;
		} else {
			return -1;
		}
		
	}

}


public class AIPlayer implements Player{
	
	public interface Player{

		public int playToken();
		public int getPlayerID();
		public void lastMove(int c);
		public void reset();
	}
//do not change the line above	
	
	int c;
	int playerID;
	int row;
	int col;
	private char[][] AIBoard;
	
	
	//constructor takes the player id for this player, and the number of 
	// rows and columns of the board we're playing on
	public AIPlayer(int playerID, int row, int col){
		this.playerID = playerID;
		this.row = row;
		this.col = col;
		
		if (row < 0) {
			row = 6;
		}
		
		if (col < 0) {
			col = 7;
		}
		
		this.AIBoard = new char[row][col];
		for (int i = 0; i < AIBoard.length; i++) {
			for (int j = 0; j < AIBoard[0].length; j++) {
				AIBoard[i][j] = ' ';
			}
		}
		
	}
	
	//used to notify your AI player of the other players last move
	public void lastMove(int c) {
		System.out.println("Last move was played in column " + c + ".");
		
		if (col >= 0 && col < AIBoard[0].length){
			for (int i = AIBoard.length-1; i >= 0; i--) {
				if (AIBoard[i][c] == ' '){
					AIBoard[i][c] = 'H';
					break;
				}
			}
		}
		
	}
	
	
	//returns column of where to play a token
	public int playToken(){
		
		//if board is full don't play
		for (int f = 0; f < AIBoard[0].length; f++) {
			if (AIBoard[0][f] == ' ') {	
			} else {
				break;
			}
		}
		
		int c = 0;
	
		/*
		for (int outerLoop = AIBoard.length-1; outerLoop >= 0; outerLoop--) {
			for (int i = outerLoop, c = AIBoard[0].length/2; i >= 0, c >= 0; i--, c--) {
				
			}
		}
		*/
		
		for (int j = AIBoard.length-1; j >= 0;) {
			for (int i = j; i >=0; i--) {
				for (c = AIBoard[0].length/2; c >= 0; c--) {
					if (AIBoard[i][c] == ' ') {
						System.out.println("First C is here " + c);
						return c;
					} else {
					c--;
				}
			}
		}
		
		for (int i = AIBoard.length-1; i >=0; i--) {
			for (c = AIBoard[0].length; c >= AIBoard[0].length/2; c--) {
				if (AIBoard[i][c] == ' ') {
					System.out.println("Second C is here " + c);
					return c;
				}
			}
		}
		
		char[][] humanBoard = AIBoard;
		
		for (int i = humanBoard.length-1; i >= 0; i--) {
			for (c = 0; c < humanBoard[0].length; c++) {
				if (humanBoard[i][c] == ' ') {
					humanBoard[i][c] = 'H';
				}
				if (checkHorizontal(humanBoard) == true || checkVertical(humanBoard) == true || checkRightLeftDiag(humanBoard) == true || checkLeftRightDiag(humanBoard) == true) {
					System.out.println("Third C is here " + c);
					return c;
				}
			}
		}
		
		
		c = (int) Math.random() * AIBoard[0].length;
		while (c < 0 || c > AIBoard[0].length || AIBoard[0][c] != ' '){
			c = (int) Math.random() * AIBoard[0].length;
		}
		
			System.out.println(c);
			return c;
		
		
			}
		return c;
		}
	
		
		/*
		for (int i = 0; i < col; i++) {
			if (playing opponent token will result in a win for opponent) {
				play i;
			}
		}
		*/
	
	
	
	
	//get this player's id
	public int getPlayerID(){
		return playerID;
		
	}
	
	//resets the state of the player in preparation for a new game
	public void reset(){
		for (int i = 0; i < AIBoard.length; i++) {
			for (int j = 0; j < AIBoard[0].length; j++) {
				AIBoard[i][j] = ' ';
			}
		}
		
	}
	
	//checks for horizontal winner
		public boolean checkHorizontal(char[][] board){
			int hCounter = 0;
			
			for (int i = 0; hCounter < 3 && i < board.length; i++){
				hCounter = 0;
				for (int j = 0; j < board[0].length-1; j++){
					if (board[i][j] == 'H' && board[i][j+1] == 'H'){
						hCounter++;
						if (hCounter == 3) {
							return true;
						}
					}
					hCounter = 0;
				}
			}
			return false;
		}
		
		//checks for vertical winner
		public boolean checkVertical(char[][] board){
			int hCounter = 0;
			
			for (int i = 0; hCounter < 3 && i < board[0].length; i++){
				for (int j = 0; j < board.length-1; j++){
					if (board[j][i] == 'H' && board[j+1][i] == 'H'){
						hCounter++;
						if (hCounter == 3) {
							return true;
						} 
					}
				}
				hCounter = 0;
			}
			return false;
		}

		//checks for diagonal winner left to right
		public boolean checkLeftRightDiag(char[][] board){
			int hCounter = 0;
			
			int i = 0;
			int j = 0;
			int firstRow = 0;
			int firstCol = 0;

			for (firstRow = 0; firstRow < board.length-3 && hCounter < 3; firstRow++) {
				hCounter = 0;
	
				for (i = firstRow, j = 0; i < board.length-1 && j < board[0].length-1; i++, j++) {
					if (board[i][j] == 'H' && board[i+1][j+1] == 'H') {
						hCounter++;
						if (hCounter >= 3) {
							return true;
						}
					}
					hCounter = 0;
				}
					
			}
			
			
			for (firstCol = 1; firstCol < board[0].length-3 && hCounter < 3; firstCol++) {
				hCounter = 0;
				
				for (i = 0, j = firstCol; i < board.length-1 && j < board[0].length-1 && j > 0; i++, j++) {
					if (board[i][j] == 'H' && board[i+1][j+1] == 'H') {
						hCounter++;
						if (hCounter >= 3) {
							return true;
						}
					}
					hCounter = 0;
				}
			}
			
			return false;
			
		}
		 
		//checks for diagonal winner right to left
		public boolean checkRightLeftDiag(char[][] board){
			int hCounter = 0;
			
			int i = 0;
			int j = 0;
			int firstRow;
			int firstCol;

			for (firstRow = 0; firstRow < board.length-3 && hCounter < 3; firstRow++) {
				hCounter = 0;
				
				for (i = firstRow, j = board[0].length-1; i < board.length-1 && j > 0; i++, j--) {
					if (board[i][j] == 'H' && board[i+1][j-1] == 'H') {
						hCounter++;
						if (hCounter >= 3) {
							return true;
						}
					}
					hCounter = 0;
				}
				
			}
			
			
			for (firstCol = board[0].length-2; firstCol > 2 && hCounter < 3; firstCol--) {
				hCounter = 0;
				
				for (i = 0, j = firstCol; i < board.length-1 && j > 0; i++, j--) {
					if (board[i][j] == 'H' && board[i+1][j-1] == 'H') {
						hCounter++;
						if (hCounter >= 3) {
							return true;
						}
						hCounter = 0;
					}
				}
			}
			
			return false;
			
		}
	
	
}
public class HumanPlayer implements Player{
//do not change the line above	
	
	public interface Player{

		public int playToken();
		public int getPlayerID();
		public void lastMove(int c);
		public void reset();
	}
	
	//Define your fields here
	int playerID;
	int row;
	int col;
	int r;
	int c;

	char[][] practice;
	
	//Board practice = new Board(row, col);
	
	//constructor takes the player ID for this player, and the number of 
	// rows and columns of the board we're playing on
	
	public HumanPlayer(int playerID, int row, int col){
		this.playerID = playerID;
		this.row = row;
		this.col = col;
		
		if (row < 0) {
			row = 6;
		}
		
		if (col < 0) {
			col = 7;
		}
		
		this.practice = new char[row][col];
		for (int i = 0; i < practice.length; i++) {
			for (int j = 0; j < practice[0].length; j++) {
				practice[i][j] = ' ';
			}
		}
		
	}
	
	
	//used to notify your AI player of the other players last move
	public void lastMove(int c) {
		System.out.println("Last move was played in column " + c + ".");
		
		if (c > 0 && c < practice[0].length){
			for (int i = practice.length-1; i >= 0; i--) {
				if (practice[i][c] == ' '){
					practice[i][c] = 'O';
					break;
				}
			}
		}
		
	}
	
	//returns column of where to play a token
	public int playToken(){
		
		System.out.println("Where would you like to place the token?");
		c = IO.readInt();
		r = row - 1;
		
		//error check for out of bounds 
		while (c < 0 || c >= col) {
			System.out.println("Where would you like to place the token?");
			c = IO.readInt();
		}
		
		//error check if the column is full
		while (practice[0][c] != ' ') {
			System.out.println("Where would you like to place the token?");
			c = IO.readInt();
		}

		//fills in board with the token
		while (r >= 0) {
			if (practice[r][c] == ' ') {
				practice[r][c] = 'T';
				return c;
			} else {
				r--;
			}
		}
	
	return c;
		
	}
	
	//get this player's id
	public int getPlayerID(){
		return playerID;
	}
	
	//resets the state of the player in preparation for a new game
	public void reset(){
		for (int i = 0; i < practice.length; i++) {
			for (int j = 0; j < practice[0].length; j++) {
				practice[i][j] = ' ';
			}
		}
		
	}

	
}
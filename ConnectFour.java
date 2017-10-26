public class ConnectFour{
	
	//method to print board
	public static void printBoard(Board practice) {
		for (int i = 0; i < practice.board.length; i++){
			for (int j = 0; j < practice.board[0].length; j++){
				System.out.print("|" + practice.board[i][j] + "|");
			}
			System.out.println();
		}
		for(int i=0; i <= practice.board.length; i++){
			System.out.print(" " + i + " ");
		}
		System.out.println();
		return;
	}

	//method to print win statement after checking isFinished after each play
	public static void checkWinner(Board practice) {
		int winner = practice.isFinished();
		
		if (winner == 1) {
			System.out.println("The winner is player 1!");
		} else if (winner == 2) {
			System.out.println("The winner is player 2!");
		} else if (winner == 0) {
			System.out.println("The game ended in a tie.");
		} 
		
		return;
	}	
	
	public static void main(String[] args){
		
		GIO.displayMessage("Welcome to Connect Four!");
		
		
		//Create new board object
		Board practice = new Board();
		
		
		
		CFGUI board = new CFGUI(practice, ChipColor.BLACK, ChipColor.RED);
		
		//Set player tokens for player 1 and player 2
		practice.setPlayerOne('G');
		practice.setPlayerTwo('R');
		
		//Create Player objects
		//Note, the code below works because of the interface Player. All classes that "implement" Player can be
		// typed as Player. Makes switching from Human to AI Players really easy. For a challenge, you might
		// consider:
		//		1. asking the user whether he/she wants to play against a human or a computer
		//		2. implementing multiple AI players (easy, med, hard) that the user can choose to play against
		
		Player p1 = new HumanPlayer(1,6,7);
		Player p2 = new HumanPlayer(2,6,7); //comment this line when testing AIPlayer
		
		//Player p2 = new AIPlayer(2,6,7); //uncomment this line when testing AIPlayer
		
		printBoard(practice);
		
		
		while (practice.canPlay() == true) {
			
			System.out.println("Player 1: ");
			int c1 = p1.playToken();
			
			if(c1 < practice.board[0].length && practice.getToken(0, c1) == ' '){
				practice.play(1, c1);
			} else {
				while (c1 >= practice.board[0].length || practice.getToken(0, c1) != ' ') {
					System.out.println("Input invalid. Enter a value less than or equal to the column parameter or a column that is not full: ");
					c1 = IO.readInt();
					practice.play(1, c1);
					board.redraw();
				}
			}
			
			printBoard(practice);
			board.redraw();
			checkWinner(practice);
			
			if (practice.isFinished() != -1) {				
				int winner = practice.isFinished();
				board.gameOver(winner);
				board.close();
				return;
			}
			
			System.out.println("Player 2: ");
			int c2 = p2.playToken();
			
			if (c2 < practice.board[0].length && practice.getToken(0, c2) == ' '){
				practice.play(2, c2);
			} else {
				while (c2 >= practice.board[0].length || practice.getToken(0, c2) != ' ') {
					System.out.println("Input invalid. Enter a value less than or equal to the column parameter or a column that is not full: ");
					c2 = IO.readInt();
					practice.play(2, c2);
					board.redraw();
				}
			}
			
			printBoard(practice);
			board.redraw();
			checkWinner(practice);
			
			if (practice.isFinished() != -1) {				
				int winner = practice.isFinished();
				board.gameOver(winner);
				board.close();
				return;
			}
		}

	}
}
	

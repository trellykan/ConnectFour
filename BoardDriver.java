
public class BoardDriver {

	//method to print the board
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
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Connect 4!");
		
		System.out.println("Set board row size: ");
		int row = IO.readInt();
		
		System.out.println("Set board column size: ");
		int col = IO.readInt();
		
		Board practice = new Board(row, col);
		CFGUI board = new CFGUI(practice, ChipColor.GREEN, ChipColor.RED);

		
		System.out.println("Set character for Player One: ");
		char o = IO.readChar();
		practice.setPlayerOne(o);
		
		System.out.println("Set character for Player Two: ");
		char t = IO.readChar();
		practice.setPlayerTwo(t);
		
		//int numCol = practice.getNumCols() - 1;
		
		printBoard(practice);
		
		while (practice.canPlay() == true) {
			
			System.out.println("Player 1 column #: ");
			int c1 = IO.readInt();
			
			if(c1 < practice.board[0].length && practice.getToken(0, c1) == ' '){
				practice.play(1, c1);
			} else {
				while(c1 >= practice.board[0].length || practice.getToken(0, c1) != ' ') {
					System.out.println("Input invalid. Enter a value less than or equal to the column parameter or a column that is not full: ");
					c1 = IO.readInt();
					practice.play(1, c1);
					board.redraw();
				}
			}
			
			printBoard(practice);
			checkWinner(practice);
			
			if (practice.isFinished() != -1) {
				return;
			}
			
			System.out.println("Player 2 column #: ");
			int c2 = IO.readInt();
			
			if(c2 < practice.board[0].length && practice.getToken(0, c2) == ' '){
				practice.play(2, c2);
			} else {
				while(c2 >= practice.board[0].length || practice.getToken(0, c2) != ' ') {
					System.out.println("Input invalid. Enter a value less than or equal to the column parameter or a column that is not full: ");
					c2 = IO.readInt();
					practice.play(2, c2);
				}
			}
			
			printBoard(practice);
			checkWinner(practice);
			
			if (practice.isFinished() != -1) {
				return;
			}

		}
	}
}

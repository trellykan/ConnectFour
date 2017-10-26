
public class TestBoardDriver {

	public static void main(String[] args) {
		
		//tests constructors
		Board test = new Board(-1, 3);
		Board defaultTest = new Board();
		
		//fills defaultTest to print it
		for (int k = 0; k < defaultTest.board.length; k++){
			for (int t = 0; t < defaultTest.board[0].length; t++){
				defaultTest.board[k][t] = 'A';
			}
		}
		
		//tests play method 
		test.play(1, 0);
		test.play(1, 0);
		test.play(2, 1);
		test.play(1, 2);
		test.play(2, 2);
		test.play(2, 1);
		
		printBoard(test);
		System.out.println("\n");
		printBoard(defaultTest);
		
		//tests getToken method
		System.out.println("The token here is " + test.getToken(0, 0) + ".");
		if (test.getToken(test.board.length, test.board[0].length) == '\0'){
			System.out.println("This is the null test.");
		}
		
		//tests getNumRows and getNumCols methods
		System.out.println(test.getNumRows());
		System.out.println(test.getNumCols());
		
		//tests setPlayerOne and setPlayerTwo methods and getPlayerOne and getPlayerTwo methods
		test.setPlayerOne('X');
		test.setPlayerTwo('Q');
		
		System.out.println("The char for Player One is: " + test.getPlayerOne());
		System.out.println("The char for Player Two is: " + test.getPlayerTwo());
		
		
		//tests canPlay method
		if (test.canPlay() == false) {
			System.out.println("Test board is full.");
		}
		
		if (test.canPlay() == true) {
			System.out.println("Test board is playable.");
		}
		
		//tests isFinished and setPlayerOne
		defaultTest.setPlayerOne('A');
		if (defaultTest.isFinished() == 1){
			System.out.println("Player 1 is the winner!");
		}

	}
	
	public static void printBoard(Board test) {
		for (int i = 0; i < test.board.length; i++){
			for (int j = 0; j < test.board[0].length; j++){
				System.out.print("|" + test.board[i][j] + "|");
			}
			System.out.println();
		}
		return;
	}

}


public class ConnectFourTest {
	public static void main (String [] args) {
		Player p1 = new HumanPlayer(1,6,7);
		Player p2 = new HumanPlayer(2,6,7);
		
		//test lastMove(int c)
		
		p1.lastMove(1);
		
		//test getPlayerID
		
		System.out.println(p1.getPlayerID());
	}
}

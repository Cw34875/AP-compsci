
/**
 * Write a description of class Simulation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Simulation {

	final static boolean DEBUGGING = false;
	
	public Simulation (int gameCount,Player p1, Player p2) {
		//Player p1 = new Player( );
		//Player p2 = new Player ( );
		for (int k=0; k<gameCount; k++) {
			boolean p1cooperated = p1.cooperates ( );
			boolean p2cooperated = p2.cooperates ( );
			if (p1cooperated) {
				if (p2cooperated) {
					p1.increaseScore (3);
					p2.increaseScore (3);
				} else {
					p1.increaseScore (0);
					p2.increaseScore (5);
				}
			} else {
				if (p2cooperated) {
					p1.increaseScore (5);
					p2.increaseScore (0);
				} else {
					p1.increaseScore (1);
					p2.increaseScore (1);
				}
			}
			if (DEBUGGING) {
				System.out.println ("p1 " + p1cooperated + ", p2 " + p2cooperated);
				System.out.println ("p1 score = " + p1.score ( ) + ", p2 score = " + p2.score ( ));
			}
			p1.remember (p2cooperated);
			p2.remember (p1cooperated);
		}
		System.out.println ("Player 1 has " + p1.score ( ));
		System.out.println ("Player 2 has " + p2.score ( ));
	}
	
	public Simulation ( ) {
		//this (200);
	}
	
		public static void main (String [ ] args) {
		Player p1 = new Player(); 
		Player p2 = new Player();
		Simulation s = new Simulation (p1, p2);
	}
}
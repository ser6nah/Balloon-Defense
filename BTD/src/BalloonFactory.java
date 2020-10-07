import java.util.LinkedList;

/*
 * Name: Serena Han
 * 
 * This class's object, when instantiated, creates a LinkedList that
 * creates Balloon objects with a level of difficulty dependent
 * on the level inputed. This object can then be used in the game's engine
 * to add balloons onto the board for the user's towers to shoot at.
 */

public class BalloonFactory {

	private LinkedList<Balloon> balloonsInLine;

	/*
	 * Constructs a BalloonFactory object that produces more balloons
	 * and balloons with more lives as the levels increase.
	 * 
	 * Parameters: 
	 * 	level = level that user is on
	 */
	public BalloonFactory(int level) {
		balloonsInLine = new LinkedList<Balloon>();
		for(int i = 0; i < level * 20; i++) {
			if(Math.random() < .2 * level) {
				balloonsInLine.add(new GreenBalloon());
			}
			else {
				balloonsInLine.add(new RedBalloon());
			}
		}
	}
	
	/**
	 * @return the balloonsInLine
	 */
	public LinkedList<Balloon> getBalloonsInLine() {
		return balloonsInLine;
	}

	/**
	 * @param balloonsInLine the balloonsInLine to set
	 */
	public void setBalloonsInLine(LinkedList<Balloon> balloonsInLine) {
		this.balloonsInLine = balloonsInLine;
	}

	/*
	 * Represents the BalloonFactory through a String to be tested in the main
	 */

	public String toString() {
		String result = "";
		for(Balloon b : balloonsInLine) {
			result += "\n" + b.toString();
		}
		return result;
	}
	
	public static void main(String[] args) {
		BalloonFactory test = new BalloonFactory(1);
		System.out.println(test);
	}
}

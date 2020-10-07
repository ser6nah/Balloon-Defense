import javafx.scene.image.Image;

/*
 * Name: Serena Han
 * 
 * Subclass of Balloon that instantiates a RedBalloon object with 1 life, 
 * its Image, its coordinates, and the $20 that is earned by the user upon its popping
 */
public class RedBalloon extends Balloon {
//
//	public static final Image RED =  new Image("redballoon copy 2.png", 80, 100, false, false);
	
	/*
	 * Constructs a RedBalloon object
	 * 
	 * Parameters:
	 * 	xCoordinate = x-coordinate of RedBalloon in pixels
	 * 	yCoordinate = y-coordinate of RedBalloon in pixels
	 */
	public RedBalloon(double xCoordinate, double yCoordinate) {
		super(1, 20, new Image("redballoon copy 2.png", 80, 100, false, false), xCoordinate, yCoordinate);
	}
	
	/*
	 * Default constructor used for creating balloons in 
	 * Balloon Factory before their specific coordinates are needed
	 * 
	 * Parameters: none
	 */
	public RedBalloon() {
		super(1, 20, new Image("redballoon copy 2.png", 80, 100, false, false), -100, 120*3);
	}
	
	/* Returns a String denoting that it is a RedBalloon
	 * 
	 * Parameters: none
	 * 
	 * Returns: String
	 */
	public String toString() {
		return "Red Balloon";
	}

	public static void main(String[] args) {
//		Balloon test = new RedBalloon(0, 0);
//		System.out.println(test);

	}

}

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * Name: Serena Han
 * 
 * Subclass of Balloon that instantiates a GreenBalloon object with 1 life, 
 * its Image, its coordinates, and the $50 that is earned by the user upon its popping
 */
public class GreenBalloon extends Balloon {

	/*
	 * Constructs a GreenBalloon object
	 * 
	 * Parameters:
	 * 	xCoordinate = x-coordinate of RedBalloon in pixels
	 * 	yCoordinate = y-coordinate of RedBalloon in pixels
	 */
	public GreenBalloon(int xCoordinate, int yCoordinate) {
		super(2, 50, new Image("greenballoon copy 2.png", 70, 90, false, false), xCoordinate, yCoordinate);
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Default constructor used to create a GreenBalloon object in 
	 * BalloonFactory before its specific coordinates are needed.
	 */
	public GreenBalloon() {
		super(2, 50, new Image("greenballoon copy 2.png", 70, 90, false, false), -100, 120*3);
	}

	/*
	 * Returns a String denoting that it is a RedBalloon
	 * 
	 * Parameters: none
	 * 
	 * Returns: String
	 */
	@Override
	public String toString() {
		return "Green Balloon";
	}
	
	public static void main(String[] args) {
//		Balloon test = new GreenBalloon(0, 0);
//		System.out.println(test);
	}

}

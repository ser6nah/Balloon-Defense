import javafx.scene.image.Image;

/*
 * Name: Serena Han
 * 
 * Class that instantiates a NormalMonkey tower, which has certain fixed properties
 * such as the shot range, delay between shots, speed of shot (in pixel/ms), cost of purchase, etc.
 * As a subclass of Tower, the NormalMonkey class inherits the same methods.
 */
public class NormalMonkey extends Tower {

	public static final int COST = 170;
	
	/*
	 * Constructs a NormalMonkey object
	 * 
	 * Parameters:
	 * 	xCoordinate = x-coordinate in pixels of the object
	 * 	yCoordinate = y-coordinate in pixels of the object
	 */

	public NormalMonkey(double xCoordinate, double yCoordinate) {
		super(150, 3000, 10, COST, (int)xCoordinate / 120, (int) yCoordinate / 120, 
				new Image("dartmonkey copy 2.png", 120, 120, false, false));
	}
	
	/*
	 * Inherits abstract method from Tower class and returns “NormalMonkey” String
	 * 
	 * Parameters: none
	 * 
	 * Returns: String
	 */
	public String toString() {
		String result = "";
		if(this != null) {
			result += "Normal Monkey with x: " + this.getPathPosition().getX() + ", y: " + this.getPathPosition().getY();
		}
		return result;
	}
	
	public static void main(String[] args) {

		/*
		 * tests from before adding the Image and ImageView, 
		 * which make Tower and NormalMonkey impossible to test
		 */
		
//		Tower test = new NormalMonkey(0, 3); //is fine
//		System.out.println(test);
//		
//		Tower test2 = new NormalMonkey(4, 4); //is not fine
//		System.out.println(test2);
//		System.out.println(test2.isPossible(4, 4) == true); //true
		
	}

}

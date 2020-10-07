import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/*
 * Name: Serena Han
 * 
 * Abstract class whose subclasses instantiate the object Balloon, which is an “enemy” to the user. 
 * It contains data about the number of lives that a Balloon object has, its coordinates, and its speed.
 * This class also allows for the location of a balloon to change through the changeLocation method,
 * which will subsequently change the instance variables for location and the Image and ImageView objects.
 * A rectangle is also constructed as the collision area so that when it intersects with a dart,
 * it can be detected.
 */

public abstract class Balloon {

	private static final double SPEED = 1;
	private int lives;
	private int moneyEarned;
	
	private Image bloon;
	private ImageView transformedBloon;
	
	//not made Point objects because needs to be precise beyond integer
	private double locationX;
	private double locationY;
	private double centerLocationX;
	private double centerLocationY;
	
	private Rectangle collisionBox; //needs to be big to account for error 
	
	/*
	 * Constructs a Balloon object using information provided by its subclasses, including
	 * the number of lives the balloon has, the money the user will earn from popping the Balloon,
	 * its image, and its coordinates in pixels.
	 * 
	 * Parameters:
	 * 	lives = number of lives the balloon has
	 * 	moneyEarned = the amount of money (in dollars) that the user will earn from popping the Balloon
	 *  bloon = image of the Balloon
	 * 	xCoordinate = the x-coordinate in pixels 
	 *  yCoordinate = the y-coordinate in pixels
	 */
	public Balloon(int lives, int moneyEarned, Image bloon, double xCoordinate, double yCoordinate) {
		this.lives = lives;
		this.moneyEarned = moneyEarned;
		
		this.bloon = bloon;
        transformedBloon = new ImageView(bloon);
        transformedBloon.setX(xCoordinate);
        transformedBloon.setY(yCoordinate);;
        
    	locationX = xCoordinate;
    	locationY = yCoordinate;
    	centerLocationX = xCoordinate + bloon.getWidth() / 2;
    	centerLocationY = yCoordinate + bloon.getHeight() / 2;
    	
    	collisionBox = new Rectangle(xCoordinate, yCoordinate, bloon.getWidth(), bloon.getHeight());
	}
	
	/*
	 * Changes the location of the Balloon by changing its internal instance variables,
	 * as well as its Image and ImageViews.
	 * 
	 * Parameters:
	 * 	xCoordinate = the x-coordinate in pixels 
	 * 	yCoordinate = the y-coordinate in pixels 
	 * 
	 * Returns: void
	 */
	public void changeLocation(double xCoordinate, double yCoordinate) {
		locationX = xCoordinate;
		locationY = yCoordinate;
		centerLocationX = xCoordinate + bloon.getWidth() / 2;
		centerLocationY = yCoordinate + bloon.getHeight() / 2;
		
		//change location of bloon image
		transformedBloon.setX(xCoordinate);
		transformedBloon.setY(yCoordinate);
		
		//change location of collision box
		collisionBox.setX(xCoordinate);
		collisionBox.setY(yCoordinate);
	}

	
	/**
	 * @return the lives
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * @param lives the lives to set
	 */
	public void setLives(int lives) {
		this.lives = lives;
	}

	/**
	 * @return the bloon
	 */
	public Image getBloon() {
		return bloon;
	}

	/**
	 * @param bloon the bloon to set
	 */
	public void setBloon(Image bloon) {
		this.bloon = bloon;
	}

	/**
	 * @return the transformedBloon
	 */
	public ImageView getTransformedBloon() {
		return transformedBloon;
	}

	/**
	 * @param transformedBloon the transformedBloon to set
	 */
	public void setTransformedBloon(ImageView transformedBloon) {
		this.transformedBloon = transformedBloon;
	}
	
	/*
	 * Subclasses will produce a String denoting the type of Balloon
	 * 
	 * Parameters: none
	 * 
	 * Returns: String
	 */
	public abstract String toString();

	/**
	 * @return the locationX
	 */
	public double getLocationX() {
		return locationX;
	}

	/**
	 * @param locationX the locationX to set
	 */
	public void setLocationX(double locationX) {
		this.locationX = locationX;
	}

	/**
	 * @return the locationY
	 */
	public double getLocationY() {
		return locationY;
	}

	/**
	 * @param locationY the locationY to set
	 */
	public void setLocationY(double locationY) {
		this.locationY = locationY;
	}

	/**
	 * @return the centerLocationX
	 */
	public double getCenterLocationX() {
		return centerLocationX;
	}

	/**
	 * @param centerLocationX the centerLocationX to set
	 */
	public void setCenterLocationX(double centerLocationX) {
		this.centerLocationX = centerLocationX;
	}

	/**
	 * @return the centerLocationY
	 */
	public double getCenterLocationY() {
		return centerLocationY;
	}

	/**
	 * @param centerLocationY the centerLocationY to set
	 */
	public void setCenterLocationY(double centerLocationY) {
		this.centerLocationY = centerLocationY;
	}

	/**
	 * @return the collisionBox
	 */
	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	/**
	 * @param collisionBox the collisionBox to set
	 */
	public void setCollisionBox(Rectangle collisionBox) {
		this.collisionBox = collisionBox;
	}

	/**
	 * @return the moneyEarned
	 */
	public int getMoneyEarned() {
		return moneyEarned;
	}

	/**
	 * @param moneyEarned the moneyEarned to set
	 */
	public void setMoneyEarned(int moneyEarned) {
		this.moneyEarned = moneyEarned;
	}

	/**
	 * @return the speed
	 */
	public static double getSpeed() {
		return SPEED;
	}
}

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

/*
 * Name: Serena Han
 * 
 * This class instantiates a Dart object that can move from the center of a Tower
 * toward its target, a balloon within the range of the Tower's shot. This Dart 
 * object's ImageView rotates so that it faces its target and moves at a constant speed.
 */
public class Dart {

	private double speed;
	private Image dart;
	private ImageView rotatedDart;
	
	private double locationX;
	private double locationY;
	private Rectangle collisionBox;
//	private static int callCheck = 0; //for debugging
	
	private Rotate rotation = new Rotate();

	/*
	 * Constructs Dart object
	 * 
	 * Parameters:
	 *	origin = Tower that the Dart originates from
	 *	endpointX = x-coordinate of the Dart's target, a balloon
	 *	endpointY = y-coordinate of the Dart's target, a balloon
	 */
	public Dart(Tower origin, double endpointX, double endpointY) { //img should be (30, 15, false, false)
		speed = origin.getShotSpeed();
		
		dart = new Image("dart copy.png", 15, 30, false, false);
		rotatedDart = new ImageView(dart);
		
		locationX = origin.getCenterLocationX();
		locationY = origin.getCenterLocationY();
		rotatedDart.setX(locationX);
		rotatedDart.setY(locationY);

		collisionBox = new Rectangle(locationX, locationY, dart.getWidth(), dart.getHeight());	
	    
		rotatedDart.getTransforms().add(rotation);
	    collisionBox.getTransforms().add(rotation);
	    
        double midX = locationX + dart.getWidth() / 2;
        double midY = locationY + dart.getHeight() / 2;
        
        //for debugging
//        callCheck++;
//        System.out.println("callCheck: " + callCheck);
        		
        rotation.setPivotX(midX); 
        rotation.setPivotY(midY);
        rotation.setAngle(Math.toDegrees(Math.atan2(endpointY - midY, endpointX - midX)) + 90);
        
	}

	/*
	 * Moves dart in negative direction along the y-axis of its ImageView
	 * 
	 * Parameters: none
	 * 
	 * Returns: void
	 */

	public void move(){
		locationY -= speed; //problems
		rotatedDart.setY(locationY);
		collisionBox.setY(locationY);
	}
	
	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	public void setCollisionBox(Rectangle collisionBox) {
		this.collisionBox = collisionBox;
	}

	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}
	

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * @return the dartImg
	 */
	public Image getDart() {
		return dart;
	}

	/**
	 * @param dartImg the dartImg to set
	 */
	public void setDart(Image dart) {
		this.dart = dart;
	}

	/**
	 * @return the transformedDartImg
	 */
	public ImageView getRotatedDart() {
		return rotatedDart;
	}

	/**
	 * @param transformedDartImg the transformedDartImg to set
	 */
	public void setRotatedDart(ImageView rotatedDart) {
		this.rotatedDart = rotatedDart;
	}

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
	 * @return the rotation
	 */
	public Rotate getRotation() {
		return rotation;
	}

	/**
	 * @param rotation the rotation to set
	 */
	public void setRotation(Rotate rotation) {
		this.rotation = rotation;
	}

}

import java.awt.Point;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/*
 * Name: Serena Han
 * 
 *  This abstract class allows the user to create objects of its subclasses, including NormalMonkey. 
 *  It also allows users to shoot a Dart whose coordinates begin at the center of the Tower's location.
 *  The Tower class holds information about the tower, including its shot range, delay time between shots,
 *  speed of dart, cost for the user to buy, location, and image. 
 */

public abstract class Tower {

	private double shotRange;
	private double shotDelay;
	private double shotSpeed;
	private int cost;
	private long lastShot;
	
	private Image towerImg;
	private ImageView rotatedTowerImg;
	
	private Point location; //has location of upper left corner in coordinate form
	private double centerLocationX; //has location of center of image in coordinate form
	private double centerLocationY;
	private Point pathPosition; //has location based on Board grid
	private Board level = new Board();
	
	private Rotate towerRotation = new Rotate();
	
	/*
	 * Constructs a Tower object using information provided by its subclasses, including
	 * the shot range, shot speed, cost of the tower, and its position on the board
	 * 
	 * Parameters:
	 * 	shotRange = radius of circle where, if the balloons are in that circle, the tower can shoot at them
	 * 	shotDelay = the smallest amount of time (in ms) between two darts from the same Tower
	 * 	shotSpeed = the speed (in pixel/ms) that a Tower's dart can move
	 * 	cost = the cost for the user to construct a Tower object
	 * 	xPath = the x-coordinate of the Tower on the Board
	 * 	yPath = the y-coordinate of the Tower on the Board
	 * 	towerImg = the Image of the specific tower
	 */
	public Tower(double shotRange, double shotDelay, 
			double shotSpeed, int cost, int xPath, int yPath, Image towerImg) {
		this.shotRange = shotRange;
		this.shotDelay = shotDelay;
		this.shotSpeed = shotSpeed;
		this.cost = cost;

		location = new Point(120 * xPath, 120 * yPath);
		centerLocationX = 120 * xPath + towerImg.getWidth() / 2;
		centerLocationY = 120 * yPath + towerImg.getHeight() / 2; 
		pathPosition = new Point(xPath, yPath);

		this.towerImg = towerImg;
		rotatedTowerImg = new ImageView(towerImg);
//		rotatedTowerImg.getTransforms().add(towerRotation);
		rotatedTowerImg.setX(location.getX());
		rotatedTowerImg.setY(location.getY());
//		CenterRotationPoint(towerRotation);

	}
	
	/*
	 * Method that allows the Tower object to create a Dart whose coordinates are initially
	 * the same as the Tower's center. Sets the lastShot equal to System.currentTimeMillis() 
	 * and returns a Dart with the coordinates of the input.
	 * 
	 * Parameters:
	 * 	xCoordinate = x-coordinate of Tower in pixels
	 * 	yCoordinate = y-coordinate of Tower in pixels
	 * 
	 * Returns:
	 * 	Dart
	 */
	public Dart shoot(double xCoordinate, double yCoordinate) {	
		this.setLastShot(System.currentTimeMillis());
		return new Dart(this, xCoordinate, yCoordinate);
	}
	
	/*
	 *  For if we want the Tower to rotate around its center and point toward
	 * the balloon it's shooting at. Currently, the Image of the NormalMonkey tower
	 * would look odd if it were rotated, so this has been commented out.
	 */
//	public void CenterRotationPoint(Rotate r){
//		r.setPivotX(this.getLocationX() + towerImg.getWidth()/2);
//		r.setPivotY(this.getLocationY() + towerImg.getHeight()/2);
//	}

	/**
	 * @return the pathPosition
	 */
	public Point getPathPosition() {
		return pathPosition;
	}

	/**
	 * @return the towerImg
	 */
	public Image getTowerImg() {
		return towerImg;
	}

	/**
	 * @param towerImg the towerImg to set
	 */
	public void setTowerImg(Image towerImg) {
		this.towerImg = towerImg;
	}

	/**
	 * @param pathPosition the pathPosition to set
	 */
	public void setPathPosition(Point pathPosition) {
		this.pathPosition = pathPosition;
	}

	/**
	 * @return the level1
	 */
	public Board getLevel() {
		return level;
	}

	/**
	 * @param level1 the level1 to set
	 */
	public void setLevel(Board level) {
		this.level = level;
	}

	/**
	 * @return the shotRange
	 */
	public double getShotRange() {
		return shotRange;
	}

	/**
	 * @return the shotDelay
	 */
	public double getShotDelay() {
		return shotDelay;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @return the shotSpeed
	 */
	public double getShotSpeed() {
		return shotSpeed;
	}

	/**
	 * @param shotSpeed the shotSpeed to set
	 */
	public void setShotSpeed(double shotSpeed) {
		this.shotSpeed = shotSpeed;
	}

	/**
	 * @param shotRange the shotRange to set
	 */
	public void setShotRange(double shotRange) {
		this.shotRange = shotRange;
	}

	/**
	 * @param shotDelay the shotDelay to set
	 */
	public void setShotDelay(long shotDelay) {
		this.shotDelay = shotDelay;
	}

	/**
	 * @return the rotatedTowerImg
	 */
	public ImageView getRotatedTowerImg() {
		return rotatedTowerImg;
	}

	/**
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	/**
	 * @param rotatedTowerImg the rotatedTowerImg to set
	 */
	public void setRotatedTowerImg(ImageView rotatedTowerImg) {
		this.rotatedTowerImg = rotatedTowerImg;
	}

	/**
	 * @return the towerRotation
	 */
	public Rotate getTowerRotation() {
		return towerRotation;
	}

	/**
	 * @param towerRotation the towerRotation to set
	 */
	public void setTowerRotation(Rotate towerRotation) {
		this.towerRotation = towerRotation;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	
	public double getLocationX() {
		return location.getX();
	}
	
	public double getLocationY() {
		return location.getY();
	}
	
	
	public double getCenterLocationX() {
		return centerLocationX;
	}
	
	public double getCenterLocationY() {
		return centerLocationY;
	}
	
	/**
	 * @return the lastShot
	 */
	public double getLastShot() {
		return lastShot;
	}

	/**
	 * @param lastShot the lastShot to set
	 */
	public void setLastShot(long lastShot) {
		this.lastShot = lastShot;
	}

	/**
	 * @param shotDelay the shotDelay to set
	 */
	public void setShotDelay(double shotDelay) {
		this.shotDelay = shotDelay;
	}

	/**
	 * @param centerLocationX the centerLocationX to set
	 */
	public void setCenterLocationX(double centerLocationX) {
		this.centerLocationX = centerLocationX;
	}
	
	/**
	 * @param centerLocationY the centerLocationY to set
	 */
	public void setCenterLocationY(double centerLocationY) {
		this.centerLocationY = centerLocationY;
	}

	/*
	 * Subclasses will produce a String denoting the type of Tower
	 * 
	 * Parameters: none
	 * 
	 * Returns: String
	 */
	public abstract String toString();
	
}

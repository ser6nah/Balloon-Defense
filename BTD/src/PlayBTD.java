import java.util.ArrayList;
import javafx.scene.Group;

/*
 * Name: Serena Han
 * 
 * Game engine for the Bloons Tower Defense game. Contains a BalloonFactory object and keeps track of 
 * the objects on the board to be displayed by the game's graphics. Also keeps tracks of the amount of 
 * money that the user has and allows the towers to aim and shoot, checks for collisions between
 * towers and darts, and contains methods that check what the user and what the graphics can and
 * cannot do.
 */
public class PlayBTD {

	private BalloonFactory factory;
	private ArrayList<Balloon> balloonsOnBoard;
	private ArrayList<Tower> towersOnBoard;
	private ArrayList<Dart> dartsOnBoard;
	
	private Board board;
	private int level; 
	private int money;

	private double balloonDelay;
	private double lastBalloon; 
	
	/*
	 * Constructs a PlayBTD game engine object
	 * 
	 * Parameters: none
	 */
	public PlayBTD() {
		board = new Board();
		level = 1;
		money = 200;
		factory = new BalloonFactory(level);
		balloonDelay = 2500 / level;
		
		balloonsOnBoard = new ArrayList<Balloon>();
		towersOnBoard = new ArrayList<Tower>();
		dartsOnBoard = new ArrayList<Dart>();
		lastBalloon = System.currentTimeMillis();
	
	}

	/*
	 * Checks for collisions between all of the darts on the board and the balloons on the board 
	 * and removes the darts and balloons (with one life) that collide. Also updates the money counter
	 * and ArrayLists if a collision occurs.
	 * 
	 * Parameters: 
	 * 	balloonGroup = group of Balloon objects on the javafx graphics
	 * 	dartGroup = group of Dart objects on the javafx graphics
	 * 
	 * Returns: void
	 */
	public void checkCollisions (Group balloonGroup, Group dartGroup) {
		for(int i = 0; i < dartsOnBoard.size(); i++){					
			for(int j = 0; j < balloonsOnBoard.size(); j++){
				if(balloonsOnBoard.get(j).getCollisionBox().intersects(dartsOnBoard.get(i).getCollisionBox().getBoundsInParent())){
					dartsOnBoard.remove(i);
					dartGroup.getChildren().remove(i);
					
					balloonsOnBoard.get(j).setLives(balloonsOnBoard.get(j).getLives() - 1);
					
					//my attempt to change the greenBalloon into a redBalloon
//					if(balloonsOnBoard.get(j).getLives() == 1) {
//						balloonGroup.getChildren().remove(j);
//						balloonsOnBoard.set(j, new RedBalloon(balloonsOnBoard.get(j).getLocationX(), balloonsOnBoard.get(j).getLocationY()));
//						balloonGroup.getChildren().add(balloonsOnBoard.get(j).getTransformedBloon());
//					}
					
					if(balloonsOnBoard.get(j).getLives() == 0) {
						money += balloonsOnBoard.get(j).getMoneyEarned();
						balloonsOnBoard.remove(j);
						balloonGroup.getChildren().remove(j);
					}
				}
			}
		}
	}
	
	/*
	 * Returns true if a balloon goes off the screen without popping
	 * 
	 * Parameter: none
	 * 
	 * Returns: boolean
	 */
	public boolean balloonSurvives() {
		for(Balloon b: balloonsOnBoard) {
			if(b.getCenterLocationX() > board.getWidth() * 120) //goes off screen
//				System.out.println("true");
				return true;
		}
		return false;
	}
	
	/*
	 * Adds a NormalMonkey to the board at the given coordinates, checking if the tower
	 * can be added
	 * 
	 * Parameters:
	 * 	xCoordinate = x-coordinate in pixels where the user clicked
	 * 	yCoordinate = y-coordinate in pixels where the user clicked
	 * 	towerGroup = group of Tower objects in the javafx graphics
	 * 
	 * Returns: void
	 */
	public void addNormalMonkey(double xCoordinate, double yCoordinate, Group towerGroup) {
		int xPath = (int) (xCoordinate / 120);
		int yPath = (int) (yCoordinate / 120);
		if (this.canAddTower(xPath, yPath)) {
			Tower t = new NormalMonkey(xCoordinate, yCoordinate);
			money -= t.getCost();
			this.getBoard().getBoard()[yPath][xPath].setContainsTower(true);
			towersOnBoard.add(t);
			towerGroup.getChildren().add(t.getRotatedTowerImg());
		}
	}

	/*
	 * Returns true if tower can be added based on where on board and 
	 * how much money the user has
	 * 
	 * Parameters:
	 * 	xPath = x-coordinate of position on the Board object
	 * 	yPath = y-coordinate of position on the Board object
	 * 
	 * Returns: boolean
	 */
	public boolean canAddTower(int xPath, int yPath) {
		
		if (xPath < this.getBoard().getWidth() && yPath < this.getBoard().getHeight()
				&& this.getMoney() >= 170) {
			Block block = this.getBoard().getBoard()[yPath][xPath];
			return block.getType() == Block.TOWERSPACE && block.containsTower() == false;
			}
		
		return false;
	}
	
	/*
	 * Returns true if balloon can be added based on the last 
	 * time a balloon was added to the board and the necessary balloon delay
	 * 
	 * Parameters: none
	 * 
	 * Returns: boolean
	 */
	public boolean canAddBalloon() {
		return System.currentTimeMillis() - lastBalloon >= balloonDelay;
	}
	
	/*
	 * Returns true if dart can be added based on the last 
	 * time a tower added a dart to the board and the necessary shot delay
	 * 
	 * Parameters: none
	 * 
	 * Returns: boolean
	 */
	public boolean canAddDart(Tower t) {
		return System.currentTimeMillis() - t.getLastShot() >= t.getShotDelay();
	}
	
	/*
	 * Moves all darts on board in their shooting direction
	 * 
	 * Parameters: none
	 * 
	 * Returns: void
	 */
	public void moveDarts() {
		for(Dart d: dartsOnBoard) {
			d.move();
		}
	}
	
	/*
	 * Has all towers shoot at balloons within their range, taking into account
	 * when the last dart was shot
	 * 
	 * Parameters:
	 * 	dartGroup = group of Dart objects in javafx graphics
	 * 
	 * Returns: void
	 */
	
	public void towersShoot(Group dartGroup) {
		for (Tower t : towersOnBoard) {
			for (Balloon b : balloonsOnBoard) {
				if (Math.sqrt((Math.pow(t.getCenterLocationX() - b.getCenterLocationX(), 2))
						+ Math.pow(t.getCenterLocationY() - b.getCenterLocationY(), 2)) <= t.getShotRange()) {
					if (this.canAddDart(t)) {
						Dart d = t.shoot(b.getCenterLocationX(), b.getCenterLocationY());
						this.getDartsOnBoard().add(d);
						dartGroup.getChildren().add(d.getRotatedDart());
					}
				}
			}
		}
	}

	/**
	 * @return the factory
	 */
	public BalloonFactory getFactory() {
		return factory;
	}

	/**
	 * @param factory the factory to set
	 */
	public void setFactory(BalloonFactory factory) {
		this.factory = factory;
	}

	/**
	 * @return the balloonsOnBoard
	 */
	public ArrayList<Balloon> getBalloonsOnBoard() {
		return balloonsOnBoard;
	}

	/**
	 * @param balloonsOnBoard the balloonsOnBoard to set
	 */
	public void setBalloonsOnBoard(ArrayList<Balloon> balloonsOnBoard) {
		this.balloonsOnBoard = balloonsOnBoard;
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}
	
	/**
	 * @param towersOnBoard the towersOnBoard to set
	 */
	public void setTowersOnBoard(ArrayList<Tower> towersOnBoard) {
		this.towersOnBoard = towersOnBoard;
	}
	
	/**
	 * @return the dartsOnBoard
	 */
	public ArrayList<Dart> getDartsOnBoard() {
		return dartsOnBoard;
	}

	/**
	 * @param dartsOnBoard the dartsOnBoard to set
	 */
	public void setDartsOnBoard(ArrayList<Dart> dartsOnBoard) {
		this.dartsOnBoard = dartsOnBoard;
	}
	
	/**
	 * @return the balloonDelay
	 */
	public double getBalloonDelay() {
		return balloonDelay;
	}

	/**
	 * @param balloonDelay the balloonDelay to set
	 */
	public void setBalloonDelay(double balloonDelay) {
		this.balloonDelay = balloonDelay;
	}

	/**
	 * @return the lastBalloon
	 */
	public double getLastBalloon() {
		return lastBalloon;
	}

	/**
	 * @param lastBalloon the lastBalloon to set
	 */
	public void setLastBalloon(double lastBalloon) {
		this.lastBalloon = lastBalloon;
	}

	/**
	 * @return the towersOnBoard
	 */
	public ArrayList<Tower> getTowersOnBoard() {
		return towersOnBoard;
	}

}

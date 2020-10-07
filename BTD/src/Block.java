/*
 * Name: Serena Han and Sam Chen
 * 
 * Instantiates Block objects that keep track of which spaces on the Board are for 
 * towers and which are for balloons. It also contains a boolean value
 * for tower spaces to keep track of if they contain towers.
 */

public class Block {

	private int type;
	private boolean containsTower;
	public static final int TOWERSPACE = 0;
	public static final int BALLOONSPACE = 1;
	
	/*
	 * Constructs a Block object
	 * 
	 * Parameters:
	 * 	type = type of Block, either TOWERSPACE or BALLOONSPACE
	 */
	public Block(int type) {
		if (type != TOWERSPACE && type != BALLOONSPACE) {
			type = TOWERSPACE;
		}
		else this.type = type;
	}
	
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the containsTower
	 */
	public boolean containsTower() {
		return containsTower;
	}

	/**
	 * @param containsTower the containsTower to set
	 */
	public void setContainsTower(boolean containsTower) {
		this.containsTower = containsTower;
	}
	
	

}

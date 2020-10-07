import java.awt.Point;

/*
 * Name: Serena Han and Sam Chen
 * 
 * 
 * Class that contains a 2D array of Block objects, allowing
 * the program to keep track of the path that the balloons travel along.
 * The default constructor initializes a level one course.
 */

public class Board {

	private int height;
	private int width;
	private Block[][] board;
	private Point start;
	private Point finish;
	
	/*
	 * Default constructor that initializes to the level one course
	 * 
	 * Parameters: none
	 */
	public Board() { 
		height = 5;
		width = 5;
		board = new Block[height][width];
		start = new Point(0, 3);
		finish = new Point(4, 1);
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = new Block(Block.TOWERSPACE);
			}
		}
		
		board[3][0].setType(Block.BALLOONSPACE);
		board[3][1].setType(Block.BALLOONSPACE);
		board[3][2].setType(Block.BALLOONSPACE);
		board[2][2].setType(Block.BALLOONSPACE);
		board[1][2].setType(Block.BALLOONSPACE);
		board[1][3].setType(Block.BALLOONSPACE);
		board[1][4].setType(Block.BALLOONSPACE);

	}
	
	/*
	 * Represents the Board through a String to be tested in the main
	 * 
	 * Parameters: none
	 * 
	 * Returns: String
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if(board[i][j].getType() == Block.BALLOONSPACE)
					result += "{{ }}"; //track for balloons
				if(board[i][j].getType() == Block.TOWERSPACE)
					result += "|   |";
			}
			result += "\n";	
		}
		return result;
	}
	
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the board
	 */
	public Block[][] getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(Block[][] board) {
		this.board = board;
	}

	/**
	 * @return the start
	 */
	public int getStartX() {
		return (int)start.getX();
	}
	
	public int getStartY() {
		return (int)start.getY();
	}

	/**
	 * @return the finish
	 */
	public double getFinishX() {
		return finish.getX();
	}
	
	public double getFinishY() {
		return finish.getY();
	}


	public static void main(String[] args) {
		Board c = new Board();
		System.out.println(c);
		System.out.println(c.getBoard()[3][0].getType() == Block.TOWERSPACE); //false
	}
}

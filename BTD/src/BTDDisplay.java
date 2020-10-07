import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*
 * Name: Serena Han
 * 
 * The BTDDisplay class implements javafx to display the Bloons Tower Defense game.
 * This class allows for a full level to be played, including the implementation of
 * the balloon factory to add balloons onto the board, as well as the towers to
 * aim and successfully shoot darts at balloons within their range. There are a few
 * buttons for actions that the user can perform, including the Start button to allow the user
 * to start the adding of balloons onto the path and the buyMonkey button that allows
 * the user to place a NormalMonkey on the board and spend his/her coins. Although the
 * program currently only has one level, more levels can be added by adding more set-ups
 * onto the Board class and running this same application.
 */
public class BTDDisplay extends Application {

	private PlayBTD game;
	
	/*
	 * Default constructor of the BTDDisplay object
	 * 
	 * Parameters: none
	 */
	public BTDDisplay() {
		game = new PlayBTD();
	}
	
	/*
	 * (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	public void start(Stage theStage) 
    {
		theStage.setTitle("Bloons Tower Defense");

		// Creates root pane
		SplitPane rootPane = new SplitPane();
		Scene scene = new Scene(rootPane);

		// Creates the left and right panes
	    Pane gameScene = new Pane();
	    Pane sidebar = new Pane();
	    
	    // Prevents user from moving divider
	    int sideBarWidth = 600;
	    gameScene.setMinWidth(sideBarWidth);
	    gameScene.setMaxWidth(sideBarWidth);
	    int gameSceneWidth = 200;
	    sidebar.setMinWidth(gameSceneWidth);
	    sidebar.setMaxWidth(gameSceneWidth);

	    // Adds panes to rootpane
	    rootPane.getItems().addAll(gameScene, sidebar);
	    
	    // Sets dimensions of Stage
        theStage.setScene(scene);
        theStage.setResizable(false);
		theStage.setHeight(623);
		theStage.setWidth(800);
		
		// Sets up gameSccene and adds groups
		Canvas canvas = new Canvas(600, 600);
		Group towerGroup = new Group();
	    Group balloonGroup = new Group();
	    Group dartGroup = new Group();
		
		gameScene.getChildren().add(canvas);
		gameScene.getChildren().add(towerGroup);
		gameScene.getChildren().add(dartGroup);
		gameScene.getChildren().add(balloonGroup);
		
		// Sets up sidebar and adds groups
		Canvas sidebarCanvas = new Canvas(200, 600);
		sidebar.getChildren().add(sidebarCanvas);
        
		GraphicsContext gc = canvas.getGraphicsContext2D();
		GraphicsContext gc2 = sidebarCanvas.getGraphicsContext2D();
		
		// Sets variables for gameScene
        int blockHeight = (int) theStage.getHeight() / game.getBoard().getHeight();
        int blockWidth = (int) (theStage.getWidth() - 200)/ game.getBoard().getWidth();
        
        // Sets up start button 
        Button startButton = new Button("  Start  "); 
        startButton.setStyle("-fx-font: 22 arial; -fx-background-color:green; -fx-text-fill: white;");
        startButton.setLayoutX(50);
        startButton.setLayoutY(500);
        sidebar.getChildren().add(startButton);
          
        // Sets up moneyCounter label
        Label moneyCounter = new Label();
        moneyCounter.setLayoutX(150);
        moneyCounter.setLayoutY(50);
        Font theFont = Font.font("Arial", 15);
        moneyCounter.setFont(theFont);
        moneyCounter.setTextFill( Color.WHITE );
        sidebar.getChildren().add(moneyCounter);
        
        // Sets up monkey button
        Image buyMonkey = new Image("dartmonkey copy.png", 80, 80, false, false);
        Button buyMonkeyBtn = new Button("");
        buyMonkeyBtn.setGraphic(new ImageView(buyMonkey));
        buyMonkeyBtn.setLayoutX(25);
        buyMonkeyBtn.setLayoutY(100);
        sidebar.getChildren().add(buyMonkeyBtn);
        
        // Sets up monkey cost
        Label monkeyCost = new Label("       $170       ");
        monkeyCost.setLayoutX(28);
        monkeyCost.setLayoutY(200);
        Font theFont4 = Font.font("Arial", 15);
        monkeyCost.setFont(theFont4);
        monkeyCost.setTextFill( Color.WHITE );
        sidebar.getChildren().add(monkeyCost);
      
        new AnimationTimer()
        {
  
			public void handle(long currentNanoTime)
            {

				// Adds background images for both panes
                Image map = new Image("sand btdmaplvl1.png", 605, 600, false, false);
                
                Image sidebarImg = new Image("dirtsidebar.png", 200, theStage.getHeight(), false, false);
                gc2.drawImage(sidebarImg, 0, 0);
                
                // Draws images for both panes
                gc.drawImage(map, 0, 0);
                gc2.drawImage(sidebarImg, 0, 0);
                
                // Updates level
                gc2.setFill( Color.WHITE );
                gc2.setLineWidth(3);
                Font theFont = Font.font( "Arial", 24 );
                gc2.setFont( theFont );
                gc2.fillText( "Level " + game.getLevel(), 55, 
                		theStage.getHeight() / 20);	
                
                // Updates moneyCounter
                moneyCounter.setText("$" + game.getMoney());
                
                // Creating actions for start and restart button
                
                // Drop shadow effect when hovering mouse cursor over button
                DropShadow shadow = new DropShadow();
                startButton.addEventHandler(MouseEvent.MOUSE_ENTERED, 
                	    new EventHandler<MouseEvent>() {
                	        @Override public void handle(MouseEvent e) {
                	            startButton.setEffect(shadow);
                	        }
                	});
                	startButton.addEventHandler(MouseEvent.MOUSE_EXITED, 
                	    new EventHandler<MouseEvent>() {
                	        @Override public void handle(MouseEvent e) {
                	            startButton.setEffect(null);
                	        }
                	});
                
                // Changes the text to "restart" or "start" upon mouse click	
                startButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
						startButton.setText("Started");
                    }
                });
                
                // Creates actions for buyMonkey button 
                
                buyMonkeyBtn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						if (buyMonkeyBtn.getText().equals("")) {
							if (game.getMoney() >= NormalMonkey.COST) {
								buyMonkeyBtn.setText("buying");
								monkeyCost.setText("click on map to place");
							}
						}
						else {
							buyMonkeyBtn.setText("");
							monkeyCost.setText("       $170       ");
						}
                    }
                });
                
                // Adds monkey based on mouse click of user
                gameScene.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        double mouseClickX = event.getSceneX();
                        double mouseClickY = event.getSceneY();
                        
                        if(buyMonkeyBtn.getText().equals("buying")) {
                        	if (game.canAddTower((int)mouseClickX / 120, (int)mouseClickY / 120)) {
								monkeyCost.setText("       $170       ");
								buyMonkeyBtn.setText("");
							}
                        	game.addNormalMonkey(mouseClickX, mouseClickY, towerGroup);
                        }
                    }
                });
                
             // Add monkeys - should have user do this
//                game.addNormalMonkey(blockWidth, blockHeight * 2, towerGroup);

//                game.addNormalMonkey(blockWidth * 3, blockHeight * 2, towerGroup);

//				game.addNormalMonkey(blockWidth * 4, blockHeight * 0, towerGroup);
				
				// Testing to see if it's off screen what happens
				game.addNormalMonkey(blockWidth * 1, blockWidth * 8, towerGroup);
				
				// Sets up startButton actions
				if (startButton.getText().equals("Started")) {
					// Adds balloons to board in intervals
					if (game.canAddBalloon()) {
						if (game.getFactory().getBalloonsInLine().peekFirst() != null) {
							game.setLastBalloon(System.currentTimeMillis());
							Balloon b = game.getFactory().getBalloonsInLine().pollFirst();
							game.getBalloonsOnBoard().add(b);
							balloonGroup.getChildren().add(b.getTransformedBloon());
						}
					}

					// Balloons move along path
					double balloonSpeedX = Balloon.getSpeed();
					double balloonSpeedY = -Balloon.getSpeed();
					
					for (Balloon b : game.getBalloonsOnBoard()) {
						
						if (b.getLocationX() < blockWidth * 2 + 20) {
							b.changeLocation(b.getLocationX() + balloonSpeedX, blockHeight * 3);
						} else if (b.getLocationX() >= blockWidth * 2 + 20 && b.getLocationY() > blockHeight * 1) {
							b.changeLocation(b.getLocationX(), b.getLocationY() + balloonSpeedY);
						} else {
							b.changeLocation(b.getLocationX() + balloonSpeedX, b.getLocationY());
						}
					}
				}
				
				// Game ends if balloon goes off board -- lose
				if(game.balloonSurvives()) {
					Label youLost = new Label("You lost!");
					youLost.setLayoutX(14);
					youLost.setLayoutY(300);
			        Font theFont9 = Font.font("Arial", 40);
			        youLost.setFont(theFont9);
			        youLost.setTextFill( Color.WHITE );
			        sidebar.getChildren().add(youLost);
				}
				
				// Game ends if balloons are all dead -- win
				if(game.getBalloonsOnBoard().size() == 0 && game.getFactory().getBalloonsInLine().size() == 0) {
					Label youWin = new Label("You won!");
					youWin.setLayoutX(14);
			        youWin.setLayoutY(300);
			        Font theFont10 = Font.font("Arial", 40);
			        youWin.setFont(theFont10);
			        youWin.setTextFill( Color.WHITE );
			        sidebar.getChildren().add(youWin);
				}
          
				// Performs game procedures
				game.towersShoot(dartGroup);
                game.moveDarts();
                game.checkCollisions(balloonGroup, dartGroup);
        
        }
      
        }.start();
        theStage.show(); 
    }
	
	public static void main(String[] args) {
		launch(args);
	
	}
}

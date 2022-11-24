import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
//import javafx.application.userData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView; 
import javafx.scene.text.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class Main extends Application implements Initializable{
	
	@FXML
    private Label userFirstLastName;

	@FXML
    private Label userIdDisplay;

	@FXML
    private Label userVanIdDisplay;
	

	private Button startButton;

	private TextArea t1;
	private TextArea t2;
	private TextArea t3;
	
	private Image loginPic;
	private ImageView bikes;
	
	private Image sample;
	private Text welcomeMessage;

	private MenuBar menu;
	//private Image img;
	
	userData data = new userData();

	private EventHandler<ActionEvent> myHandler;




	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Divvy Van Navigator - Login");

		startButton = new Button("Enter");
		
		t1 = new TextArea();
		t1.setEditable(true);
		t1.setDisable(false);

		primaryStage.setTitle("Divvy Van Navigator - Main Menu");

		// need box for name, user id, van id

		TextArea t1;
		TextArea t2;
		TextArea t3;
		ImageView bikes;
		Text welcomeMessage;
		//Image img;



		MenuBar menu;
		EventHandler<ActionEvent> myHandler;

		//Button startButton = new Button("Start");
		welcomeMessage = new Text();
		
		welcomeMessage.setText("Welcome to the Divvy Van Navigator, enter info below to get started");
		welcomeMessage.setTranslateX(175);
		
		
		
		t1 = new TextArea();
		t1.setEditable(true);
		t1.setDisable(false);
		t1.setPromptText("Enter Name");
		t1.setMaxWidth(200);
		t1.setMinWidth(200);
		t1.setMaxHeight(25);
		//t1.setLayoutX(150);
		t1.setTranslateX(250);
		//t1.setOnMouseClicked(null);
		//t1.setOnMouseClicked();
		
		
		t2 = new TextArea();
		t2.setEditable(true);
		t2.setDisable(false);
		t2.setPromptText("Enter User ID");
		t2.setMaxWidth(200);
		t2.setMinWidth(200);
		t2.setMaxHeight(25);
		t2.setTranslateX(250);
		
		t3 = new TextArea();
		t3.setEditable(true);
		t3.setDisable(false);
		t3.setPromptText("Enter Van ID or License Plate");
		t3.setMaxWidth(200);
		t3.setMinWidth(200);
		t3.setMaxHeight(25);
		t3.setTranslateX(250);
		
		
		
		//bikes.setImage(null);
		
		//loginPic = new Image("divvyLoginPic.jpg");
		//bikes = new ImageView();
		//bikes.setImage(loginPic);
		//URL url = getClass().getResource("/drawIcon.png");
		//Image image = ImageIO.read(url);
		
		// this needs to change to check the local project files not c/downloads
		Image img = new Image(getClass().getResource("/images/loginLogo.jpg").toURI().toString());
		
		//sample = new Image("C:\\Users\\guagu\\Downloads\\pic4.jpg");
		//loginPic = new Image("loginLogo.jpg");
		//sample.isPreserveRatio();
		
		
		bikes = new ImageView();
		bikes.setImage(img);
		bikes.setTranslateX(90);
		bikes.setFitHeight(150);
		bikes.setFitWidth(700);
		
		bikes.setPreserveRatio(true);
		
		
		//Image image = new Image("divvyLoginPic.jpg");
		

		startButton.setOnAction(e-> {
			//primaryStage.setTitle("TicTacToe - Game");
			//primaryStage.setScene(new GameScreen().createGameScene());
			try {
				
				data.setUserName(t1.getText());
				data.setUserID(t2.getText());
				data.setvanID(t3.getText());
				
				
				
				
	            // Read file fxml and draw interface.
	            Parent root = FXMLLoader.load(getClass()
	                    .getResource("/FXML/FXMLv2.fxml"));

	            
	            primaryStage.setTitle("My Application");
             	Scene s1 = new Scene(root, 950,700);
             	s1.getStylesheets().add("/styles/style1.css");

	            primaryStage.setScene(s1);
	            primaryStage.show();
	            
	            
	            
	            

	        } catch(Exception x) {
	            x.printStackTrace();
	            System.exit(1);
	        }
		});
		//startButton.setAlignment(Pos.BOTTOM_CENTER);
		startButton.setTranslateX(325);
		menu = new MenuBar(); //a menu bar takes menus as children
		Menu mOne = new Menu("Menu"); //a menu goes inside a menu bar

		MenuItem iOne = new MenuItem("Help"); //menu items go inside a menu
		MenuItem iTwo = new MenuItem("Options"); //menu items go inside a menu
		MenuItem iThree = new MenuItem("Exit"); //menu items go inside a menu

		iOne.setOnAction(e->t1.setText(""));

		iTwo.setOnAction(e->t1.setText(""));
		iThree.setOnAction(e-> Platform.exit());

		mOne.getItems().add(iOne); //add menu item to first menu
		mOne.getItems().add(iTwo); //add menu item to first menu
		mOne.getItems().add(iThree); //add menu item to first menu

		menu.getMenus().addAll(mOne); //add two menus to the menu bar
		
		



		//new scene with root node
		Scene scene = new Scene(new VBox(20,menu,bikes,welcomeMessage,t1,t2,t3,startButton), 700, 500);
		welcomeMessage.requestFocus();
		primaryStage.setScene(scene); //set the scene in the stage
		primaryStage.show(); //make visible to the user


	}
	
	public void initData(userData currentData) {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

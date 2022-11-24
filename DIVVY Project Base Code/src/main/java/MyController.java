

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MyController implements Initializable {
	
	private List<String> storeDataList = new ArrayList<>(); // list to collect coordinate data from API
 
	private ObservableList<String> listviewOL = FXCollections.observableArrayList();
	
	private String x_App_Token = "QdqzeCrstFeHWJrQY6vEfyX6L"; // app token on City of Chicago site

    private String host = "https://data.cityofchicago.org/resource/bbyy-e7gq.json"; // API endpoint
    
    @FXML
    private WebView webView;
    
    @FXML
    private WebEngine webEngine;
    
	@FXML
	private VBox vbox1;

	@FXML
    private MenuBar menubar1;

	@FXML
    private Menu file;

	@FXML
    private Menu edit;

	@FXML
    private Menu help;

	@FXML
    private SplitPane splitpane1;

	@FXML
    private Label userInfoText;

	@FXML
    private Label userFirstLastName;

	@FXML
    private Label userIdDisplay;

	@FXML
    private Label userVanIdDisplay;

	@FXML
    private ImageView divvyLogoImageView;

	@FXML
    private Label tempTextBoxDivvyLogo;

	@FXML
    private ListView taskListTable;

	@FXML
    private Label tempTextTaskTable;

	@FXML
    private AnchorPane Content;

	@FXML
    private Label tempTextMapView;

	@FXML
    private Label jobInfoTextLabel;

	@FXML
    private Label nextStopText;

	@FXML
    private Label currentBoxText;

	@FXML
    private Label currentScootersText;

	@FXML
    private Label stopsCompletedText;

	@FXML
    private Label stopsLeftText;

	@FXML
    private Label jobFunctionsLabel;
	
	@FXML
	private Button startButton; 
	@FXML
    private Button skipStopButton;

	@FXML
    private Button altRouteButton;

	@FXML
    private Button addBikeButton;

	@FXML
    private Button addScooterButton;

	@FXML
    private Button removeBikeButton;

	@FXML
    private Button removeScooterButton;

	@FXML
    private Button takeBreakButton;

	@FXML
    private Button resumeWorkButton;

	@FXML
    private Button markStopCompletedButton;
	
	@FXML
	private Button displayMap;

	@FXML
    private HBox HBox;

	@FXML
    private Label notificationsLabel;

	@FXML
    private Label topAlertNotification;

	@FXML
    private Label bottomAlertNotification;
	
    //static so each instance of controller can access to update 
    private static String textEntered = "";


	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
		// TODO Auto-generated method stub

		webEngine = webView.getEngine();
		
        
	}
	
	public void loadPage() {
		webEngine.load("https://www.google.com/maps/@41.8805712,-87.658697,13z");

	}

	public void helloMethod(ActionEvent e) throws IOException {

 

        //get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/FXMLv2.fxml"));
        
        

        Parent root2 = loader.load(); //load view into parent

        MyController myctr = loader.getController();//get controller created by FXMLLoader
        
        

        root2.getStylesheets().add("/styles/style2.css");//set style
       


	}
	
	public void getCoords() {

        try {
            HttpResponse <JsonNode> response = Unirest.get(host)
                    .header("X-App-Token", x_App_Token)
                    .header("accept", "application/json")//.queryString("docks_in_service", "9")
                    .asJson();
            System.out.println(response.getStatus());

            JSONArray responseBody = response.getBody().getArray(); // get response body into Array format for parsing
            JSONObject coords;
            String locations;
            for(int i = 0; i < responseBody.length(); i++) { // loop through body and parse out location dictionary and station_name values
                coords = (JSONObject) responseBody.getJSONObject(i).get("location");
                locations = (String) responseBody.getJSONObject(i).get("station_name");
                storeDataList.add((String) (locations + ": " + coords.get("latitude") + ", " + coords.get("longitude")));

                //taskListTable.getItems().add(someData.get(0));
            }
            System.out.println(storeDataList);
            listviewOL.setAll(storeDataList);
            taskListTable.setItems(listviewOL);
            taskListTable.setCellFactory(ComboBoxListCell.forListView(listviewOL));

        }
        catch(Exception e) {
            System.out.println("ERROR: request failed");
            System.out.println(e);
        }

    }
	
	public void updateNextStop() {
        listviewOL.remove(0, 1);
        String nextStop = listviewOL.get(0);
        nextStopText.setText("Next stop: " + nextStop.split(":")[0]);
    }
	
	public void incrementBikeCounter() {
        String currentBikesText = currentBoxText.getText(); // original label text
        System.out.println(currentBikesText);
        int currNumBikes = Integer.parseInt(currentBikesText.substring(currentBikesText.length() - 1)); // get int at end of label text
        System.out.println(currNumBikes);
        currNumBikes++; // increment number of bikes
        currentBoxText.setText("Current Bikes in Van: " + currNumBikes);
    }

	public void decrementBikeCounter() {
        String currentBikesText = currentBoxText.getText(); // original label text
        System.out.println(currentBikesText);
        int currNumBikes = Integer.parseInt(currentBikesText.substring(currentBikesText.length() - 1)); // get int at end of label text
        System.out.println(currNumBikes);
        if(currNumBikes > 0) // condition so label does not display negatives
            currNumBikes--; // increment number of bikes
        currentBoxText.setText("Current Bikes in Van: " + currNumBikes);
    }
	
	public void incrementScooterCounter() {
        String currentScooterText = currentScootersText.getText(); // original label text
        System.out.println(currentScooterText);
        int currNumScooters = Integer.parseInt(currentScooterText.substring(currentScooterText.length() - 1)); // get int at end of label text
        System.out.println(currNumScooters);
        currNumScooters++; // increment number of bikes
        currentScootersText.setText("Current Scooters in Van: " + currNumScooters);
    }
	
	public void decrementScooterCounter() {
        String currentScooterText = currentScootersText.getText(); // original label text
        System.out.println(currentScooterText);
        int currNumScooters = Integer.parseInt(currentScooterText.substring(currentScooterText.length() - 1)); // get int at end of label text
        System.out.println(currNumScooters);
        if(currNumScooters > 0) // condition so label does not display negatives
            currNumScooters--; // increment number of bikes
        currentScootersText.setText("Current Scooters in Van: " + currNumScooters);
    }
	
	

} // end of file

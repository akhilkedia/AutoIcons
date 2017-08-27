package autoicons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class UI extends Application implements Initializable {

	// private int counter = 0;
	public AutoIcons autoIcons = new AutoIcons();
	public Stage primaryStage;
	public AnchorPane rootLayout;
	private PrintStream ps;

	@FXML
	public TextField API_KEY;
	@FXML
	public TextField SEARCH_ENGINE_ID;
	@FXML
	public TextField PROXY_HOST;
	@FXML
	public TextField PROXY_PORT;
	@FXML
	public CheckBox PROXY;
	@FXML
	public TextField TOP_FOLDER;
	@FXML
	public TextField DEPTH;
	@FXML
	public CheckBox MUSICMOVIE;
	@FXML
	public CheckBox RETRYFAILLIST;
	@FXML
	public TextArea OUTPUT;
	@FXML
	public Button BUTTON;
	@FXML
	public Button BROWSE;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AutoIcons");
		initRootLayout();

		// showPersonOverview();
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(UI.class.getResource("SettingsLayout.fxml"));
			rootLayout = (AnchorPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Called when the user clicks on the RunAutoIcons button.
	 */
	@FXML
	private void handleRunAutoIconsPressed(ActionEvent e) {
		System.out.println("Pressed Button.");

		UItoParameters();
		SaveParameters();
		BUTTON.setDisable(true);
		// Console console = new Console(OUTPUT);
		// ps = new PrintStream(console, true);
		//TODO: Chang ethe following.
		//System.setOut(ps);
		//System.setErr(ps);
		System.out.println("Starting AutoIcons!");
		autoIcons.ui = this;
		//new Thread(autoIcons).start();
		// AutoIcons.RunAutoIcons();
		
		
	    WebView wv = new WebView();
	    StackPane root = new StackPane();
	    root.getChildren().add(wv);

	    Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Hello World!");
        stage.setScene(scene);
        stage.show();
	    wv.getEngine().load("http://www.i-am-bored.com/pop_up_blocker_test.html");
	}

	@FXML
	private void handleBrowsePressed(ActionEvent e) {
		System.out.println("Pressed Browse");
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Select the Folder to Iconize");
		File file = directoryChooser.showDialog(null);
		if (file != null) {
			TOP_FOLDER.setText(file.getPath());
		}
	}

	public void UItoParameters() {
		autoicons.Parameters.API_KEY = API_KEY.getText();
		autoicons.Parameters.SEARCH_ENGINE_ID = SEARCH_ENGINE_ID.getText();
		autoicons.Parameters.PROXY_HOST = PROXY_HOST.getText();
		autoicons.Parameters.PROXY_PORT = PROXY_PORT.getText().isEmpty() ? 0 : Integer.parseInt(PROXY_PORT.getText());
		autoicons.Parameters.PROXY = PROXY.isSelected() ? 1 : 0;
		autoicons.Parameters.TOP_FOLDER = TOP_FOLDER.getText();
		autoicons.Parameters.DEPTH = DEPTH.getText().isEmpty() ? 0 : Integer.parseInt(DEPTH.getText());
		autoicons.Parameters.MUSICMOVIE = MUSICMOVIE.isSelected() ? 1 : 0;
		autoicons.Parameters.RETRYFAILLIST = RETRYFAILLIST.isSelected() ? 1 : 0;
	}

	public void SaveParameters() {
		Properties prop = new Properties();
		OutputStream output = null;

		try {
			output = new FileOutputStream(autoicons.Parameters.CONFIG_PROPERTIES_FILE);
			// set the properties value
			prop.setProperty("API_KEY", autoicons.Parameters.API_KEY);
			prop.setProperty("SEARCH_ENGINE_ID", autoicons.Parameters.SEARCH_ENGINE_ID);
			prop.setProperty("PROXY_HOST", autoicons.Parameters.PROXY_HOST);
			prop.setProperty("PROXY_PORT", autoicons.Parameters.PROXY_PORT + "");
			prop.setProperty("PROXY", autoicons.Parameters.PROXY + "");
			prop.setProperty("FULL_PATH", autoicons.Parameters.FULL_PATH);
			prop.setProperty("TOP_FOLDER", autoicons.Parameters.TOP_FOLDER);
			prop.setProperty("DEPTH", autoicons.Parameters.DEPTH + "");
			prop.setProperty("MUSICMOVIE", autoicons.Parameters.MUSICMOVIE + "");
			prop.setProperty("RETRYFAILLIST", autoicons.Parameters.RETRYFAILLIST + "");

			// save properties to project root folder
			prop.store(output, null);
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public void LoadParameters() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(autoicons.Parameters.CONFIG_PROPERTIES_FILE);

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			autoicons.Parameters.API_KEY = prop.getProperty("API_KEY");
			autoicons.Parameters.SEARCH_ENGINE_ID = prop.getProperty("SEARCH_ENGINE_ID");
			autoicons.Parameters.PROXY_HOST = prop.getProperty("PROXY_HOST");
			autoicons.Parameters.PROXY_PORT = Integer.parseInt(prop.getProperty("PROXY_PORT"));
			autoicons.Parameters.PROXY = Integer.parseInt(prop.getProperty("PROXY"));
			autoicons.Parameters.FULL_PATH = prop.getProperty("FULL_PATH");
			autoicons.Parameters.TOP_FOLDER = prop.getProperty("TOP_FOLDER");
			autoicons.Parameters.DEPTH = Integer.parseInt(prop.getProperty("DEPTH"));
			autoicons.Parameters.MUSICMOVIE = Integer.parseInt(prop.getProperty("MUSICMOVIE"));
			autoicons.Parameters.RETRYFAILLIST = Integer.parseInt(prop.getProperty("RETRYFAILLIST"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void SetUIParameters() {
		API_KEY.setText(autoicons.Parameters.API_KEY);
		SEARCH_ENGINE_ID.setText(autoicons.Parameters.SEARCH_ENGINE_ID);
		PROXY_HOST.setText(autoicons.Parameters.PROXY_HOST);
		PROXY_PORT.setText(autoicons.Parameters.PROXY_PORT + "");
		PROXY.setSelected(autoicons.Parameters.PROXY == 1);
		TOP_FOLDER.setText(autoicons.Parameters.TOP_FOLDER);
		DEPTH.setText(autoicons.Parameters.DEPTH + "");
		MUSICMOVIE.setSelected(autoicons.Parameters.MUSICMOVIE == 1);
		RETRYFAILLIST.setSelected(autoicons.Parameters.RETRYFAILLIST == 1);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		autoicons.Parameters.FULL_PATH = System.getProperty("user.dir") + "\\resources\\";
		System.out.println("FULL_PATH is set to " + autoicons.Parameters.FULL_PATH);
		autoicons.Parameters.recalculatePaths();
		LoadParameters();
		SetUIParameters();
		ps = new PrintStream(new Console(OUTPUT));

	}
}

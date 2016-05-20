import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.*;

public class Login extends Application
{
	private static Stage window;
	
	private static Button loginButton;
	private static TextField username;
	private static TextField password;
	
	private static Label loginLabel;
	
	private static Scene scene;
	
	private static VBox loginLayout;
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void start(Stage primaryStage) throws Exception 
	{
		window = primaryStage;
		window.setMaxWidth(200);
		window.setMaxHeight(300);
				
		loginLabel = new Label("Login to use our shitty chat");
		//loginLabel.setAlignment(Pos.TOP_CENTER);
		
		username = new TextField("Username");
		password = new TextField("Password");
		
		loginButton = new Button("Login");
		loginButton.setOnAction(e -> login());
		
		loginLayout = new VBox();
		loginLayout.getChildren().add(loginLabel);
		loginLayout.getChildren().addAll(username, password);
		loginLayout.getChildren().add(loginButton);
		
		
		
		
		scene = new Scene(loginLayout, 200, 300);
		
		window.setScene(scene);
		window.setTitle("Login for Memes");
		window.show();
	}
	
	public static void login()
	{
		System.out.println(username.getText() + " " + password.getText());
		ChatMain.display();
		window.close();
	}
	
	public void consumeEvent(WindowEvent event)
	{
		event.consume();
	}
	
}

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Login 
{
	private static Stage window;
	
	private static Button loginButton;
	
	private static Label loginLabel;
	
	private static Scene scene;
	
	private static StackPane loginLayout;
	
	public static void display()
	{
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		loginLabel = new Label("Login to use our shitty chat");
		//loginLabel.setAlignment(Pos.TOP_CENTER);
		
		loginButton = new Button("Login");
		loginButton.setOnAction(e -> login());
		
		loginLayout = new StackPane();
		loginLayout.getChildren().add(loginLabel);
		loginLayout.getChildren().add(loginButton);
		loginLayout.setAlignment(loginLabel, Pos.TOP_CENTER);
		loginLayout.setAlignment(loginButton,Pos.BOTTOM_CENTER);
		
		scene = new Scene(loginLayout, 200, 300);
		
		window.setScene(scene);
		window.setTitle("Login for Memes");
		window.show();
	}
	
	public static void login()
	{
		System.out.println("Logged in");
		window.close();
	}
	
}

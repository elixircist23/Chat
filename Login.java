import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class Login 
{
	private static Stage window;
	
	private static Button loginButton;
	
	private static Scene scene;
	
	private static VBox loginLayout;
	
	public static void display()
	{
		window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		loginButton = new Button("Login");
		loginButton.setOnAction(e -> login());
		
		loginLayout = new VBox();
		loginLayout.getChildren().add(loginButton);
			
		scene = new Scene(loginLayout, 200, 300);
		
		window.setScene(scene);
		
		window.show();
	}
	
	public static void login()
	{
		System.out.println("Logging in");
	}
	
}

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChatMain extends Application
{
	private Stage window;
	
	private Scene chatrooms;
		
	private StackPane crLayout;
	
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception 
	{	
		//stages
		window = primaryStage;
		
		
		//layouts
		crLayout = new StackPane();

		//scenes
		chatrooms = new Scene(crLayout, 800, 600);
		
		window.setScene(chatrooms);
		window.setTitle("Shitty Chat Program");
		window.show();
		Login.display();
	}
}

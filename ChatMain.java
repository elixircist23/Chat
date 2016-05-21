import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChatMain extends Application {
	private static Stage window;

	private static Scene chatrooms;

	private static BorderPane borderpane;
	private static StackPane centerChat;
	private static HBox bottomChat;
	
	private static TextArea chatRoomMsgs;
	private static TextField message;
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		display();
	}

	public static void display() {
		// stages
		window = new Stage();

		//nodes
		chatRoomMsgs = new TextArea();
		chatRoomMsgs.setMinSize(300, 300);
		chatRoomMsgs.setMaxSize(300,  300);
		chatRoomMsgs.setEditable(false);
		
		message = new TextField("message");
		message.setPrefWidth(400);
		
		// layouts
		centerChat = new StackPane();
		bottomChat = new HBox(10);
		bottomChat.setAlignment(Pos.TOP_CENTER);
		
		borderpane = new BorderPane();
		
		centerChat.getChildren().add(chatRoomMsgs);
		bottomChat.getChildren().addAll(message, new Button("Send"));
		
		borderpane.setCenter(centerChat);
		borderpane.setBottom(bottomChat);
		
		// scenes
		chatrooms = new Scene(borderpane, 800, 600);
		
		
		window.setScene(chatrooms);
		window.setTitle("Shitty Chat Program");
		window.show();
		System.out.println("ayy");
	}
}

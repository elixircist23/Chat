package application;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class ChatMain extends Application {
	
	private static Stage window;

	private static Scene chatrooms;

	private static BorderPane borderpane;
	private static StackPane centerChat;
	private static HBox bottomChat;
	
	private static TextArea chatRoomMsgs;
	private static TextField message;
	
	private static Parent root = null;
	@Override
	public void start(Stage primaryStage) throws IOException {
		// try {
		// BorderPane root = new BorderPane();
		// Scene scene = new Scene(root,400,400);
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// primaryStage.setScene(scene);
		// primaryStage.show();
		// } catch(Exception e) {
		// e.printStackTrace();
		// }
		final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChatMain.fxml"));
		root = (Parent) fxmlLoader.load();
		display();
	}

	public static void main(String[] args) {
		launch(args);
	}
	public static void display() {
		// stages
		window = new Stage();

		// scenes
		chatrooms = new Scene(root, 800, 600);
		
		window.setScene(chatrooms);
		window.setTitle("Shitty Chat Program");
		window.show();
	}
}


////nodes
//chatRoomMsgs = new TextArea();
//chatRoomMsgs.setMinSize(300, 300);
//chatRoomMsgs.setMaxSize(300,  300);
//chatRoomMsgs.setEditable(false);
//
//message = new TextField("message");
//message.setPrefWidth(400);
//
//// layouts
//centerChat = new StackPane();
//bottomChat = new HBox(10);
//bottomChat.setAlignment(Pos.TOP_CENTER);
//
//borderpane = new BorderPane();
//
//centerChat.getChildren().add(chatRoomMsgs);
//bottomChat.getChildren().addAll(message, new Button("Send"));
//
//borderpane.setCenter(centerChat);
//borderpane.setBottom(bottomChat);
//
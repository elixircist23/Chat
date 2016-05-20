import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ChatMain extends Application {
	private static Stage window;

	private static Scene chatrooms;

	private static StackPane crLayout;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

	}

	public static void display() {
		// stages
		window = new Stage();

		// layouts
		crLayout = new StackPane();

		// scenes
		chatrooms = new Scene(crLayout, 800, 600);

		window.setScene(chatrooms);
		window.setTitle("Shitty Chat Program");
		window.show();
	}
}

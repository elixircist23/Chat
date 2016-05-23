import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Test extends Application {

	private Button button;
	private Button switchScene;
	private Button switchBack;
	private static String number;
	private static int x;

	private Scene scene1, scene2;

	public static void main(String[] args) {
		number = x + "";
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label label1 = new Label("first scene");

		primaryStage.setTitle("ChatRoom");

		// addition button
		button = new Button(number);
		// button.setOnAction(e -> {
		// x++;
		// button.setText(x + "");
		// });
		//button.setOnAction(e -> Login.display("ayy", "lmao"));

		// switch scene button
		switchScene = new Button("Click to switch scene");
		switchScene.setOnAction(e -> primaryStage.setScene(scene2));

		// switch back button
		switchBack = new Button("this is ghetto scene, go back to the first scene");
		switchBack.setOnAction(e -> primaryStage.setScene(scene1));
		// first layout
		VBox layout = new VBox(20);
		layout.getChildren().addAll(label1, button, switchScene);
		scene1 = new Scene(layout, 800, 600);

		// second layout
		StackPane layout2 = new StackPane();
		layout2.getChildren().add(switchBack);
		scene2 = new Scene(layout2, 800, 600);

		primaryStage.setScene(scene1);
		primaryStage.setTitle("RHQ");
		primaryStage.show();
	}

}

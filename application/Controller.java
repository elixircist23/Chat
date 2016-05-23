package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.*;

public class Controller implements Initializable {

	@FXML
	private Button sendButton;
	@FXML
	private TextField messageField;
	@FXML
	private TextArea chatArea;

	private int x = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// messageField.addEventFilter(KeyEvent.KEY_TYPED, new
		// EventHandler<KeyEvent>() {
		//
		// @Override
		// public void handle(KeyEvent event) {
		//
		// enterPressed(event);
		// event.consume();
		// }
		// });

	}

	@FXML
	public void sendClicked(ActionEvent event) {
		event.consume();
		send(event);
	}

	@FXML
	public void enterPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER) {
			event.consume();
			send(event);
		}
	}

	@FXML
	public void send(Event event) {
		// event.consume();
		if (!(messageField.getText().trim() == "")) {
			chatArea.appendText(messageField.getText().trim() + "\n");
			messageField.clear();
		}
		x += 1;
		System.out.println(x + messageField.getText().trim());
	}

}

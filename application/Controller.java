package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;

public class Controller 
{
	
	@FXML private Button sendButton;
	@FXML private TextField messageField;
	@FXML private TextArea chatArea;
	
	@FXML
	public void sendClicked(ActionEvent event)
	{
		chatArea.appendText(messageField.getText() + "\n");
		messageField.clear();
	}
}

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.*;

public class Controller 
{
	
	@FXML private Button sendButton;
	@FXML private TextField messageField;
	@FXML private TextArea chatArea;
	
	@FXML
	public void send()
	{
		chatArea.appendText(messageField.getText() + "\n");
		messageField.clear();
	}
	
	@FXML
	public void sendClicked(ActionEvent event)
	{
		send();
	}
	
	@FXML
	public void enterPressed(KeyEvent event)
	{
		if(event.getCode() == KeyCode.ENTER)
		{
			send();
		}
	}
}

package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.SubscriberUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import server.Subscriber;

public class SubscriberFormController implements Initializable {
	private Subscriber s;
	
	@FXML
	private Label lblSID;
	@FXML
	private Label lblName;
	@FXML
	private Label lblHistoryID;
	@FXML
	private Label lblPhoneNum;
	@FXML
	private Label lblEmail;
	
	
	@FXML
	private TextField txtSID;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtHistory;
	@FXML
	private TextField txtPhoneNum;
	@FXML
	private TextField txtEmail;
	
	@FXML
	private Button btnclose=null;
	
	
	
	@FXML
	private TextField idText;
	
	@FXML
	private Button saveButton;
	
	ObservableList<String> list;
		
	public void loadSubscriber(Subscriber s1) {
		this.s=s1;
		this.txtEmail.setText(s.getEmail());
		this.txtName.setText(s.getName());		
		this.txtPhoneNum.setText(s.getPNumber()); 
		this.txtSID.setText(s.getSID());
		this.txtHistory.setText(s.getHistID());
		
	}
	
	
	public void getExitBtn(ActionEvent event) throws Exception {
		((Stage)((Node)event.getSource()).getScene().getWindow()).close();
		SubscriberUI.primaryStage.show();
	}
	
	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("/gui/SubscriberFrom.fxml"));
				
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/gui/SubscriberFrom.css").toExternalForm());
		primaryStage.setTitle("Subscriber Managment Tool");
		primaryStage.setScene(scene);
		
		primaryStage.show();	 	   
	}
	
//	public void save(ActionEvent event) throws Exception {
//		
//		Student newStudent = new Student(idText.getText() , txtName.getText() , txtSurname.getText() ,faculty);
//		ClientUI.chat.saveStudentInfo(newStudent , s);
//		((Stage)((Node)event.getSource()).getScene().getWindow()).close();
//		ClientUI.primaryStage.show();
//	}
	
	
	public void save(ActionEvent event) throws Exception {
		if (s != null) {
	        // Update the existing subscriber's email and phone number
	        String newEmail = txtEmail.getText();
	        String newPhoneNumber = txtPhoneNum.getText();

	        if (newEmail != null && !newEmail.trim().isEmpty()) {
	            s.setEmail(newEmail);
	        }
	        else {
	            System.out.println("Email cannot be empty.");
	        }

	        if (newPhoneNumber != null && !newPhoneNumber.trim().isEmpty()) {
	            s.setPNumber(newPhoneNumber);
	        } else {
	            System.out.println("Phone number cannot be empty.");
	        }

	        // Save the updated subscriber info
	       // ClientUI.chat.saveSubscriberInfo(s); // Assuming this method updates the subscriber in the backend
	        //System.out.println("Subscriber info updated successfully.");
	        
			ArrayList<String> editS = new ArrayList<String>();
			editS.add("subscriber:edit");
			editS.add("subscriber_id:"+s.getSID());
			editS.add("subscriber_phone_number:"+newPhoneNumber);
			editS.add("subscriber_email:"+newEmail);
			
			SubscriberUI.chat.accept(editS);
			
	        // Close the current window and show the primary stage
	        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
	        SubscriberUI.primaryStage.show();
    	} 
		else 
	    {
	        System.out.println("Subscriber object not found. Cannot update.");
	    }
		
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		//setFacultyComboBox();		
	}
	
}

package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import client.ChatClient;
import client.ClientController;
import client.SubscriberUI;
import common.ChatIF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.Subscriber;


public  class SubscriberEntryController   {
	private SubscriberFormController sfc;	
	private static int itemIndex = 3;
	
	@FXML
	private Button btnExit = null;
	
	@FXML
	private Button btnSend = null;
	
	@FXML
	private TextField idtxt;
	
	private String getID() {
		return idtxt.getText();
	}
	
	public void Send(ActionEvent event) throws Exception {
		String id;
		FXMLLoader loader = new FXMLLoader();
		
		id=getID();
		if(id.trim().isEmpty())
		{

			System.out.println("You must enter an id number");	
		}
		else
		{
			ArrayList<String> SID = new ArrayList<String>();
			String toSend = "subscriber_id:";
			toSend=toSend.concat(id);
			SID.add("subscriber:info");
			SID.add(toSend);
			
			SubscriberUI.chat.accept(SID);
			
		
			if(ChatClient.s1.getSID()==null)
			{
				System.out.println("Subscriber ID Not Found");
				
			}
			else {
				System.out.println("Subscriber ID Found");
				((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
				Stage primaryStage = new Stage();
				Pane root = loader.load(getClass().getResource("/gui/SubscriberForm.fxml").openStream());
				SubscriberFormController subscriberFormController = loader.getController();		
				//System.out.println(ChatClient.s1);
				subscriberFormController.loadSubscriber(ChatClient.s1);
			
				Scene scene = new Scene(root);			
				scene.getStylesheets().add(getClass().getResource("/gui/SubscriberForm.css").toExternalForm());
				primaryStage.setTitle("Subscriber Managment Tool");
	
				primaryStage.setScene(scene);		
				primaryStage.show();
			}
		}
	}

	public void start(Stage primaryStage) throws Exception {	

		Parent root = FXMLLoader.load(getClass().getResource("SubscriberEntry.fxml"));
				
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("SubscriberEntry.css").toExternalForm());
		primaryStage.setTitle("Subscriber Entry Tool");
		primaryStage.setScene(scene);
		
		primaryStage.show();	 	   
	}
	
	public void getExitBtn(ActionEvent event) throws Exception {
		System.out.println("exit Academic Tool");
		System.exit(0);
	}
	
	public void loadSubscriber(Subscriber s1) {
		this.sfc.loadSubscriber(s1);
	}	
	
	public  void display(String message) {
		System.out.println("message");
		
	}
	
}

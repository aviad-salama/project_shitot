package client;

import gui.SubscriberEntryController;
import javafx.application.Application;
import javafx.stage.Stage;

public class SubscriberUI extends Application {
	public static ClientController chat; //only one instance
	public static Stage primaryStage ;
	private static String host="localhost";
	public static void main(String args[] ) throws Exception
	   { 
		try
		{
			host = args[0];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			host = "localhost";
		}
		    launch(args);  
	   } // end main
	 
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		 chat = new ClientController(host, 5555);
		SubscriberUI.primaryStage = primaryStage ;				  		
		SubscriberEntryController aFrame = new SubscriberEntryController(); // create SubscriberEntry
		 
		aFrame.start(primaryStage);
	}
}

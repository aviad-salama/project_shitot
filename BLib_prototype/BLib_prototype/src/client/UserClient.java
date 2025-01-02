package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ocsf.client.AbstractClient;


//BLib user-side
public class UserClient extends AbstractClient {
	
		final public static int DEFAULT_PORT = 5555;

	  	/**
	   	* The default port to listen on.
	   	*/
		boolean editMode;
		int userID;//the user of the client. prototype-version.

		/**
		   * Constructs an instance of the chat client.
		   *
		   * @param host The server to connect to.
		   * @param port The port number to connect on.
		   * @param clientUI The interface type variable.
		   */
		  
		  public UserClient(String host, int port, int userID) throws IOException 
		  {
			  
			  super(host, port); //Call the superclass constructor
			  
			  this.userID=userID; 
			  editMode=false;
			  try {
				  openConnection(); 
			  }
			  catch(IOException exception) 
			  {
				  	System.out.println("Error: Can't setup connection!" + " Terminating client : " + exception.getMessage());
			  		System.exit(1);	
			  }
			  
		  }

		  
		  //Instance methods ************************************************
		    
		  /**
		   * This method handles all data that comes in from the server.
		   *
		   * @param msg The message from the server.
		   */
		  private void infoRequest()
		  {
			  ArrayList<String> requestInfo = new ArrayList<String>();
			  requestInfo.add("info request");
			  requestInfo.add(String.valueOf(userID));
			  try {
				sendToServer(requestInfo);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
		  }
		  private void editRequest(String msg)
		  {
			  ArrayList<String> fields = new ArrayList<String>();
			  
			  boolean flag=true;
			  int i=0,j,k;
			//  subscriber_id:1;
			//  subscriber_email:newfakemail@fake.com;
			 // subscriber_phone_number:0573051111;
			  fields.add("subscriber_id:1");
			  fields.add("subscriber_email:nASDLKJFNSDJKLFl@fake.com");
			  fields.add("subscriber_phone_number:0573051331");

			  
			  try {
				  System.out.println(fields);
				  sendToServer(fields);
			  }
			  catch (Exception ex) 
			    {
			      System.out.println
			        ("Unexpected error while reading from console!");
			    }
		  }
		  public void handleMessageFromServer(Object msg) 
		  {
			  ArrayList data;
			  if (msg instanceof ArrayList)
			  {
				  data = (ArrayList<String>)msg;
				  
				  if (data.get(0).equals("StudentInfo"))
					{
						//OPEN STUDENT WINDOW
					    //insert data into STUDENT WINDOW fields.
					}
			  }
			  
			  //System.out.println(((ArrayList<String>)msg).toString());
			  
		  }

		  
		  public void accept() 
		  {
		    try
		    {
		      BufferedReader fromConsole = 
		        new BufferedReader(new InputStreamReader(System.in));
		      String message;

		      while (true) 
		      {
		        message = fromConsole.readLine();
		        System.out.println(message);
		        if (message.equals("info")) {
		        	infoRequest();	
		        }
		        else if (message.contains("update")) 
		        	editRequest(message);
	        	else
		        	sendToServer(message);
		      }
		    } 
		    catch (Exception ex) 
		    {
		      System.out.println
		        ("Unexpected error while reading from console!");
		    }
		    
		    
		  }
		  
		  /**
		   * This method terminates the client.
		   */
		  public void quit()
		  {
		    try
		    {
		      closeConnection();
		    }
		    catch(IOException e) {}
		    System.exit(0);
		  }
		  
		  
		  public static void main(String[] args)
		  {
			  
			
		    String host = "";
		    int port = 5555;  //The port number

		    
		    
		    /*
		     * 
		     * 
		     * tests:subscriber_id:1;subscriber_email:newfakemail@fake.com;subscriber_phone_number:0573051111;
		     * 
		     */
		    try {
		        // Get host from args or use default
		        host = (args.length > 0) ? args[0] : "localhost";
		        // Create an instance of UserClient
		        UserClient UC = new UserClient(host, port, 1); // temp user
		        UC.accept(); // Wait for console data
		    } catch (IOException e) {
		        System.out.println("Failed to initialize UserClient: " + e.getMessage());
		        e.printStackTrace();
		    }
		    
		    
		    
		    
		  }
	

}

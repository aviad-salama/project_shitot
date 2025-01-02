// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 
//A
package client;

import ocsf.client.*;
import server.Subscriber;
import client.*;
import common.ChatIF;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */


// CHECKING COMMIT PART 989869168 //


public class ChatClient extends AbstractClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 
  public static boolean awaitResponse = false;
  public static Subscriber  s1 = new Subscriber(null,null,null,null,null);

  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public ChatClient(String host, int port, ChatIF clientUI) 
    throws IOException 
  {
    super(host, port); //Call the superclass constructor
    this.clientUI = clientUI;
    openConnection();
  }

  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public void handleMessageFromServer(Object msg) 
  {
	  
//	  System.out.println("--> handleMessageFromServer");
////	  if(msg instanceof SaveStudentInfo) {
////		  System.out.println("the info saved successfully");
////		  s1 = ((SaveStudentInfo)msg).newStudent;
////		  return;
////	  }
//	  if (msg instanceof ArrayList)
//	  {
//		  (ArrayList<String>)msg
//		  
//	  }
	  
//	  String st;
//	  st=msg.toString();
//	  String[] result = st.split("\\s");
	  
	  awaitResponse = false;
	  ArrayList<String> data = new ArrayList<String>();
	  
	  System.out.println("--> handleMessageFromServer");
	  
	  if (msg instanceof ArrayList)
	  {
		 
		 data=(ArrayList<String>)msg;
		 if (data.get(0).contains("subscriber"))
		 {
			 s1.setSID(data.get(1));
		 	 s1.setName(data.get(2));
		 	 s1.setHistID(data.get(3));
		 	 s1.setPNumber(data.get(4));
		 	 s1.setEmail(data.get(5));
		 }

		 
		 
	  }
	  

	 
  }

  
  
 
  
  
  
  
  /**
   * This method handles all data coming from the UI            
   *
   * @param message The message from the UI.    
   */
  
  public void handleMessageFromClientUI(Object message)  
  {
    try
    {
    	//openConnection();//in order to send more than one message
       	awaitResponse = true;
    	sendToServer(message);
		// wait for response
		while (awaitResponse) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    }
    
    catch(IOException e)
    {
    	e.printStackTrace();
      clientUI.display("Could not send message to server: Terminating client."+ e);
      quit();
    }
    
  }
  
//  public void handleSavingStudentInfo(Student newStudent , Student oldStudent) {
//	  //SaveStudentInfo ssi = new SaveStudentInfo(newStudent , oldStudent) ;
//	  try {
//		openConnection();
//		sendToServer(ssi);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//  }

  
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
}
//End of ChatClient class

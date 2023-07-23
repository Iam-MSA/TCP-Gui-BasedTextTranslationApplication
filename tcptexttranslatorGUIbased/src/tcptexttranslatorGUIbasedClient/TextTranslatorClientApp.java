package tcptexttranslatorGUIbasedClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/** 
 * This client class will send request to server for text translation
 * 
 * @author Mirza Sahid Afridi
 */
public class TextTranslatorClientApp {

	// declare static variable for client front-end GUI
	public static ClientFrame client = new ClientFrame();
	
	public static void main(String[] args) 
	{	
		
		client.setVisible(true);
	}
	
	
	static public void btnPressed()
	{
		try
		{	
			// 1. Connect to the server
			Socket socket = new Socket(InetAddress.getLocalHost(), 6999);
			
			// Update the status of the connection
			client.updateConnectionStatus(socket.isConnected());
			
			// 2. Send request to server, request is made by default
			// 3. Accept response from the server - read from network
			
			DataInputStream datainputstream = new DataInputStream
					(socket.getInputStream());
			
			// Obtain input from the client front-end
			String textinput = client.getText();
			String language = client.getLanguage();
			
			// create stream to write data on network
			DataOutputStream dataoutputStream = new DataOutputStream
					(socket.getOutputStream());
			
			// if client press the button from front end 
			if(client.ispressed() == true)
			{
				// write the data
				dataoutputStream.writeUTF(textinput);
				dataoutputStream.writeUTF(language);
				
				// read the translated text data
				String translated = datainputstream.readUTF();
				
				// parse back to front-end
				client.setAnsLbl(translated);
				client.updatebtn(false);
			}
			
			// clear the stream
			dataoutputStream.flush();
			
			// close the stream
			datainputstream.close();
			dataoutputStream.close();
			socket.close();
			
			textinput = "";
			language = "";
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		} 
	}
}
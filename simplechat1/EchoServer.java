
// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class EchoServer extends AbstractServer {
	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */
	public EchoServer(int port) {
		super(port);
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		System.out.println("Message received: " + msg + " from " + client);
		this.sendToAllClients(msg);
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	// **** Changed for E49
	/**
	 * This method overrides the one in the superclass. Called when the has secured
	 * the connection.
	 */
	protected void clientConnected(ConnectionToClient client) {
		// Outputs a message when a client connects
		System.out.println(client + " Connected to the server.");
	}

	// **** Changed for E49
	/**
	 * This method overrides the one in the superclass. Called when the server disconnects
	 * from the client due to exception encounter.
	 */
	protected void clientException(ConnectionToClient client, Throwable exception) {
		// Outputs a disconnect message
		System.out.println("Client disconnected from the server");
	}

	// **** Changed for E49
	/**
	 * This method overrides the one in the superclass. Called when the client has
	 * disconnected from the server
	 */
	protected void clientDisconnected(ConnectionToClient client) {
		// Outputs a disconnect message
		System.out.println(client + "Disconnected from the server");
	}

	// Class methods ***************************************************

	/**
	 * This method is responsible for the creation of the server instance (there is
	 * no UI in this phase).
	 *
	 * @param args[0] The port number to listen on. Defaults to 5555 if no argument
	 *        is entered.
	 */
	public static void main(String[] args) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[0]); // Get port from command line
			System.out.println("Connecting to port " + Integer.toString(port));
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
			System.out.println("Connecting to default port " + Integer.toString(port));
		}

		EchoServer sv = new EchoServer(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
}
//End of EchoServer class

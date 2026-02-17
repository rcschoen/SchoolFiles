package assignment2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.LinkedList;

public class loadbalancer {
	public static void main(String[] args) 
	{
		int clientPort, serverPort = 5000;
		byte[] buf= new byte[65535];	//it's better to keep the buffer size with in the MTU limit i.e. 1472 bytes
		String rcvMessage, sndMessage;
		DatagramPacket sndPacket, rcvPacket;
		InetAddress clientAddress;
		
		try {
			
			DatagramSocket serverSocket = new DatagramSocket(serverPort); // create a server socket to send and receive messages
					
			while(true) 
			{
				System.out.println("Waiting for incoming connections");
				
				rcvPacket = new DatagramPacket(buf, buf.length); //initialize the receive packet such that incoming data is stored in buf
				serverSocket.receive(rcvPacket); //wait for incoming data from client
				clientPort = rcvPacket.getPort(); //retrieve client's port number
				clientAddress = rcvPacket.getAddress(); //retrieve client's ip address
				System.out.println("Request from client with IP address "+ clientAddress+ " and Port number "+clientPort);
				rcvMessage = new String(rcvPacket.getData(), 0, rcvPacket.getLength()); 
				
				 
				
				sndPacket = new DatagramPacket(MAKETHIS WHAT I SEND TO CLIENT, clientAddress, clientPort ); 
				
				serverSocket.send(sndPacket);//send the packet to the client (client details are in the sndPacket) through the serverSocket
				System.out.println("Response sent to client with IP address "+ clientAddress+ " and Port number "+clientPort);
				
				buf = new byte[1000];//reset the buf and continue waiting for new incoming requests
				
			}
					
		}
		catch(Exception E) {
			E.printStackTrace();
		}
		
	}

}


// Reagan Schoenow
// CIS 301
// Assignment 1
package assignment2;
import java.net.*;
import java.io.*;
import java.util.*;
	public class client {
		public static void main(String[] args) {
			try {
				DatagramSocket socket = new DatagramSocket();
				// Use "localhost" when client and server are on the same computer
				// Change this to the server machine's IP address when running over a network
				InetAddress loadAddress = InetAddress.getByName("localhost");
				
				
				
				
				
				
				InetAddress serverAddress = InetAddress.getByName("localhost");
				Scanner sc = new Scanner(System.in);
				System.out.print("Enter first and last name: ");
				String name = sc.nextLine().toUpperCase();
				// Encrypt the user input before sending to server
				String encryptedName = encryptData(name);
				byte[] sendData = encryptedName.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress,4000);
				socket.send(sendPacket);
				byte[] receiveData = new byte[1024];
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				socket.receive(receivePacket);
				// Convert received encrypted SSN into a String
				String encryptedSSN = new String(receivePacket.getData(), 0, receivePacket.getLength());
				// Decrypt SSN received from server
				String plainSSN = decryptData(encryptedSSN);
				System.out.println("SSN received from server: " + plainSSN);
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// ================= Caesar Cipher Encryption =================
		// Encrypts text using a Caesar cipher with a right shift of 2
		public static String encryptData(String plainText) {
			StringBuilder encrypted = new StringBuilder();
			plainText = plainText.toUpperCase();
			for (int i = 0; i < plainText.length(); i++) {
				char c = plainText.charAt(i);
				if (c >= 'A' && c <= 'Z') {
					c = (char) ((c - 'A' + 2) % 26 + 'A');
				}
				encrypted.append(c);
			}
			return encrypted.toString();
		}
		// Decrypts text by reversing the Caesar cipher shift
		public static String decryptData(String cipherText) {
			StringBuilder decrypted = new StringBuilder();
			cipherText = cipherText.toUpperCase();
			for (int i = 0; i < cipherText.length(); i++) {
				char c = cipherText.charAt(i);
				if (c >= 'A' && c <= 'Z') {
					c = (char) ((c - 'A' - 2 + 26) % 26 + 'A');
				}
				decrypted.append(c);
			}
			return decrypted.toString();
		}
	}
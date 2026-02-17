// Reagan Schoenow
// CIS 301
// Assignment 2
package assignment2;
import java.net.*;
import java.io.*;
import java.util.*;
	public class server2 {
		
		// Stores username (First Last) and corresponding SSN
		private static Map<String, String> userSSNMap = new HashMap<>();
		public static void main(String[] args) {
			// Load SSN data from text file when server starts
			loadUsers();
			try {
				DatagramSocket socket = new DatagramSocket(4020);
				byte[] receiveData = new byte[1024];
				System.out.println("Server started on port 4020...");
				while (true) {
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					socket.receive(receivePacket);
					
					// Convert received encrypted bytes into a String
					String encryptedName = new String(receivePacket.getData(), 0, receivePacket.getLength());
					
					// Decrypt username received from client
					String plainName = decryptData(encryptedName);
					System.out.println("Received (decrypted): " + plainName);
					
					// Look up SSN for the given user
					String ssn = userSSNMap.getOrDefault(plainName, "NOT FOUND");
					
					// Encrypt SSN before sending back to client
					String encryptedSSN = encryptData(ssn);
					byte[] sendData = encryptedSSN.getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(),receivePacket.getPort());
					socket.send(sendPacket);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	
		// ================= Caesar Cipher Encryption =================
		// Encrypts plain text using a right shift of 2
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
		
		// Decrypts cipher text by reversing the Caesar shift
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
		
		// ================= File Reading Logic =================
		// Reads SSN.txt and stores First Last -> SSN in a HashMap
		public static void loadUsers() {
			try {
				File file = new File("SSN.txt");
				Scanner input = new Scanner(file);
				// Allows reading comma-separated values
				input.useDelimiter(",|\n");
				while (input.hasNext()) {
					String firstName = input.next().toUpperCase();
					String lastName = input.next().toUpperCase();
					String ssn = input.next();
					userSSNMap.put(firstName + " " + lastName, ssn);
				}
				input.close();
				System.out.println("SSN file loaded successfully.");
			} catch (FileNotFoundException e) {
				System.out.println("SSN.txt file not found.");
			}
		}
	}
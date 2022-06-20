/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it145;
/**
 *
 * @author 1559907_snhu
 */

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException; //Needed to read local file
import java.security.MessageDigest; //MessageDigest and NoSuchAlgorithm needed for password
import java.security.NoSuchAlgorithmException;
public class AuthenticationSystem {

    /**
     * @throws java.io.IOException
     * @throws java.security.NoSuchAlgorithmException
     * 
     */
    
    // Moved all variables outside of main class
    public static Scanner scnr = new Scanner(System.in);
    public static Scanner inFS = null;
    public static FileInputStream credentialsStream = null;
    public static FileInputStream roleStream = null;
    public static String userName = "";
    public static String password = "";
    public static String entry = "";
    public static int attemptNum = 1; //Set to one due to one attempt being made everytime
    public static int numSearches = 0;
    public static LogIn fieldEntry = new LogIn();
    public static boolean authenticator = false;
    
    
    // Moved almost all functions to separate methods
    public static void userEntry() {
        System.out.println("Enter username (type \"quit\" to exit): "); // Enter username or quit
            entry = scnr.nextLine();
            userName = fieldEntry.getUsername(entry);
    }
    
    public static void passwordEntry() {
        System.out.println("Enter password (type \"quit\" to exit): "); // Enter password or quit
            entry = scnr.nextLine();
            password = fieldEntry.getPassword(entry);
    }
    
    // Generates password with exception for filestream/password failure
    public static void credentialLogin() throws FileNotFoundException, NoSuchAlgorithmException {
        credentialsStream = new FileInputStream("credentials.txt");
            inFS = new Scanner(credentialsStream);
            
            String original = password;
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(original.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
	    for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
	    }
            /*Scans through credential file for a match to username and password
              Follows pattern of scanning two lines, skipping two, then printing next one */
            if (userName.equals(inFS.next()) && sb.toString().equals(inFS.next())) {
                authenticator = true;
                inFS.next();
                inFS.next();
                System.out.println(inFS.next());
                numSearches = 1;
            }
            inFS.nextLine();
            if (userName.equals(inFS.next()) && sb.toString().equals(inFS.next())) {
                authenticator = true;
                inFS.next();
                inFS.next();
                System.out.println(inFS.next());
                numSearches = 2;
            }
            inFS.nextLine();
            if (userName.equals(inFS.next()) && sb.toString().equals(inFS.next())) {
                authenticator = true;
                inFS.next();
                inFS.next();
                System.out.println(inFS.next());
                numSearches = 3;
            }
            inFS.nextLine();
            if (userName.equals(inFS.next()) && sb.toString().equals(inFS.next())) {
                authenticator = true;
                inFS.next();
                inFS.next();
                System.out.println(inFS.next());
                numSearches = 4;
            }
            inFS.nextLine();
            if (userName.equals(inFS.next()) && sb.toString().equals(inFS.next())) {
                authenticator = true;
                inFS.next();
                System.out.println(inFS.next());
                numSearches = 5;
            }
            inFS.nextLine();
            if (userName.equals(inFS.next()) && sb.toString().equals(inFS.next())) {
                authenticator = true;
                inFS.next();
                System.out.println(inFS.next());
                numSearches = 6;
            }
    }
    
    public static void roleInfo() throws FileNotFoundException, IOException {
        /*User can view appropriate file for as long as they wish.
            Can also log in as another user after selecting 'n'
            */
            if (numSearches == 1 || numSearches == 4) {
                String yesNo = "";
                authenticator = true;
                while (!yesNo.equals("n")) {
                    
                    // Input any document here--this was my original file
                    roleStream = new FileInputStream("\\\\apporto.com\\dfs\\Users\\"
                    + "1559907_snhu\\Documents\\zookeeper.txt");
                    inFS = new Scanner(roleStream);
                    System.out.println("Opening zookeeper file...");
                    
                    // Follow format for document: print line 1, skip line 2, print line 3
                    System.out.println(inFS.nextLine()); 
                    inFS.nextLine();
                    System.out.println(inFS.nextLine());
                    System.out.println("Continue? y/n");
                    yesNo = scnr.next();
                    scnr.nextLine();
                    roleStream.close();
                }
            }
            
            if (numSearches == 2 || numSearches == 6) {
                String yesNo = "";
                authenticator = true;
                while (!yesNo.equals("n")) {
                    roleStream = new FileInputStream("\\\\apporto.com\\dfs\\Users\\"
                    + "1559907_snhu\\Documents\\admin.txt");
                    inFS = new Scanner(roleStream);
                    System.out.println("Opening admin file...");
                    System.out.println(inFS.nextLine());
                    inFS.nextLine();
                    System.out.println(inFS.nextLine());
                    System.out.println("Continue? y/n");
                    yesNo = scnr.next();
                    scnr.nextLine();
                    roleStream.close();
                }
            }
            if (numSearches == 3 || numSearches == 5) {
                String yesNo = "";
                authenticator = true;
                while (!yesNo.equals("n")) {
                    roleStream = new FileInputStream("\\\\apporto.com\\dfs\\Users\\"
                    + "1559907_snhu\\Documents\\veterinarian.txt");
                    inFS = new Scanner(roleStream);
                    System.out.println("Opening veterinarian file...");
                    System.out.println(inFS.nextLine());
                    inFS.nextLine();
                    System.out.println(inFS.nextLine());
                    System.out.println("Continue? y/n");
                    yesNo = scnr.next();
                    scnr.nextLine();
                    roleStream.close();
                }
            }
    }
    
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        
        //Main loop. Checks for username and password and allows user to exit
        while ((!"quit".equals(userName)) && (!"quit".equals(password))) {
            numSearches = 0;
            
            userEntry();
            
            if (userName.equals("quit")) {
                continue;
            }
            passwordEntry();
            
            if (password.equals("quit")) {
                continue;
            }
            credentialLogin();
        
            roleInfo();
            
            if (!authenticator) { //Checks if credentials were matched. Exits if not.
                if (attemptNum == 3) {
                    break;
                }
                ++ attemptNum; //Counter adds up to three
                System.out.println("Incorrect username and/or password. Try again."
                + " You are limited to three attempts. Attempt no. " + attemptNum);
            }
        }
        
        if (attemptNum == 3) { //Two separate exit screens. One voluntary, one involuntary.
            System.out.println("Too many failed attempts. Exiting.");
        }
        else {
            System.out.println("Exiting.");
        }        
    }
}
        
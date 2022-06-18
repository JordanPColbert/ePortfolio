/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contact;

/**
 *
 * @author Jordan Colbert
 */
public class Contact {
    public static String contactID;
    public static String firstName;
    public static String lastName;
    public static String phoneNum;
    public static String address;
    
    // Constructor that passes all variables and ensures correct length
    public Contact(String contactID, String firstName, String lastName,
                   String phoneNum, String address){
        if (contactID.length() > 10 || contactID == null){
            throw new IllegalArgumentException("Invalid ID");
        }
        
        if (firstName.length() > 10 || lastName.length() > 10 ||
            firstName == null || lastName == null){
            throw new IllegalArgumentException("Invalid name");
        }
        
        if (phoneNum.length() != 10 || phoneNum == null){
            throw new IllegalArgumentException("Invalid phone number");
        }
        
        if (address.length() > 30 || address == null){
            throw new IllegalArgumentException("Invalid address");
        }
        
        this.contactID = contactID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.address = address;
    }
    
    // Retrieve ID
    public String getID(){
        return contactID;
    }
    
    // Retrieve first name
    public String getFirstName(){
        return firstName;
    }
    
    // Retrieve last name
    public String getLastName(){
        return lastName;
    }
    
    // Retrieve phone number
    public String getPhoneNum(){
        return phoneNum;
    }
    
    // Retrieve address
    public String getAddress(){
        return address;
    }
}
    

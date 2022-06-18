/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contact;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jordan Colbert
 */
public class ContactService {
    
    // Create hash map to be populated with contacts from Contact class  
    public static Map<String, String[]> contactList = new HashMap<>();
    
    public static String contactIDEntry;
    public static String firstNameEntry;
    public static String lastNameEntry;
    public static String phoneNumEntry;
    public static String addressEntry;
           
    // Method populates hash map with key and string array with entry types
    public void createEntry() {
        
        // Define variables in first method to prevent issues with method order
        contactIDEntry = Contact.contactID;
        firstNameEntry = Contact.firstName;
        lastNameEntry = Contact.lastName;
        phoneNumEntry = Contact.phoneNum;
        addressEntry = Contact.address;
        
        String[] contactInfo = new String[]{firstNameEntry, lastNameEntry,
        phoneNumEntry, addressEntry};
        contactList.put(contactIDEntry, contactInfo);
    }
    
    // Returns value passed with ID
    public static String[] getEntry(String contactID) {
        return contactList.get(contactID);
    }
    
    // Deletes an entry using ID
    public void removeEntry(String contactID) {
        contactList.remove(contactID);
    }
    
    // Removes and re-enters a value/key, resulting in an update
    public void updateEntry(String contactID, String newFirstName, String newLastName, 
            String newPhoneNum, String newAddress) {
        
        new ContactService().removeEntry(contactID);
        
        contactIDEntry = contactID;
        this.firstNameEntry = newFirstName;
        this.lastNameEntry = newLastName;
        this.phoneNumEntry = newPhoneNum;
        this.addressEntry = newAddress;
        
        String[] contactInfo = new String[]{firstNameEntry, lastNameEntry,
        phoneNumEntry, addressEntry};
        contactList.put(contactIDEntry, contactInfo);
    }    
}

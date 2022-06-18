/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jordan Colbert
 */
public class ContactTest {
    
    //Verifies all variables are initialized properly
    @Test
    public void ContactTest() {
        Contact.Contact contactTest = new Contact.Contact("3439", "John", "Smith",
                "1234567890", "Some Street, IL");
        assertTrue(contactTest.getID().equals("3439"));
        assertTrue(contactTest.getFirstName().equals("John"));
        assertTrue(contactTest.getLastName().equals("Smith"));
        assertTrue(contactTest.getPhoneNum().equals("1234567890"));
        assertTrue(contactTest.getAddress().equals("Some Street, IL"));
    }
    
    // Verifies lengths of respective variables
    @Test(expected = IllegalArgumentException.class)
    public void contactIDTooLong() {
        new Contact.Contact("4894654564564", "John", "Smith", "1234567890",
            "Some Street, IL");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void firstNameTooLong() {
        new Contact.Contact("48946545", "ZechariahHarris", "Smith", "1234567890",
            "Some Street, IL");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void lastNameTooLong() {
        new Contact.Contact("489465", "John", "Smith-Robertson", "1234567890",
            "Some Street, IL");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void phoneNumTooLong() {
        new Contact.Contact("4894654", "John", "Smith", "12345678901",
            "Some Street, IL");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void phoneNumTooShort() {
        new Contact.Contact("489465", "John", "Smith", "12345678",
            "Some Street, IL");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void addressTooLong() {
        new Contact.Contact("48946545", "John", "Smith", "1234567890",
            "Some Random Street In Some Random Part of the World!");
    }
    
}

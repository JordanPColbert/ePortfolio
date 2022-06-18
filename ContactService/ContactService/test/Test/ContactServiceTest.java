/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jordan Colbert
 */
public class ContactServiceTest {
    
    // Tests utilize contains() due to usage of array
    @Test
    public void createEntryTest() {
        new Contact.Contact("3439", "John", "Smith", "1234567890", "Some Street, IL");
        new Contact.ContactService();
        new Contact.ContactService().createEntry();
        
        // Assertion now tests for exact results
        assertTrue(Arrays.toString(Contact.ContactService.getEntry("3439")).
                equals("[John, Smith, 1234567890, Some Street, IL]"));
    }
    
    @Test
    public void removeEntryTest() {
        new Contact.Contact("3439", "John", "Smith", "1234567890", "Some Street, IL");
        new Contact.ContactService();
        new Contact.ContactService().createEntry();
        new Contact.ContactService().removeEntry("3439");
        boolean isKeyPresent = Contact.ContactService.contactList.containsKey("3439");
        assertFalse(isKeyPresent);
    }
    
    @Test
    public void updateEntryTest() {
        new Contact.Contact("3439", "John", "Smith", "1234567890", "Some Street, IL");
        new Contact.ContactService();
        new Contact.ContactService().createEntry();
        new Contact.ContactService().updateEntry("3439", "Thomas", "Jefferson",
                "0574869884", "Another Street, OH");
        assertTrue(Arrays.toString(Contact.ContactService.getEntry("3439")).
                equals("[Thomas, Jefferson, 0574869884, Another Street, OH]"));
    }
    
}

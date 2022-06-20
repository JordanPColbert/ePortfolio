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
public class LogIn {
    private String usernameEntry;
    private String passwordEntry;
    
    public String getUsername(String userName) { //returns username entry
        usernameEntry = userName;
        return usernameEntry;
    }
    public String getPassword(String password) { //returns password entry
        passwordEntry = password;
        return passwordEntry;
    }
}

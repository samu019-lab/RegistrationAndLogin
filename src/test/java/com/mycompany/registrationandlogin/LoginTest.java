/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registrationandlogin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Samukelisiwe
 */
public class LoginTest {
   
    
    @Test
     public void testCheckUserNameValid() {

        Login login = new Login(
                "kyl_1",
                "Ch&cK@ke99!",
                "+27838968976",
                "Samukelisiwe",
                "Hlophe");   
        assertTrue(login.checkUserName());
     }
    @Test
    public void testCheckUserNameInvalid(){
       
        Login login = new Login(
                "kyle!!!!!!",
                "Ch&cK@ke99!",
                "+27838968976",
                "Samukelisiwe",
                "Hlophe");   
         assertFalse(login.checkUserName());
    }
    @Test
    public void testCheckPasswordComplexityValid(){
        
        Login login = new Login(
                "kyl_1",
                "Ch&cK@ke99!",
                "+27838968976",
                "Samukelisiwe",
                "Hlophe"); 
        assertTrue(login.checkPasswordComplexity());
    }
    @Test
    public void testCheckPasswordComplexityInvalid(){
        
        Login login = new Login(
                "kyl_1",
                "samuke",
                "+27838968976",
                "Samukelisiwe",
                "Hlophe"); 
        assertFalse(login.checkPasswordComplexity());
    }
    @Test
    public void testCheckCellPhoneNumberValid(){
        
        Login login = new Login(
                "kyl_1",
                "Ch&cK@ke99!",
                "+27838968976",
                "Samukelisiwe",
                "Hlophe"); 
        assertTrue(login.checkCellPhoneNumber());
    }
     @Test
    public void testCheckCellPhoneNumberInvalid(){
        
        Login login = new Login(
                "kyl_1",
                "Ch&cK@ke99!",
                "063029",
                "Samukelisiwe",
                "Hlophe"); 
        assertFalse(login.checkCellPhoneNumber());
    }

     @Test
    public void testLoginSuccessful() {

        Login login = new Login(
                "kyl_1",
                "Ch&cK@ke99!",
                "+27838968976",
                "Samukelisiwe",
                "Hlophe"
        );

        assertTrue(
                login.loginUser("kyl_1", "Ch&cK@ke99!")
        );
    }

    @Test
    public void testLoginFailed() {

        Login login = new Login(
                "kyl_1",
                "Ch&cK@ke99!",
                "+27838968976",
                "Samukelisiwe",
                "Hlophe"
        );

        assertFalse(login.loginUser("wrong_username", "wrong_password"));
         
    }
    @Test
    public void testReturnLoginStatusSuccessful() {

        Login login = new Login(
                "kyl_1",
                "Ch&cK@ke99!",
                "+27838968976",
                "Samukelisiwe",
                "Hlophe"
        );

        login.loginUser("kyl_1", "Ch&cK@ke99!");

        assertTrue(
                login.returnLoginStatus().contains("Login successful")
        );
    }
      @Test
    public void testReturnLoginStatusFailed() {

        Login login = new Login(
                "kyl_1",
                "Ch&cK@ke99!",
                "+27838968976",
                "Samukelisiwe",
                "Hlophe"
        );

        login.loginUser("wrong_username", "wrong_password");

        assertTrue(
                login.returnLoginStatus().contains("Login has failed")
        );
    }
    // 7. Test successful registration
@Test
public void testRegisterUserSuccessful() {

    Login login = new Login(
            "kyl_1",
            "Ch&cK@ke99!",
            "+27838968976",
            "Samukelisiwe",
            "Hlophe"
    );

    String result = login.registerUser("User successfully registered.");

    assertEquals("User successfully registered.", result);
}
@Test
public void testRegisterUserIncorrectUsername() {

    Login login = new Login(
            "kyle!!!!!!",
            "Ch&cK@ke99!",
            "+27838968976",
            "Samukelisiwe",
            "Hlophe"
    );

    String result = login.registerUser("User successfully registered.");

    assertTrue(result.contains("Username is not correctly formatted"));
}
@Test
public void testRegisterUserIncorrectPassword() {

    Login login = new Login(
            "kyl_1",
            "password",
            "+27838968976",
            "Samukelisiwe",
            "Hlophe"
    );

    String result = login.registerUser("User successfully registered.");

    assertTrue(result.contains("Password is not correctly formatted"));
}
@Test
public void testRegisterUserIncorrectCellPhone() {

    Login login = new Login(
            "kyl_1",
            "Ch&cK@ke99!",
            "08966553",
            "Samukelisiwe",
            "Hlophe"
    );

    String result = login.registerUser("User successfully registered.");

    assertTrue(result.contains("Cell phone number is incorrectly formatted"));
}

}
     
      

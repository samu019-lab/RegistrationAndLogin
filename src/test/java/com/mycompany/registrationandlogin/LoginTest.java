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

}

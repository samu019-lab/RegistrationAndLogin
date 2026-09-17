/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registrationandlogin;

import java.util.Scanner;



/**
 *
 * @author Samukelisiwe
 */
public class RegistrationAndLogin {

    public static void main(String[] args) {

    Scanner input = new Scanner(System.in);

        System.out.println("REGISTRATION");
    System.out.println("Enter your username");
        String userName = input.nextLine();
        System.out.println("Enter your password");
        String password = input.nextLine();
        System.out.println("Enter your cell phone number");
        String cellPhoneNumber = input.nextLine();
        System.out.println("Enter your first name");
        String firstName = input.nextLine();
        System.out.println("Enter your last name");
        String lastName = input.nextLine();
        
    Login login = new Login(userName,password,cellPhoneNumber,firstName,lastName);
        System.out.println(login.registerUser("User successfully registerd."));
        
        System.out.println("LOGIN");
        System.out.println("Enter your username to login");
        String enteredUserName = input.nextLine();
        System.out.println("Enter your password");
        String enteredpassword = input.nextLine();
        
        login.loginUser(enteredUserName, enteredpassword);
        System.out.println(login.returnLoginStatus());
    }
    }

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandlogin;

/**
 *
 * @author Samukelisiwe
 */
class Login {
     //Viarables
    public String userName;
    public String password;
    public String cellPhoneNumber;
    public String firstName;
    public String lastName;
    public boolean loginStatus;
    
    //CONSTRUCTOR
    public Login(String userName,String password,String cellPhoneNumber,String firstName,String lastName){
        this.userName = userName;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginStatus = false;
    }
    // check user name 
    public boolean checkUserName(){
    if (userName.contains("_") & userName.length()<=5){
        return true;
    } else{
        return false;
    }
    }
    public boolean checkPasswordComplexity(){
        String passwordRegex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$";
        
        if (password.matches(passwordRegex)){
            return true;
        }else{
            return false;
        }
    }
    public boolean checkCellPhoneNumber(){
        String cellPhoneRegex = "\\+27\\d{9}";
        
        if (cellPhoneNumber.matches(cellPhoneRegex)){
            return true;
        }else{
            return false;
        }
    }
    public String registerUser(String user_successfully_registerd){
        if (checkUserName()){
            System.out.println("Username successfully captured.");
        }else{
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (checkPasswordComplexity()){
            System.out.println("Password successfully captured.");
        }else{
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (checkCellPhoneNumber()) {
            System.out.println("Cell phone number successfully captured.");
        }else{
            return "Cell phone number is incorrectly formatted or does not contain international code; please correct the number and try again.";
        } 
        
            return "User successfully registered."; 
                
    }
    public boolean loginUser(String enteredUserName,String enteredpassword){
        
        if (enteredUserName.equals(userName)&& enteredpassword.equals(password)){
            loginStatus = true;
        }else {
        loginStatus = false;
    }
        return loginStatus;
    }
        public String returnLoginStatus() {
            if (loginStatus) {

            return "Login successful\nWelcome " + firstName + " " + lastName
                    + ", it is great to see you again.";

        } else {

            return "Login has failed\nUsername or password incorrect, please try again.";
    }
        }
}




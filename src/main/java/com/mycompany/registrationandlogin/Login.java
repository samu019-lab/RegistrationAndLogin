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

    public String username;
    public String password;
    public String cellPhoneNumber;
    public String firstName;
    public String lastName;

    public boolean loginStatus;

    // Constructor
    public Login(String username, String password, String cellPhoneNumber,
                 String firstName, String lastName) {

        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginStatus = false;
    }

    // Check username
    public boolean checkUserName() {

        if (username.contains("_") && username.length() <= 5) {
            return true;
        } else {
            return false;
        }
    }

    // Check password
    public boolean checkPasswordComplexity() {

        String passwordRegex =
                "^(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{8,}$";

        if (password.matches(passwordRegex)) {
            return true;
        } else {
            return false;
        }
    }

    // Check cellphone number
    public boolean checkCellPhoneNumber() {

        String cellPhoneRegex = "^\\+27\\d{9}$";

        if (cellPhoneNumber.matches(cellPhoneRegex)) {
            return true;
        } else {
            return false;
        }
    }

    // Register user
    public String registerUser(String user_successfully_registered) {

        if (!checkUserName()) {

            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";

        } else if (!checkPasswordComplexity()) {

            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";

        } else if (!checkCellPhoneNumber()) {

            return "Cell phone number is incorrectly formatted or does not contain international code; please correct the number and try again.";

        } else {

            return "User successfully registered.";
        }
    }

    // Login user
    public boolean loginUser(String enteredUsername, String enteredPassword) {

        if (enteredUsername.equals(username)
                && enteredPassword.equals(password)) {

            loginStatus = true;

        } else {

            loginStatus = false;
        }

        return loginStatus;
    }

    // Return login status
    public String returnLoginStatus() {

        if (loginStatus) {

            return "Welcome " + firstName + " " + lastName
                    + ", it is great to see you again.";

        } else {

            return "Username or password incorrect, please try again.";
        }
    }
}

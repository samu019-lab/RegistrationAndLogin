/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registrationandlogin;

import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author Samukelisiwe
 */
public class RegistrationAndLogin {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Registration");

        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        System.out.print("Enter your cellphone number: ");
        String cellPhoneNumber = scanner.nextLine();

        Login login = new Login(
                username,
                password,
                cellPhoneNumber,
                firstName,
                lastName
        );

        System.out.println(login.registerUser("User successfully registered."));

        System.out.println();
        System.out.println("Login");

        System.out.print("Enter your username: ");
        String enteredUsername = scanner.nextLine();

        System.out.print("Enter your password: ");
        String enteredPassword = scanner.nextLine();

        login.loginUser(enteredUsername, enteredPassword);

        System.out.println(login.returnLoginStatus());
// part 2 starting here
         if (!login.loginUser(enteredUsername, enteredPassword)) {
            System.out.println("Program ended.");
            scanner.close();
            return;
        }

        System.out.println("\nWelcome to QuickChat.");

        boolean running = true;
        int messageNumber = 0;

        while (running) {

            System.out.println("\n1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");

            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    System.out.print(
                            "How many messages would you like to send? "
                    );

                    int numberOfMessages;

                    try {
                        numberOfMessages = Integer.parseInt(
                                scanner.nextLine()
                        );
                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Please enter a valid number."
                        );
                        break;
                    }

                    if (numberOfMessages <= 0) {
                        System.out.println(
                                "Enter a number greater than zero."
                        );
                        break;
                    }

                     for (int i = 0; i < numberOfMessages; i++) {

                        System.out.println(
                                "\nMessage " + (i + 1)
                        );
                 
                        // Generate a random 10-digit message ID.
                        String messageID = String.valueOf(
                                1000000000L
                                + (long) (
                                        random.nextDouble()
                                        * 9000000000L
                                )
                        );

    String recipient;

    while (true) {
        System.out.print(
                "Enter recipient cellphone number (include +): "
        );
        recipient = scanner.nextLine();

        if (recipient.matches("\\+\\d{1,12}")) {
            break;
        }

        System.out.println(
                "Cell phone number is incorrectly formatted or does not "
                + "contain an international code. Please correct the "
                + "number and try again."
        );
    }
    String messageText;

    while (true) {
        System.out.print("Enter your message: ");
        messageText = scanner.nextLine();

        if (!messageText.trim().isEmpty()
                && messageText.length() <= 250) {
            break;
        }

        if (messageText.length() > 250) {
            System.out.println(
                    "Please enter a message of less than 250 characters."
            );
        } else {
            System.out.println("Message cannot be empty.");
        }
    }
    Message newMessage = new Message(
            messageID,
            messageNumber,
            recipient,
            messageText
    );

    if (!newMessage.checkMessageID()) {
        System.out.println("Invalid message ID.");
        continue;
    }
    
                     System.out.println(
                                "Message Hash: "
                                + newMessage.createMessageHash()
                        );

                        

      String messageChoice;

    while (true) {

    System.out.println("\nChoose an option:");
    System.out.println("1. Send Message");
    System.out.println("2. Disregard Message");
    System.out.println("3. Store Message");

    System.out.print("Enter your choice: ");
    messageChoice = scanner.nextLine();

    if (messageChoice.equals("1")
            || messageChoice.equals("2")
            || messageChoice.equals("3")) {
        break;
    }

            System.out.println("Invalid choice. Please try again.");
}

            newMessage.createMessageHash();

                        messageNumber++;
                    }

                    break;

                case "2":
                    System.out.println("Coming Soon.");
                    break;

                case "3":
                    System.out.println(
                            "Total messages sent: "
                            + Message.returnTotalMessages()
                    );

                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println(
                            "Invalid option. Please try again."
                    );
            }
        }

        scanner.close();
    }
    }


     
    
         

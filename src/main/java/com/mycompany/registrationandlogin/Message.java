/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandlogin;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
/**
 *
 * @author Samukelisiwe
 */
class Message {


    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    private static int totalMessages = 0;
    private static MessageNode sentHead = null;
    private static MessageNode sentTail = null;

    private static class MessageNode {
        private Message data;
        private MessageNode next;

        private MessageNode(Message data) {
            this.data = data;
            this.next = null;
        }
    }

    public Message(String messageID, int messageNumber,
                   String recipient, String message) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = "";
    }

    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public boolean checkMessageID() {
        return messageID != null
                && messageID.matches("\\d{10}");
    }

    public boolean checkRecipientCell() {
        if (recipient == null || !recipient.startsWith("+")) {
            return false;
        }

        String number = recipient.substring(1);

        return number.length() >= 1
                && number.length() <= 12
                && number.matches("\\d+");
    }

    public boolean checkMessage() {
        return message != null
                && !message.trim().isEmpty()
                && message.length() <= 250;
    }
    public String checkMessageLength() {
    if (message != null && !message.trim().isEmpty()
            && message.length() <= 250) {
        return "Message ready to send.";
    }

    if (message != null && message.length() > 250) {
        return "Message exceeds 250 characters by "
                + (message.length() - 250)
                + "; please reduce the size.";
    }

    return "Message cannot be empty.";
}

public String checkCellPhoneNumber() {
    if (checkRecipientCell()) {
        return "Cell phone number successfully captured.";
    }

    return "Cell phone number is incorrectly formatted or does not "
            + "contain an international code. Please correct the "
            + "number and try again.";
}
    public String createMessageHash() {
        String firstWord = "";
        String lastWord = "";
        String trimmedMessage = message.trim();

        if (!trimmedMessage.isEmpty()) {
            int firstSpace = trimmedMessage.indexOf(' ');
            int lastSpace = trimmedMessage.lastIndexOf(' ');

            if (firstSpace == -1) {
                firstWord = trimmedMessage;
                lastWord = trimmedMessage;
            } else {
                firstWord = trimmedMessage.substring(0, firstSpace);
                lastWord = trimmedMessage.substring(lastSpace + 1);
            }
        }

        messageHash = messageNumber + ":"
                + messageID.substring(0, 2) + ":"
                + firstWord.toUpperCase() + ":"
                + lastWord.toUpperCase();

        return messageHash;
    }

    public String SentMessage(Scanner scanner) {
        System.out.println("\nChoose an option:");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message");

        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                totalMessages++;
                addSentMessage(this);
                System.out.println("Message sent successfully.");
                return "Message sent";

            case "0":
                System.out.println("Message disregarded and deleted.");
                return "Message disregarded";

            case "3":
                if (storeMessage()) {
                    System.out.println(
                            "Message stored in messages.json."
                    );
                    return "Message successfully stored";
                }

                System.out.println("Message could not be stored.");
                return "Storage failed";

            default:
                System.out.println("Invalid choice.");
                return "Invalid choice";
        }
    }

    private static void addSentMessage(Message newMessage) {
        MessageNode newNode = new MessageNode(newMessage);

        if (sentHead == null) {
            sentHead = newNode;
            sentTail = newNode;
        } else {
            sentTail.next = newNode;
            sentTail = newNode;
        }
    }

    public boolean storeMessage() {
        Path filePath = Paths.get("messages.json");

        try {
            String existingContent = "[]";

            if (Files.exists(filePath)) {
                existingContent = Files.readString(filePath).trim();

                if (existingContent.isEmpty()) {
                    existingContent = "[]";
                }
            }

            if (!existingContent.startsWith("[")
                    || !existingContent.endsWith("]")) {
                System.out.println("Invalid JSON file format.");
                return false;
            }

            String newObject = "{\n"
                    + "  \"messageID\": \"" + escapeJSON(messageID) + "\",\n"
                    + "  \"messageNumber\": " + messageNumber + ",\n"
                    + "  \"recipient\": \"" + escapeJSON(recipient) + "\",\n"
                    + "  \"message\": \"" + escapeJSON(message) + "\",\n"
                    + "  \"messageHash\": \"" + escapeJSON(messageHash) + "\"\n"
                    + "}";

            String contentInside = existingContent.substring(
                    1, existingContent.length() - 1
            ).trim();

            String updatedContent;

            if (contentInside.isEmpty()) {
                updatedContent = "[\n" + newObject + "\n]";
            } else {
                updatedContent = "[\n"
                        + contentInside + ",\n"
                        + newObject + "\n]";
            }

            try (FileWriter writer =
                         new FileWriter(filePath.toFile())) {
                writer.write(updatedContent);
            }

            return true;

        } catch (IOException e) {
            System.out.println(
                    "Error saving message: " + e.getMessage()
            );
            return false;
        }
    }

    private String escapeJSON(String value) {
        return value.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    public static void printMessages() {
        MessageNode current = sentHead;

        if (current == null) {
            System.out.println("No sent messages available.");
            return;
        }
// a while loop
        while (current != null) {
            Message currentMessage = current.data;

            System.out.println("\nMessage ID: "
                    + currentMessage.getMessageID());
            System.out.println("Recipient: "
                    + currentMessage.getRecipient());
            System.out.println("Message: "
                    + currentMessage.getMessage());
            System.out.println("Message Hash: "
                    + currentMessage.getMessageHash());

            current = current.next;
        }
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }
}



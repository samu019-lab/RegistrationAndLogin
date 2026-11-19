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
    
// PART 3 
private static final int MAX_MESSAGES = 100;

private static Message[] sentMessages = new Message[MAX_MESSAGES];
private static Message[] disregardedMessages = new Message[MAX_MESSAGES];
private static Message[] storedMessages = new Message[MAX_MESSAGES];

private static String[] messageHashes = new String[MAX_MESSAGES];
private static String[] messageIDs = new String[MAX_MESSAGES];

private static int sentCount = 0;
private static int disregardedCount = 0;
private static int storedCount = 0;

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
       
        String trimmedMessage = message.trim();
        String cleanMessage = trimmedMessage.replaceAll("[^a-zA-Z0-9\\s]", "");
        String[] words = cleanMessage.trim().split("\\s+");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        String firstTwoDigits = messageID.substring(0, 2);

        messageHash = firstTwoDigits + ":"
                + messageNumber + ":"
                + firstWord.toUpperCase()
                + lastWord.toUpperCase();

        return messageHash;
    }

    public String SentMessage(String choice) {
        System.out.println("\nChoose an option:");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message");

        System.out.print("Enter your choice: ");
        

       switch (choice) {
    case "1" -> {
        totalMessages++;
        addSentMessage(this);
        
       // Add sent message to array
     if (sentCount < MAX_MESSAGES) {
    sentMessages[sentCount] = this;
    messageHashes[sentCount] = messageHash;
    messageIDs[sentCount] = messageID;
    sentCount++;
}
        
         
    System.out.println("\nMessage successfully sent.");
    System.out.println("Message ID: " + messageID);
    System.out.println("Message Hash: " + messageHash);
    System.out.println("Recipient: " + recipient);
    System.out.println("Message: " + message);

    return "Message successfully sent.";
            }

    case "2" -> {
        
    if (disregardedCount < MAX_MESSAGES) {
    disregardedMessages[disregardedCount] = this;
    disregardedCount++;
    }
        
        System.out.println("Press 0 to delete the message.");
        return "Press 0 to delete the message.";
            }

    case "3" -> {
        if (storeMessage()) {
            
    if (storedCount < MAX_MESSAGES) {
    storedMessages[storedCount] = this;
    storedCount++;
   }
            System.out.println("Message successfully stored.");
            return "Message successfully stored.";
        }

        System.out.println("Message could not be stored.");
        return "Storage failed";
            }

    default -> {
        System.out.println("Invalid choice.");
        return "Invalid choice";
            }
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
          // Generate the correct hash before storing
    createMessageHash();
    
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
    
    
 //  Display the longest sent message
public static void displayLongestMessage() {

    if (sentCount == 0) {
        System.out.println("No sent messages available.");
        return;
    }

    Message longestMessage = sentMessages[0];

    for (int i = 1; i < sentCount; i++) {
        if (sentMessages[i].getMessage().length()
                > longestMessage.getMessage().length()) {
            longestMessage = sentMessages[i];
        }
    }

    System.out.println("\nLongest Message Details:");
    System.out.println("Message ID: " + longestMessage.getMessageID());
    System.out.println("Recipient: " + longestMessage.getRecipient());
    System.out.println("Message: " + longestMessage.getMessage());
    System.out.println("Message Hash: " + longestMessage.getMessageHash());
}  

 // Search for a message using Message ID
public static void searchMessageID(String searchID) {

    boolean found = false;

    for (int i = 0; i < sentCount; i++) {

        if (messageIDs[i].equals(searchID)) {

            System.out.println("\nMessage Found!");
            System.out.println("Recipient: "
                    + sentMessages[i].getRecipient());
            System.out.println("Message: "
                    + sentMessages[i].getMessage());

            found = true;
            break;
        }
    }

    if (!found) {
        System.out.println("Message ID not found.");
    }
}
 //  Search for messages sent to a recipient
public static void searchRecipient(String searchRecipient) {

    boolean found = false;

    for (int i = 0; i < sentCount; i++) {

        if (sentMessages[i].getRecipient().equals(searchRecipient)) {

            System.out.println("\nMessage Found!");
            System.out.println("Recipient: "
                    + sentMessages[i].getRecipient());
            System.out.println("Message: "
                    + sentMessages[i].getMessage());

            found = true;
        }
    }

    if (!found) {
        System.out.println("No messages found for this recipient.");
    }
}
 // Delete a sent message using its hash
public static void deleteMessage(String searchHash) {

    for (int i = 0; i < sentCount; i++) {

        if (messageHashes[i].equals(searchHash)) {

            for (int j = i; j < sentCount - 1; j++) {
                sentMessages[j] = sentMessages[j + 1];
                messageHashes[j] = messageHashes[j + 1];
                messageIDs[j] = messageIDs[j + 1];
            }
            
            sentMessages[sentCount - 1] = null;
            messageHashes[sentCount - 1] = null;
            messageIDs[sentCount - 1] = null;

            sentCount--;
           removeFromSentList(searchHash);
            System.out.println("Message successfully deleted.");
            return;
        }
    }

    System.out.println("Message hash not found.");
}

 // Display report of sent messages
     public static void displayReport() {

    System.out.println("\n===== SENT MESSAGE REPORT =====");

    if (sentCount == 0) {
        System.out.println("No sent messages available.");
        return;
    }

    for (int i = 0; i < sentCount; i++) {

        System.out.println("\nMessage " + (i + 1));
        System.out.println("Message Hash: "
                + sentMessages[i].getMessageHash());
        System.out.println("Recipient: "
                + sentMessages[i].getRecipient());
        System.out.println("Message: "
                + sentMessages[i].getMessage());
        System.out.println("Message ID: "
                + sentMessages[i].getMessageID());
    }

    System.out.println("\nTotal messages in report: " + sentCount);
}
     
 //Read stored messages from JSON into an array
    // Read stored messages from JSON into an array without external libraries
    public static void readStoredMessages() {
        Path filePath = Paths.get("messages.json");

        if (!Files.exists(filePath)) {
            System.out.println("No stored messages file found.");
            return;
        }

        try {
            String jsonContent = Files.readString(filePath).trim();

            if (jsonContent.isEmpty() || jsonContent.equals("[]")) {
                System.out.println("No stored messages found.");
                return;
            }

            storedMessages = new Message[MAX_MESSAGES];
            storedCount = 0;

            // Remove outer brackets [ ] and split each JSON object
            String inner = jsonContent.substring(1, jsonContent.length() - 1).trim();
            String[] objects = inner.split("\\},\\s*\\{");

            for (String obj : objects) {
                if (storedCount >= MAX_MESSAGES) {
                    System.out.println("Storage array is full. Some messages were not loaded.");
                    break;
                }

                String cleanObj = obj.replace("{", "").replace("}", "").trim();
                String id = extractJsonValue(cleanObj, "messageID");
                String numStr = extractJsonValue(cleanObj, "messageNumber");
                String rec = extractJsonValue(cleanObj, "recipient");
                String msg = extractJsonValue(cleanObj, "message");
                String hash = extractJsonValue(cleanObj, "messageHash");

                int msgNum = numStr.isEmpty() ? 0 : Integer.parseInt(numStr);
                Message loaded = new Message(id, msgNum, rec, msg);
                loaded.messageHash = hash;

                storedMessages[storedCount] = loaded;
                storedCount++;
            }

            System.out.println(storedCount + " stored messages loaded from JSON.");

            for (int i = 0; i < storedCount; i++) {
                Message current = storedMessages[i];
                System.out.println("\nMessage ID: " + current.getMessageID());
                System.out.println("Recipient: " + current.getRecipient());
                System.out.println("Message: " + current.getMessage());
                System.out.println("Message Hash: " + current.getMessageHash());
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading stored messages: " + e.getMessage());
        }
    }

    private static String extractJsonValue(String jsonBlock, String key) {
        String searchKey = "\"" + key + "\":";
        int keyIndex = jsonBlock.indexOf(searchKey);
        if (keyIndex == -1) {
            return "";
        }

        int valueStart = keyIndex + searchKey.length();
        String rest = jsonBlock.substring(valueStart).trim();

        if (rest.startsWith("\"")) {
            int endQuote = rest.indexOf("\"", 1);
            while (endQuote > 0 && rest.charAt(endQuote - 1) == '\\') {
                endQuote = rest.indexOf("\"", endQuote + 1);
            }
            return endQuote > 0 ? rest.substring(1, endQuote) : "";
        } else {
            int commaIndex = rest.indexOf(",");
            return commaIndex > 0 ? rest.substring(0, commaIndex).trim() : rest.trim();
        }
    }
     
private static void removeFromSentList(String searchHash) {

    MessageNode current = sentHead;
    MessageNode previous = null;

    while (current != null) {

        if (current.data.getMessageHash().equals(searchHash)) {

            if (previous == null) {
                sentHead = current.next;
            } else {
                previous.next = current.next;
            }

            if (current == sentTail) {
                sentTail = previous;
            }

            return;
        }

        previous = current;
        current = current.next;
    }
}

}

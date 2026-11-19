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
public class MessageTest {
    
     @Test
    public void testMessageWithin250Characters() {
        Message message = new Message(
                "1234567890", 1,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals("Message ready to send.",
                message.checkMessageLength());
    }

    @Test
    public void testMessageExceeds250Characters() {
        String longMessage = "A".repeat(251);

        Message message = new Message(
                "1234567890", 1,
                "+27718693002", longMessage
        );

        assertEquals(
                "Message exceeds 250 characters by 1; please reduce the size.",
                message.checkMessageLength()
        );
    }

    @Test
    public void testValidRecipientNumber() {
        Message message = new Message(
                "1234567890", 1,
                "+27718693002", "Hello"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkCellPhoneNumber()
        );
    }

    @Test
    public void testInvalidRecipientNumber() {
        Message message = new Message(
                "1234567890", 2,
                "08575975889", "Hi Keegan, did you receive the payment?"
        );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not "
                + "contain an international code. Please correct the "
                + "number and try again.",
                message.checkCellPhoneNumber()
        );
    }

    @Test
    public void testValidMessageID() {
        Message message = new Message(
                "1234567890", 1,
                "+27718693002", "Hello"
        );

        assertTrue(message.checkMessageID());
    }

    @Test
    public void testInvalidMessageID() {
        Message message = new Message(
                "12345", 1,
                "+27718693002", "Hello"
        );

        assertFalse(message.checkMessageID());
    }

     @Test
    public void testMessageHash() {
        
        Message message = new Message(
                "0012345678", 0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "00:0:HITONIGHT",
                message.createMessageHash()
        );
    }

    @Test
    public void testSendMessage() {
        Message message = new Message(
                "1234567890", 1,
                "+27718693002", "Hello Mike"
        );

        assertEquals(
    "Message successfully sent.",
    message.SentMessage("1")
);
    }

        @Test
    public void testDisregardMessage() {
        Message message = new Message(
                "1234567891", 1,
                "+27718693002", "Hello Keegan"
        );

        assertEquals(
                "Press 0 to delete the message.",
                message.SentMessage("2")
                 );
    }
    
     @Test
    public void testStoreMessage() {
        Message message = new Message(
                "1234567892", 2,
                "+27718693002", "Hello again"
        );

        assertEquals(
                "Message successfully stored.",
                message.SentMessage("3")
        );
} 
    
    @Test
    public void testLongestMessage() {

        Message message1 = new Message(
                "1234567893", 3,
                "+27718693002", "Hello"
        );

        Message message2 = new Message(
                "1234567894", 4,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        message1.createMessageHash();
        message2.createMessageHash();

        message1.SentMessage("1");
        message2.SentMessage("1");

        assertDoesNotThrow(() -> Message.displayLongestMessage());
    }

    @Test
    public void testSearchMessageByID() {

        Message message = new Message(
                "1234567895", 5,
                "+27718693002", "Hello Mike"
        );

        message.createMessageHash();
        message.SentMessage("1");

        assertDoesNotThrow(() ->
                Message.searchMessageID("1234567895")
        );
    }
    @Test
    public void testSearchMessageByRecipient() {

        Message message = new Message(
                "1234567896", 6,
                "+27718693002", "Hello Keegan"
        );

        message.createMessageHash();
        message.SentMessage("1");

        assertDoesNotThrow(() ->
                Message.searchRecipient("+27718693002")
        );
    }

    @Test
    public void testDeleteMessageByHash() {

        Message message = new Message(
                "1234567897", 7,
                "+27718693002", "Hello again"
        );

        String hash = message.createMessageHash();
        message.SentMessage("1");

        assertDoesNotThrow(() ->
                Message.deleteMessage(hash)
        );
    }

    @Test
    public void testDisplayMessageReport() {

        Message message = new Message(
                "1234567898", 8,
                "+27718693002", "This is my report message"
        );

        message.createMessageHash();
        message.SentMessage("1");

        assertDoesNotThrow(() ->
                Message.displayReport()
        );
    }

    @Test
    public void testReadStoredMessages() {

        assertDoesNotThrow(() ->
                Message.readStoredMessages()
        );
    }

}


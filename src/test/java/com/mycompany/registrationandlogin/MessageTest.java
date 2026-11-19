/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.registrationandlogin;

import java.util.Scanner;
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
}


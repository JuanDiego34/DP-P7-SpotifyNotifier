package org.example.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Notification {
    private static List<String> messageHistory = new ArrayList<>();
    private static List<JFrame> notificationFrames = new ArrayList<>();
    private static final int MAX_MESSAGES = 4;

    public static void showNotification(String message) {
        // Add the new message to the history
        messageHistory.add(message);

        // Trim the message history if it exceeds the maximum allowed messages
        while (messageHistory.size() > MAX_MESSAGES) {
            messageHistory.remove(0);
        }

        // Create a JFrame for the notification
        JFrame notificationFrame = new JFrame("Notification");
        notificationFrame.setSize(300, 100);
        notificationFrame.setUndecorated(true);

        // Set the location to be more to the right of the screen
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        int xPosition = screenWidth / 2 - notificationFrame.getWidth() / 2 + 220; // Adjust the value as needed
        int yPosition = screenHeight / 2 - notificationFrame.getHeight() / 2 - 220;

        notificationFrame.setLocation(xPosition, yPosition);

        // Create a JTextArea to display the last 5 messages
        JTextArea messageArea = new JTextArea();
        Font font = new Font("SansSerif", Font.BOLD, 16); // Adjust the font size as needed
        messageArea.setFont(font);
        messageArea.setEditable(false);

        // Concatenate the last 5 messages
        for (int i = messageHistory.size() - 1; i >= 0; i--) {
            messageArea.append(messageHistory.get(i) + "\n");
        }

        // Add the messageArea to the notification frame
        notificationFrame.add(messageArea);

        // Set a timer to close the notification after a certain duration (e.g., 3 seconds)
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notificationFrame.dispose();
                notificationFrames.remove(notificationFrame);

            }
        });
        timer.setRepeats(false); // Set to false to run only once
        timer.start();

        // Set the background color and make it slightly transparent
        notificationFrame.getContentPane().setBackground(new Color(220, 220, 220, 200));

        // Show the notification
        notificationFrame.setVisible(true);

        // Add the frame to the list
        notificationFrames.add(notificationFrame);

    }
}

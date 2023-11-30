package org.example.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Notification {
    public static void showNotification(String message) {
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

        // Create a JLabel to display the message
        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        notificationFrame.add(messageLabel);

        // Set a timer to close the notification after a certain duration (e.g., 3 seconds)
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notificationFrame.dispose();
            }
        });
        timer.setRepeats(false); // Set to false to run only once
        timer.start();

        // Set the background color and make it slightly transparent
        notificationFrame.getContentPane().setBackground(new Color(220, 220, 220, 200));

        // Show the notification
        notificationFrame.setVisible(true);
    }
}

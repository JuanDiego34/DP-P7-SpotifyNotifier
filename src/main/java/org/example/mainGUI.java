package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public mainGUI() {
        // Window configuration
        setTitle("Spotify Notifier");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        JButton artistsButton = new JButton("Artists");
        JButton playlistsButton = new JButton("Playlists");
        buttonsPanel.add(artistsButton);
        buttonsPanel.add(playlistsButton);

        JButton returnButton1 = new JButton("Return");
        JButton returnButton2 = new JButton("Return");

        // Artists information panel
        JPanel artistsPanel = new JPanel();
        artistsPanel.setLayout(new GridLayout(4, 1));
        artistsPanel.add(new JButton("Artist 1"));
        artistsPanel.add(new JButton("Artist 2"));
        artistsPanel.add(new JButton("Artist 3"));
        artistsPanel.add(returnButton1);

        // Playlists information panel
        JPanel playlistsPanel = new JPanel();
        playlistsPanel.setLayout(new GridLayout(4, 1));
        playlistsPanel.add(new JButton("Playlist 1"));
        playlistsPanel.add(new JButton("Playlist 2"));
        playlistsPanel.add(new JButton("Playlist 3"));
        playlistsPanel.add(returnButton2);

        // Action listeners
        artistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Artists");
            }
        });

        playlistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Playlists");
            }
        });

        returnButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Buttons");
            }
        });

        returnButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Buttons");
            }
        });

        // Add panels to the main panel with  CardLayout
        cardPanel.add(buttonsPanel, "Buttons");
        cardPanel.add(artistsPanel, "Artists");
        cardPanel.add(playlistsPanel, "Playlists");

        // Add the main panel to the window
        add(cardPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new mainGUI();
            }
        });
    }
}
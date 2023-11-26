package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTextArea infoArtistsArea, infoPlaylistsArea;

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
        infoArtistsArea = new JTextArea(10, 30);
        artistsPanel.add(new JScrollPane(infoArtistsArea));
        artistsPanel.add(returnButton1);

        // Playlists information panel
        JPanel playlistsPanel = new JPanel();
        infoPlaylistsArea = new JTextArea(10, 30);
        playlistsPanel.add(new JScrollPane(infoPlaylistsArea));
        playlistsPanel.add(returnButton2);

        // Action listeners
        artistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Artists");
                showInfo("Artists goes here", infoArtistsArea);
            }
        });

        playlistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Playlists");
                showInfo("Playlists goes here", infoPlaylistsArea);
            }
        });

        returnButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "buttons");
            }
        });

        returnButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "buttons");
            }
        });

        // Add panels to the main panel with  CardLayout
        cardPanel.add(buttonsPanel, "buttons");
        cardPanel.add(artistsPanel, "Artists");
        cardPanel.add(playlistsPanel, "Playlists");

        // Add the main panel to the window
        add(cardPanel);
        setVisible(true);
    }

    private void showInfo(String information, JTextArea infoArea) {
        infoArea.setText(information);
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
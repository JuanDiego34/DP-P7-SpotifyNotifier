package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class mainGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private ArrayList<JButton> followButtons, addSongButtons, returnButtons;

    public mainGUI() {
        // Window configuration
        setTitle("Spotify Notifier");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        followButtons = new ArrayList<JButton>();
        addSongButtons = new ArrayList<JButton>();
        returnButtons = new ArrayList<JButton>();

        // Main panel
        JPanel mainPanel = new JPanel(new GridLayout());

        JButton artistsButton = new JButton("Artists");
        artistsButton.setBackground(new Color(25, 20, 20));
        artistsButton.setForeground(new Color(30, 215, 96));
        artistsButton.setFont(new Font("SansSerif", Font.BOLD, 50));

        JButton playlistsButton = new JButton("Playlists");
        playlistsButton.setBackground(new Color(25, 20, 20));
        playlistsButton.setForeground(new Color(30, 215, 96));
        playlistsButton.setFont(new Font("SansSerif", Font.BOLD, 50));

        mainPanel.add(artistsButton);
        mainPanel.add(playlistsButton);

        // Sub-panels
        String artistsNames[] = {"Artist 1", "Artist 2", "Artist 3"}; // Replace for Artist method getNames()
        JPanel artistsPanel = newSubPanel(artistsNames, "Follow");

        String playlistsNames[] = {"Playlist 1", "Playlist 2", "Playlist 3"}; // Replace for Playlist method getNames()
        JPanel playlistsPanel = newSubPanel(playlistsNames, "Add song");

        // Action listeners
        for (JButton b: followButtons) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Temporal behaviour (replace for observer pattern)
                    cardLayout.show(cardPanel, "Main");
                }
            });
        }

        for (JButton b: addSongButtons) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Temporal behaviour (replace for observer pattern)
                    cardLayout.show(cardPanel, "Main");
                }
            });
        }

        for (JButton b: returnButtons) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "Main");
                }
            });
        }

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

        // Add panels to the main panel with  CardLayout
        cardPanel.add(mainPanel, "Main");
        cardPanel.add(artistsPanel, "Artists");
        cardPanel.add(playlistsPanel, "Playlists");

        // Add the main panel to the window
        add(cardPanel);
        setVisible(true);
    }

    private JPanel newSubPanel(String[] names, String button) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (int i = 0; i < names.length; i++) {
            panel.add(newItemPanel(names[i], button));
        }

        JPanel returnPanel = new JPanel();
        JButton returnButton = new JButton("Return");
        returnPanel.setBackground(new Color(25, 20, 20));
        returnPanel.add(returnButton);
        panel.add(returnPanel);

        returnButtons.add(returnButton);

        return panel;
    }

    private JPanel newItemPanel(String text, String type) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 20, 20));

        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JButton button = new JButton(type);
        button.setBackground(new Color(30, 215, 96));
        button.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel.add(button);

        if (type == "Follow") {
            followButtons.add(button);
        } else {
            addSongButtons.add(button);
        }

        return panel;
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
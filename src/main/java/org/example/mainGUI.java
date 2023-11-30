package org.example;

import org.example.Observer.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import se.michaelthelin.spotify.model_objects.specification.Image;

public class mainGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private ArrayList<JButton> returnButtons;
    private HashMap<JButton, String> artistsMap, playlistsMap;
    private static ArrayList<SpotifySubject> subjsArtists, subjsPlaylists;
    private static SpotifyObserver observer;

    public mainGUI() {
        // Window configuration
        setTitle("Spotify Notifier");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        returnButtons = new ArrayList<JButton>();
        artistsMap = new HashMap<JButton, String>();
        playlistsMap = new HashMap<JButton, String>();

        // Main panel
        JPanel mainPanel = new JPanel(new GridLayout());

        JButton artistsButton = new JButton("Artists");
        artistsButton.setBackground(new Color(25, 20, 20));
        artistsButton.setForeground(new Color(30, 215, 96));
        artistsButton.setFont(new Font("SansSerif", Font.BOLD, 50));

        JButton playlistsButton = new JButton("My playlists");
        playlistsButton.setBackground(new Color(25, 20, 20));
        playlistsButton.setForeground(new Color(30, 215, 96));
        playlistsButton.setFont(new Font("SansSerif", Font.BOLD, 50));

        mainPanel.add(artistsButton);
        mainPanel.add(playlistsButton);

        // Sub-panels
        String artistsNames[] = new String[subjsArtists.size()];
        Image artistsImages[] = new Image[subjsArtists.size()];

        for (int i = 0; i < subjsArtists.size(); i++) {
            artistsNames[i] = subjsArtists.get(i).getName();
            artistsImages[i] = subjsArtists.get(i).getCover();
        }

        JPanel artistsPanel = newSubPanel("Artists", "Follow", artistsNames, artistsImages);

        String playlistsNames[] = new String[subjsPlaylists.size()];
        Image playlistsImages[] = new Image[subjsPlaylists.size()];

        for (int i = 0; i < subjsPlaylists.size(); i++) {
            playlistsNames[i] = subjsPlaylists.get(i).getName();
            playlistsImages[i] = subjsPlaylists.get(i).getCover();
        }

        JPanel playlistsPanel = newSubPanel("My playlists", "Add song", playlistsNames, playlistsImages);

        // Action listeners
        for (JButton b : artistsMap.keySet()) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!b.isSelected()) {
                        b.setText("Unfollow");
                        b.setBackground(new Color(215, 30, 96));

                        for (int i = 0; i < artistsMap.size(); i++) {
                            if (subjsArtists.get(i).getName() == artistsMap.get(b)) {
                                subjsArtists.get(i).attach(observer);
                            }
                        }
                    } else {
                        b.setText("Follow");
                        b.setBackground(new Color(30, 215, 96));

                        for (int i = 0; i < artistsMap.size(); i++) {
                            if (subjsArtists.get(i).getName() == artistsMap.get(b)) {
                                subjsArtists.get(i).detach(observer);
                            }
                        }
                    }

                    b.setSelected(!b.isSelected());
                }
            });
        }

        for (JButton b : playlistsMap.keySet()) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < playlistsMap.size(); i++) {
                        if (subjsPlaylists.get(i).getName() == playlistsMap.get(b)) {
                            subjsPlaylists.get(i).notifyObservers("A song has been added to " + playlistsMap.get(b) + "!");
                        }
                    }
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

    private JPanel newSubPanel(String title, String button, String[] names, Image[] images) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel titlePanel = new JPanel();
        JLabel label = new JLabel(title);
        label.setFont(new Font("SansSerif", Font.BOLD, 60));
        label.setForeground(Color.WHITE);
        titlePanel.add(label);
        titlePanel.setBackground(new Color(25, 20, 20));
        panel.add(titlePanel);

        for (int i = 0; i < names.length; i++) {
            panel.add(newItemPanel(names[i], images[i], button));
        }

        JPanel returnPanel = new JPanel();
        JButton returnButton = new JButton("Return");
        returnPanel.setBackground(new Color(25, 20, 20));
        returnPanel.add(returnButton);
        panel.add(returnPanel);

        returnButtons.add(returnButton);

        return panel;
    }

    private JPanel newItemPanel(String text, Image image, String type) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 20, 20));

        try {
            URL url = new URL(image.getUrl());
            ImageIcon imageIcon = new ImageIcon(url);
            java.awt.Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            ImageIcon newImageIcon = new ImageIcon(scaledImage);
            JLabel labelImage = new JLabel(newImageIcon);
            panel.add(labelImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setForeground(Color.WHITE);
        panel.add(label);

        JButton button = new JButton(type);
        button.setBackground(new Color(30, 215, 96));
        button.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel.add(button);

        if (type == "Follow") {
            artistsMap.put(button, text);
        } else {
            playlistsMap.put(button, text);
        }

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                observer = new User();
                subjsArtists = new ArrayList<SpotifySubject>();
                subjsPlaylists = new ArrayList<SpotifySubject>();

                SpotifySubject pl1 = new PlaylistSubjectImpl("37i9dQZF1DX0XUsuxWHRQd");
                SpotifySubject pl2 = new PlaylistSubjectImpl("37i9dQZF1DXaxEKcoCdWHD");
                SpotifySubject pl3 = new PlaylistSubjectImpl("37i9dQZF1DWTl4y3vgJOXW");
                pl1.attach(observer);
                pl2.attach(observer);
                pl3.attach(observer);

                SpotifySubject art1 = new ArtistSubjectImpl("3TVXtAsR1Inumwj472S9r4");
                SpotifySubject art2 = new ArtistSubjectImpl("52iwsT98xCoGgiGntTiR7K");
                SpotifySubject art3 = new ArtistSubjectImpl("1Xyo4u8uXC1ZmMpatF05PJ");

                subjsPlaylists.add(pl1);
                subjsPlaylists.add(pl2);
                subjsPlaylists.add(pl3);
                subjsArtists.add(art1);
                subjsArtists.add(art2);
                subjsArtists.add(art3);

                // Implement timers for every artist to "upload" a song every 5 seconds
                for (int i = 0; i < subjsArtists.size(); i++) {
                    int finalI = i;
                    Timer timer = new Timer(5000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            subjsPlaylists.get(finalI).notifyObservers("New song has been added to " + subjsArtists.get(finalI).getName() + "!");
                        }
                    });
                    timer.start();
                }
                new mainGUI();
            }
        });
    }
}
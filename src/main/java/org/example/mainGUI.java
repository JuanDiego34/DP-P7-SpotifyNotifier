package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class mainGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private ArrayList<JButton> returnButtons;
    HashMap<JButton, String> artistsMap, playlistsMap;

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
        String artistsNames[] = {"Artist 1", "Artist 2", "Artist 3"}; // Replace for Artist method getNames()
        JPanel artistsPanel = newSubPanel("Artists", artistsNames, "Follow");

        String playlistsNames[] = {"Playlist 1", "Playlist 2", "Playlist 3"}; // Replace for Playlist method getNames()
        JPanel playlistsPanel = newSubPanel("Playlists", playlistsNames, "Add song");

        // Action listeners
        for (JButton b : artistsMap.keySet()) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!b.isSelected()) {
                        b.setText("Unfollow");
                        b.setBackground(new Color(215, 30, 96));
                        System.out.println(artistsMap.get(b));

                        // Replace for observer pattern
                        /*
                        subjs[?].attach(obs);
                        */
                    } else {
                        b.setText("Follow");
                        b.setBackground(new Color(30, 215, 96));

                        // Replace for observer pattern
                        /*
                        subjs[?].dettach(obs);
                        */
                    }

                    b.setSelected(!b.isSelected());
                }
            });
        }

        for (JButton b : playlistsMap.keySet()) {
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Temporal behaviour (replace for observer pattern)
                    /*
                    subjs[?].update();
                     */
                    System.out.println(playlistsMap.get(b));
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

    private JPanel newSubPanel(String title, String[] names, String button) {
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
                /*
                Observer obs = new User();
                ArrayList<Subject> subjs;

                Subject pl1 = new Playlist();
                Subject pl2 = new Playlist();
                Subject pl3 = new Playlist();
                pl1.attach(obs);
                pl2.attach(obs);
                pl3.attach(obs);


                Subject art1 = new Artist();
                Subject art2 = new Artist();
                Subject art3 = new Artist();
                art1.update();
                art2.update();
                art3.update();

                subjs.add(pl1);
                subjs.add(pl2);
                subjs.add(pl3);
                subjs.add(art1);
                subjs.add(art2);
                subjs.add(art3);
                 */
                new mainGUI();
            }
        });
    }
}
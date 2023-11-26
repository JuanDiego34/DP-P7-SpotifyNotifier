package org.example.Observer;

/**
 * Interface for Spotify observers.
 * Declares the update method that gets called when SpotifySubject notifies changes.
 */
public interface SpotifyObserver {
    void update();
}
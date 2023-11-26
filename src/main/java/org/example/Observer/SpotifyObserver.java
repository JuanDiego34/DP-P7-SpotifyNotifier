package org.example.Observer;

/**
 * Interface for Spotify subjects.
 * This interface declares methods for attaching, detaching, notifying observers,
 * and checking for updates in Spotify data.
 */
public interface SpotifySubject {

    /**
     * Notify all attached observers of a change, when a checkForUpdates method, gets an Update
     */
    void notifyObservers();

    /**
     * Check for updates in Spotify data.
     * Involve checking for changes in artist/playlist followers, new releases, etc.
     * In this case the check for updates method, means checking if a verification gap has been marked
     * This will be implemented at Implementation File and GUI afterwards
     */
    void checkForUpdates();
}

package org.example.Observer;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief Abstract class representing a Spotify subject.
 * 
 * This class serves as an abstract base for subjects in the observer pattern,
 * specifically for Spotify-related functionality. It provides methods for 
 * attaching, detaching, and notifying observers, as well as managing 
 * interactions with the Spotify API.
 */
public abstract class SpotifySubject {
    
    protected final List<SpotifyObserver> observers; /**< List of observers to notify about changes. */
    protected final SpotifyApi spotifyApi; /**< Instance of SpotifyApi for interacting with the Spotify API. */

    /**
     * Constructor for SpotifySubject.
     * Initializes the list of observers and sets up the Spotify API connection.
     */
    public SpotifySubject() {
        observers = new ArrayList<>();
        SpotifyApiManager spotifyApiManager = new SpotifyApiManager();
        spotifyApiManager.authenticate();
        this.spotifyApi = spotifyApiManager.getSpotifyApi();
    }


    /**
     * @brief Notifies all attached observers.
     *
     * Invokes the update method on each of the observers, signaling a change in the artist and playlists.
     */
    public void notifyObservers(String message) {
        for (SpotifyObserver observer : observers) {
            observer.update(message);
        }
    }

    /**
     * @brief Attaches an observer to the subject.
     * 
     * @param o The SpotifyObserver to be attached.
     */
    public void attach(SpotifyObserver o) {
        observers.add(o);
    }

    /**
     * @brief Detaches an observer from the subject.
     * 
     * @param o The SpotifyObserver to be detached.
     */
    public void detach(SpotifyObserver o) {
        observers.remove(o);
    }

    /**
     * @brief Abstract method to get the name of the subject.
     * 
     * @return String representing the name of the subject.
     */
    public abstract String getName();

    /**
     * @brief Abstract method to get the cover image of the subject.
     * 
     * @return Image object representing the cover of the subject.
     */
    public abstract Image getCover();
}

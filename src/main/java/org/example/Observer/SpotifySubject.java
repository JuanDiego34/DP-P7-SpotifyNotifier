package org.example.Observer;

import se.michaelthelin.spotify.SpotifyApi;

/**
 * Interface for Spotify subjects.
 * Declares methods for attaching, detaching, and notifying observers,
 * as well as managing interactions with the Spotify API.
 */
public interface SpotifySubject {

    /**
     * Attaches an observer to the subject.
     * 
     * @param o The observer to be attached.
     */
    void attach(SpotifyObserver o);

    /**
     * Detaches an observer from the subject.
     * 
     * @param o The observer to be detached.
     */
    void detach(SpotifyObserver o);

    /**
     * Notifies all attached observers of a change.
     */
    void notifyObservers();

    /**
     * Handles authentication with the Spotify API.
     */
    void authenticate();

    /**
     * Provides access to the Spotify API instance.
     * 
     * @return SpotifyApi The instance of SpotifyApi.
     */
    SpotifyApi getSpotifyApi();
}

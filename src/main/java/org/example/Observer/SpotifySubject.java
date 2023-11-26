package org.example.Observer;

import org.example.Observer.SpotifyObserver;

/**
 * Interface for Spotify subjects.
 * This interface declares methods for attaching, detaching, and notifying observers.
 */
public interface SpotifySubject {
    void attach(SpotifyObserver o);
    void detach(SpotifyObserver o);
    void notifyObservers();
}
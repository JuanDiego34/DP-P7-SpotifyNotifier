package org.example.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of SpotifySubject.
 * This class interacts with the Spotify API and notifies observers of changes.
 */
public class SpotifySubjectImpl implements SpotifySubject {
    private List<SpotifyObserver> observers = new ArrayList<>();

    /**
     * Method to check for updates in Spotify.
     * It should contain logic to detect changes in Spotify and notify observers if any.
     */
    public void checkForUpdates() {
        // Logic to check for changes in Spotify
        // Notify observers if there are any changes
        notifyObservers();
    }

    @Override
    public void attach(SpotifyObserver o) {
        observers.add(o);
    }

    @Override
    public void detach(SpotifyObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (SpotifyObserver o : observers) {
            o.update();
        }
    }
}

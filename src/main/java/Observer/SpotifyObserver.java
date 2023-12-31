package Observer;

/**
 * Interface for Spotify observers.
 * Declares the update method that gets called when SpotifySubject notifies changes.
 */
public interface SpotifyObserver {
    boolean update(String message);
}

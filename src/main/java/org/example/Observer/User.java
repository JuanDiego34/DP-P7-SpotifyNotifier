package org.example.Observer;

/**
 * @class User
 * @brief Class representing a User in the Spotify observer system.
 *
 * This class implements the SpotifyObserver interface. It is used to represent 
 * a user who observes changes in SpotifySubject instances, such as playlists or tracks. 
 * When notified of a change, the update method will be called.
 */
public class User implements SpotifyObserver {

    /**
     * @brief Updates the User based on a notification.
     *
     * This method is called when the SpotifySubject that this User is observing 
     * reports a change. It can contain logic to handle the update accordingly.
     * 
     * @return boolean Returns true to indicate successful handling of the update.
     */
    @Override
    public boolean update() {
        // Here you can add logic for what should happen when the user is notified of a change.
        return true;
    }
}

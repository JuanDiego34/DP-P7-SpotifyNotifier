package org.example.Observer;

import se.michaelthelin.spotify.model_objects.specification.Image;

/**
 * @class ArtistSubjectTest
 * @brief Test class for ArtistSubjectImpl.
 *
 * This class demonstrates how to create an instance of ArtistSubjectImpl, retrieve artist information,
 * and notify observers about changes.
 */
public class ArtistSubjectTest {

    public static void main(String[] args) {
        // Replace "your_artist_id" with the actual Spotify artist ID you want to test
        String artistId = "3TVXtAsR1Inumwj472S9r4";

        // Create an instance of ArtistSubjectImpl
        ArtistSubjectImpl artistSubject = new ArtistSubjectImpl(artistId);

        // Display initial artist information
        displayArtistInfo(artistSubject);

        // Attach an observer (you can customize your observer implementation)
        artistSubject.attach(new YourCustomObserver());

        // Simulate a change in the artist (you can call this method when the artist information changes)
        // artistSubject.updateArtist();

        // Display updated artist information
        displayArtistInfo(artistSubject);
    }

    /**
     * @brief Display artist information.
     *
     * @param artistSubject The ArtistSubjectImpl instance.
     */
    private static void displayArtistInfo(ArtistSubjectImpl artistSubject) {
        System.out.println("Artist Name: " + artistSubject.getName());
        Image artistImage = artistSubject.getCover();
        System.out.println("Artist Image URL: " + (artistImage != null ? artistImage.getUrl() : "N/A"));
        System.out.println("-----");
    }

    /**
     * Example custom observer class. You can replace it with your custom observer implementation.
     */
    static class YourCustomObserver implements SpotifyObserver {
        @Override
        public boolean update(String message) {
            System.out.println("Observer notified: Artist information has changed.");
            return true;
        }
    }
}

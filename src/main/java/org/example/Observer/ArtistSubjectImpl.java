package org.example.Observer;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @class ArtistSubjectImpl
 * @brief Class specialized in managing Spotify artists.
 *
 * This class extends SpotifySubject and is responsible for interacting with the Spotify API
 * to manage and observe changes in specific artists. It maintains a map of artist details
 * and provides functionality to notify observers about changes in the artist.
 */
public class ArtistSubjectImpl extends SpotifySubject {

    private final Map<String, Artist> artistMap; /**< Map storing artist details by ID. */
    private Artist artist;

    /**
     * @brief Constructor for ArtistSubjectImpl.
     *
     * Initializes the SpotifyApiManager instance, authenticates, and assigns the SpotifyApi instance.
     * It also fetches the specified artist by its ID.
     *
     * @param idArtist The Spotify artist ID to manage and observe.
     */
    public ArtistSubjectImpl(String idArtist) {
        artistMap = new HashMap<>();
        try {
            GetArtistRequest getArtistRequest = spotifyApi.getArtist(idArtist).build();
            artist = getArtistRequest.execute();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * @brief Returns the name of the artist.
     *
     * @return String representing the name of the artist.
     */
    public String getName() {
        return artist.getName();
    }
    /**
     * @brief Returns the image of the artist.
     *
     * @return Image object representing the image of the artist.
     */
    @Override
    public Image getCover() {
        return artist.getImages()[0];
    }
}

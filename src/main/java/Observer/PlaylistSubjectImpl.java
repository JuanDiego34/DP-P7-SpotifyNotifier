package Observer;


import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.*;

/**
 * @class PlaylistSubjectImpl
 * @brief Class specialized in managing Spotify playlists.
 * 
 * This class extends SpotifySubject and is responsible for interacting with the Spotify API
 * to manage and observe changes in specific playlists. It maintains a map of playlist tracks
 * and provides functionality to notify observers about changes in the playlist.
 */
public class PlaylistSubjectImpl extends SpotifySubject {

    private final Map<String, List<PlaylistTrack>> playlistTrackMap; /**< Map storing tracks of playlists by ID. */
    private Playlist playlist;

    /**
     * @brief Constructor for PlaylistSubjectImpl.
     * 
     * Initializes the Observer.SpotifyApiManager instance, authenticates, and assigns the SpotifyApi instance.
     * It also fetches the specified playlist by its ID.
     * 
     * @param idPlaylist The Spotify playlist ID to manage and observe.
     */
    public PlaylistSubjectImpl(String idPlaylist) {
        playlistTrackMap = new HashMap<>();
        try {
            GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist(idPlaylist).build();
            playlist = getPlaylistRequest.execute();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * @brief Returns the name of the playlist.
     * 
     * @return String representing the name of the playlist.
     */
    public String getName() {
        return playlist.getName();
    }

    /**
     * @brief Returns the cover image of the playlist.
     * 
     * @return Image object representing the cover of the playlist.
     */
    public Image getCover() {
        return playlist.getImages()[0];
    }
}

package org.example;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;

import java.io.IOException;

public class SpotifyAPIcalls {
    // clientID and clientSecret are retrieved from the app created on Spotify Dashboard
    private static final String clientId = "4dc165c050c6424b9270741eeb871dd0";
    private static final String clientSecret = "93cc4bc42e344d209fb2c30d4d570413";
    private static final String idArtist = "3TVXtAsR1Inumwj472S9r4";
    private static final String idPlaylist = "37i9dQZF1DX0XUsuxWHRQd";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();

    // Set access token for further "spotifyApi" object usage
    public static void setToken() {
        try {
            ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getArtist() {
        try {
            GetArtistRequest getArtistRequest = spotifyApi.getArtist(idArtist).build();
            final Artist artist = getArtistRequest.execute();

            System.out.println("Artist: " + artist.getName());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void getPlaylist() {
        try {
            GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist(idPlaylist).build();
            final Playlist playlist = getPlaylistRequest.execute();

            System.out.println("Playlist: " + playlist.getName());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        setToken();
        getArtist();
        getPlaylist();
    }
}
package org.example.Observer;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

/**
 * Manages the authentication and configuration of the Spotify API.
 * Implements SpotifySubject to integrate Spotify API management.
 * This class handles setting up and authenticating with the Spotify API using client credentials.
 *
 * @implNote Implements SpotifySubject for observer pattern integration.
 */
public class SpotifyApiManager {

    /**
     * Client ID for Spotify API, obtained from Spotify Developer Dashboard.
     */
    private static final String clientId = "4dc165c050c6424b9270741eeb871dd0";

    /**
     * Client Secret for Spotify API, obtained from Spotify Developer Dashboard.
     */
    private static final String clientSecret = "93cc4bc42e344d209fb2c30d4d570413";

    /**
     * Instance of SpotifyApi, used for all interactions with the Spotify Web API.
     */
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .build();

    /**
     * Authenticates the application to the Spotify Web API using the Client Credentials flow.
     * Sets the access token on the SpotifyApi instance upon successful authentication.
     */

    public void authenticate() {
        try {
            ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error during Spotify authentication: " + e.getMessage());
        }
    }

    /**
     * Retrieves the authenticated SpotifyApi instance.
     * 
     * @return SpotifyApi The authenticated SpotifyApi instance.
     */

    public SpotifyApi getSpotifyApi() {
        return spotifyApi;
    }
}

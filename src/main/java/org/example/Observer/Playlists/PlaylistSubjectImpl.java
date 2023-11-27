package org.example.Observer.Playlists;

import org.example.Observer.SpotifyObserver;
import org.example.Observer.SpotifySubjectImpl;
import org.example.Observer.SpotifyApiManager;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;

import java.io.IOException;
import java.util.*;

/**
 * @class PlaylistSubjectImpl
 * @brief Clase especializada en manejar listas de reproducción de Spotify.
 *
 * Esta clase extiende SpotifySubjectImpl y se encarga de interactuar con la API de Spotify
 * para gestionar y observar cambios en las listas de reproducción específicas.
 */
public class PlaylistSubjectImpl extends SpotifySubjectImpl {

    private final SpotifyApi spotifyApi; /**< Instancia de SpotifyApi para interactuar con la API de Spotify. */
    private final Map<String, List<PlaylistTrack>> playlistTrackMap; /**< Mapa que almacena las pistas de las playlists por ID. */
    private final List<SpotifyObserver> observers; /**< Lista de observadores para notificar cambios. */

    /**
     * @brief Constructor de PlaylistSubjectImpl.
     *
     * Inicializa la instancia de SpotifyApiManager, autentica y asigna la instancia de SpotifyApi.
     */
    public PlaylistSubjectImpl() {
        SpotifyApiManager spotifyApiManager = new SpotifyApiManager();
        spotifyApiManager.authenticate();
        this.spotifyApi = spotifyApiManager.getSpotifyApi();
        playlistTrackMap = new HashMap<>();
        observers = new ArrayList<>();
    }

    /**
     * @brief Verifica las actualizaciones en las playlists.
     *
     * Itera sobre los IDs de las playlists predefinidas, verifica si hay cambios en ellas,
     * y notifica a los observadores en caso de cambios.
     */
    public void checkForPlaylistUpdates() {
        String playlistId1 = "37i9dQZF1DXcBWIGoYBM5M"; // Today's Top Hits
        String playlistId2 = "37i9dQZEVXbMDoHDwVN2tF"; // Top 50 - Global
        String playlistId3 = "37i9dQZF1DX0XUsuxWHRQd"; // RapCaviar
        String playlistId4 = "37i9dQZF1DX10zKzsJ2jva"; // Viva Latino
        String playlistId5 = "37i9dQZF1DWXRqgorJj26U"; // Rock Classics

        String[] playlistIds = {playlistId1, playlistId2, playlistId3, playlistId4, playlistId5};

        for (String playlistId : playlistIds) {
            try {
                GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist(playlistId).build();
                List<PlaylistTrack> currentTracks = getPlaylistRequest.execute().getItems();

                if (!playlistTrackMap.containsKey(playlistId) || playlistTrackMap.get(playlistId).size() != currentTracks.size()) {
                    playlistTrackMap.put(playlistId, currentTracks);
                    notifyObservers();
                }
            } catch (IOException | SpotifyWebApiException e) {
                System.out.println("Error al actualizar la playlist: " + e.getMessage());
            }
        }
    }

    /**
     * @brief Adjunta un observador a la lista de observadores.
     *
     * @param o Objeto SpotifyObserver que será adjuntado.
     */
    @Override
    public void attach(SpotifyObserver o) {
        observers.add(o);
    }

    /**
     * @brief Desvincula un observador de la lista de observadores.
     *
     * @param o Objeto SpotifyObserver que será desvinculado.
     */
    @Override
    public void detach(SpotifyObserver o) {
        observers.remove(o);
    }

    /**
     * @brief Notifica a todos los observadores adjuntos.
     *
     * Invoca el método update en cada uno de los observadores.
     */
    @Override
    public void notifyObservers() {
        for (SpotifyObserver observer : observers) {
            observer.update();
        }
    }
    /*
    private Map<String, List<PlaylistTrack>> playlistTrackMap;

    public void checkForPlaylistUpdates() {
        Scanner scanner = new Scanner(System.in);

        // Menú interactivo para añadir una canción ficticia
        System.out.println("Introduce el nombre del artista:");
        String artist = scanner.nextLine();
        System.out.println("Introduce el nombre de la canción:");
        String song = scanner.nextLine();
        System.out.println("Elige el ID de la playlist a la que añadirás la canción:");
        System.out.println("1: Today's Top Hits, 2: Top 50 - Global, 3: RapCaviar, 4: Viva Latino, 5: Rock Classics");
        int choice = Integer.parseInt(scanner.nextLine());

        String playlistId;
        switch (choice) {
            case 1: playlistId = "37i9dQZF1DXcBWIGoYBM5M"; break;
            case 2: playlistId = "37i9dQZEVXbMDoHDwVN2tF"; break;
            case 3: playlistId = "37i9dQZF1DX0XUsuxWHRQd"; break;
            case 4: playlistId = "37i9dQZF1DX10zKzsJ2jva"; break;
            case 5: playlistId = "37i9dQZF1DWXRqgorJj26U"; break;
            default: System.out.println("Selección inválida"); return;
        }

        PlaylistTrack fakeTrack = createFakePlaylistTrack(artist, song);
        List<PlaylistTrack> currentTracks = playlistTrackMap.getOrDefault(playlistId, new ArrayList<>());
        currentTracks.add(fakeTrack);
        playlistTrackMap.put(playlistId, currentTracks);

        notifyObservers();

        System.out.println("La canción " + song + ", de " + artist + " ha sido añadida a la playlist " + playlistId);
    }

    private PlaylistTrack createFakePlaylistTrack(String artist, String song) {
        // Genera un ID único para cada PlaylistTrack
        String uniqueId = UUID.randomUUID().toString();

        return new PlaylistTrack(uniqueId, song, artist);
    }


 */
}







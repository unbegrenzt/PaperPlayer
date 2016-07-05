package net.devdome.paperplayer.playback;

import net.devdome.paperplayer.data.model.Song;

import java.util.ArrayList;

public class PlaylistManager {

    private static PlaylistManager playlistManager = null;
    private ArrayList<PlaylistItem> playlist = new ArrayList<>();
    private int startIndex = 0;

    private PlaylistManager() {
    }

    public static PlaylistManager getInstance() {
        if (playlistManager == null)
            playlistManager = new PlaylistManager();

        return playlistManager;
    }

    public int getStartIndex() {
        return startIndex;
    }

    /**
     * Generate new playlist and set the playlist item playback should begin with
     *
     * @param songs {@link ArrayList<PlaylistItem>} of {@link Song}
     * @return {@link ArrayList<PlaylistItem>}
     */
    public ArrayList<PlaylistItem> generatePlaylist(ArrayList<Song> songs) {
        return generatePlaylist(songs, 0);
    }

    /**
     * Generate new playlist and set the playlist item playback should begin with
     *
     * @param songs              {@link ArrayList<PlaylistItem>} of {@link Song}
     * @param playbackInitSongId song id of track that should be played first
     * @return {@link ArrayList<PlaylistItem>}
     */
    public ArrayList<PlaylistItem> generatePlaylist(ArrayList<Song> songs, long playbackInitSongId) {
        if (playbackInitSongId == 0) {
            playbackInitSongId = songs.get(0).getId();
        }
        clearPlaylist();
        for (Song song : songs) {
            PlaylistItem item = new PlaylistItem(song, song.getId() == playbackInitSongId);
            playlist.add(item);
            if (song.getId() == playbackInitSongId) {
                startIndex = playlist.lastIndexOf(item);
            }
        }
        return playlist;
    }

    /**
     * Removes all items from playlist
     */
    protected void clearPlaylist() {
        playlist.clear();
    }

    /**
     * Get the playlist currently loaded
     *
     * @return {@link ArrayList<PlaylistItem>} playlist
     */
    public ArrayList<PlaylistItem> getCurrentPlaylist() {
        return this.playlist;
    }

    /**
     * A song in a playlist to improve SoC
     */
    public class PlaylistItem {
        private Song song;
        private boolean isPlaying = false;

        public PlaylistItem() {
        }

        public PlaylistItem(Song song) {
            this.song = song;
        }

        PlaylistItem(Song song, boolean isPlaying) {
            this.song = song;
            this.isPlaying = isPlaying;
        }

        public Song getSong() {
            return song;
        }

        public void setSong(Song song) {
            this.song = song;
        }

        public boolean isPlaying() {
            return isPlaying;
        }

        public void setPlaying(boolean playing) {
            isPlaying = playing;
        }
    }
}
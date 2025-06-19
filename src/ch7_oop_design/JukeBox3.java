/*
 7.3 Jukebox: Design a musical jukebox using object-oriented principles.
 */
package ch7_oop_design;
import java.util.*;

public class JukeBox3 {

    public static class Song {
        private String id;
        private String title;
        private String artist;
        private int durationInSeconds;

        public Song(String id, String title, String artist, int duration) {
            this.id = id;
            this.title = title;
            this.artist = artist;
            this.durationInSeconds = duration;
        }

        public String getTitle() { return title; }
        public String getArtist() { return artist; }

        @Override
        public String toString() {
            return "\"" + title + "\" by " + artist;
        }
    }

    public static class CD {
        private String name;
        private List<Song> songs;

        public CD(String name, List<Song> songs) {
            this.name = name;
            this.songs = songs;
        }

        public List<Song> getSongs() {
            return songs;
        }
    }

    public static class CDPlayer {
        private CD cd;

        public CDPlayer(CD cd) {
            this.cd = cd;
        }

        public void play(Song song) {
            System.out.println("Playing: " + song);
        }

        public List<Song> getAvailableSongs() {
            return cd.getSongs();
        }
    }

    public static class PlayList {
        private Queue<Song> songQueue = new LinkedList<>();

        public void addSong(Song song) {
            songQueue.offer(song);
        }

        public Song nextSong() {
            return songQueue.poll();
        }

        public boolean isEmpty() {
            return songQueue.isEmpty();
        }
    }

    public static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public void addSongToPlayList(Song song, JukeBox jukebox) {
            jukebox.getPlayList().addSong(song);
            System.out.println(name + " added: " + song);
        }
    }

    public static class JukeBox {
        private CDPlayer cdPlayer;
        private PlayList playList;

        public JukeBox(CD cd) {
            this.cdPlayer = new CDPlayer(cd);
            this.playList = new PlayList();
        }

        public void playNextSong() {
            if (!playList.isEmpty()) {
                Song song = playList.nextSong();
                cdPlayer.play(song);
            } else {
                System.out.println("Playlist is empty.");
            }
        }

        public List<Song> showAvailableSongs() {
            return cdPlayer.getAvailableSongs();
        }

        public PlayList getPlayList() {
            return playList;
        }
    }
}

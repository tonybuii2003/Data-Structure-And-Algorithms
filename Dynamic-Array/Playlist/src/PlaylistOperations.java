
// Name: Phi Long Bui, ID: 114555975, R-30
import java.util.Scanner;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This class Java application tests the methods of the Playlist class and
 * allows the user to manipulate a single Playlist object by performing
 * operations on it.
 */
public class PlaylistOperations {

    /**
     * The main method runs a menu driven application which first creates an empty
     * Playlist and then prompts the user for a menu command selecting the
     * operation.
     * The required information is then requested from the user based on the
     * selected operation.
     * 
     * @param args
     *             The command line arguments.
     */
    public static void main(String[] args) {
        int count = 0;
        Playlist[] lists = new Playlist[1001];
        Playlist[] playlists = lists;
        playlists[0] = new Playlist();
        Playlist current = playlists[0];
        Scanner input = new Scanner(System.in);
        boolean isEnded = false;
        System.out.println("A) Add Song\n" +
                "B) Print Songs by Artist\n" +
                "C) Copy the current playlist's songs into a new playlist.\n" +
                "D) Display all playlist names\n" +
                "E) Compare the songs in the current playlist with the given playlist.\n" +
                "G) Get Song\n" +
                "N) Create a new playlist and set as current playlist\n" +
                "R) Remove Song\n" +
                "P) Print All Songs\n" +
                "S) Size\n" +
                "V) Change current playlist.\n" +
                "O) Play a song file\n" +
                "Q) Quit");

        while (!isEnded) {
            try {
                System.out.print("\nSelect a menu option: ");
                String inp = input.nextLine().toLowerCase();
                System.out.println();
                switch (inp) {
                    case "a": {
                        try {
                            System.out.print("Enter the song title: ");
                            String title = input.nextLine();
                            System.out.print("Enter the song artist: ");
                            String artist = input.nextLine();
                            System.out.print("Enter the song length (minutes): ");
                            int minutes = input.nextInt();
                            input.nextLine();
                            System.out.print("Enter the song length (seconds): ");
                            int seconds = input.nextInt();
                            input.nextLine();
                            if (seconds > 60 || seconds < 0)
                                throw new Exception("Invalid song length.");

                            System.out.print("Enter the position: ");
                            int position = input.nextInt();
                            input.nextLine();
                            SongRecord song = new SongRecord(title, artist, minutes, seconds);

                            current.addSong(song, position);
                            System.out.printf("Song Added: %s By %s\n", song.getTitle(), song.getArtist());
                        } catch (FullPlaylistException e) {
                            System.out.println(e.getMessage());
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                    }
                        break;
                    case "r": {
                        System.out.print("Enter the position: ");
                        int position = input.nextInt();
                        input.nextLine();
                        try {
                            current.removeSong(position);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                        break;
                    case "s": {
                        System.out.printf("There are %s song(s) in the current playlist.\n", current.size());
                    }
                        break;
                    case "g": {
                        System.out.print("Enter the position: ");
                        int position = input.nextInt();
                        input.nextLine();
                        SongRecord song = current.getSong(position);
                        System.out.printf("%-10s%-16s%-16s%s\n", "Song#", "Title", "Artist", "Length");
                        System.out.println("------------------------------------------------");
                        System.out.printf("%-10s%s\n", position, song.toString());
                    }
                        break;
                    case "p": {
                        current.printAllSongs();
                    }
                        break;
                    case "o": {
                        System.out.print("Enter file name: ");
                        String file = input.nextLine();
                        boolean songEnded = false;
                        try {
                            Clip clip;
                            AudioInputStream audioInputStream;
                            audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
                            clip = AudioSystem.getClip();
                            clip.open(audioInputStream);
                            clip.loop(Clip.LOOP_CONTINUOUSLY);
                            System.out.print("Press X to stop: ");
                            String in = input.nextLine().toLowerCase();
                            while (!songEnded) {
                                switch (in) {
                                    case "x": {
                                        clip.stop();
                                        clip.close();
                                        songEnded = true;
                                    }
                                        break;
                                    default: {
                                        System.out.println("Invalid Please try again");
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.print(e.getMessage());

                        }
                    }
                        break;
                    case "b": {
                        System.out.print("Enter the artist: ");
                        String artist = input.nextLine();
                        Playlist tmp = Playlist.getSongsByArtist(current, artist);
                        System.out.printf("%-10s%-16s%-16s%s\n", "Song#", "Title", "Artist", "Length");
                        System.out.println("------------------------------------------------");
                        for (int i = 0; i < tmp.size(); i++) {
                            System.out.printf("%-10s%s\n", i + 1,
                                    tmp.getSong(i + 1).toString());
                        }
                    }
                        break;
                    case "n": {
                        System.out.print("Enter the playlist name: ");
                        String playlistName = input.nextLine();
                        if (count == playlists.length - 1) {
                            Playlist[] newList = new Playlist[playlists.length * 2];
                            for (int i = 0; i < count + 1; i++) {
                                newList[i] = playlists[i];
                            }
                            playlists = newList;
                        }
                        Playlist newPlaylist = new Playlist();
                        newPlaylist.setName(playlistName);
                        count += 1;
                        playlists[count] = newPlaylist;
                        current = playlists[count];
                        System.out.printf("%s playlist have been added and sets at current playlist\n", playlistName);
                    }
                        break;
                    case "v": {
                        boolean found = false;
                        System.out.print("Enter the playlist name: ");
                        String playlistName = input.nextLine();
                        for (int i = 0; i <= count; i++) {
                            if (playlistName.equals(playlists[i].getName())) {
                                current = playlists[i];
                                found = true;
                                System.out.printf("%s is the current playlist\n", playlists[i].getName());
                            }
                        }
                        if (!found) {
                            System.out.println("Playlist not found");
                        }
                    }
                        break;
                    case "c": {
                        System.out.print("Enter the playlist name: ");
                        String playlistName = input.nextLine();
                        if (count == playlists.length - 1) {
                            Playlist[] newList = new Playlist[playlists.length * 2];
                            for (int i = 0; i < count + 1; i++) {
                                newList[i] = playlists[i];
                            }
                            playlists = newList;
                        }
                        Playlist newPlaylist = (Playlist) current.clone();
                        newPlaylist.setName(playlistName);
                        count += 1;
                        playlists[count] = newPlaylist;
                        System.out.printf("%s is create as an copy of %s\n", newPlaylist.getName(), current.getName());

                    }
                        break;
                    case "e": {
                        boolean found = false;
                        System.out.print("Enter the playlist name: ");
                        String playlistName = input.nextLine();
                        for (int i = 0; i <= count; i++) {
                            if (playlistName.equals(playlists[i].getName())) {
                                found = true;
                                if (current.equals(playlists[i])) {
                                    System.out.printf("Playlist %s (current playlist) is equals to %s\n",
                                            current.getName(), playlists[i].getName());
                                } else {
                                    System.out.printf("Playlist %s (current playlist) is not equals to %s\n",
                                            current.getName(), playlists[i].getName());
                                }
                            }
                        }
                        if (!found) {
                            System.out.println("Playlist not found");
                        }

                    }
                        break;

                    case "d": {
                        System.out.printf("All playlists: \n");
                        System.out.println("--------------");
                        for (int i = 0; i <= count; i++) {
                            System.out.println(playlists[i].getName());
                        }
                    }
                        break;
                    case "q": {
                        System.out.println("Program terminating normally...");
                        isEnded = true;

                    }
                        break;
                    default: {
                        System.out.println("Invalid Please try again");
                    }
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid Please try again");
            }
        }
        input.close();
    }
}

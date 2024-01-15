// Name: Phi Long Bui, ID: 114555975, R-30
/**
 * This class stores all SongRecord objects that belong to a particular
 * playlist. The SongRecord objects should be stored in an array.
 * There should be a maximum of 50 SongRecord objects allowed, a number which
 * should be defined as a final variable
 * 
 * @author Phi Long Bui
 */
public class Playlist implements Cloneable {
    private int CAPACITY = 50;
    private int songsCount = 0;
    private String name = "Default";
    private SongRecord[] songs = new SongRecord[CAPACITY];

    /**
     * This Contructor creates a Playlist
     */
    public Playlist() {
    }

    /**
     * This method gets the compacity of the playlist
     * 
     * @return
     *         The compacity of the playlist
     */
    public int getCapacity() {
        return CAPACITY;
    }

    /**
     * This method gets the list of songs
     * 
     * @return
     *         The list of songs
     */
    public SongRecord[] getSongs() {
        return songs;
    }

    /**
     * This method gets the name of the playlist
     * 
     * @return
     *         The name of the playlist
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name of this playlist to the given name
     * 
     * @param name
     *             The given name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method clone the object and return the clone.
     * 
     * @return
     *         A copy of this Playlist. Subsequent changes to the copy will not
     *         affect the original, nor vice versa.
     */
    public Object clone() {
        Playlist clone = new Playlist();
        for (int i = 0; i < songsCount; i++) {
            try {
                clone.addSong((SongRecord) songs[i].clone(), i + 1);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (FullPlaylistException e) {
                System.out.println(e.getMessage());
            }
        }
        return clone;
    }

    /**
     * This method compares two object's properties.
     * 
     * @return
     *         A value of true indicates that obj refers to a Playlist object with
     *         the same SongRecords in the same order as this Playlist. Otherwise,
     *         the return value is false.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Playlist) {
            if (!(songsCount == ((Playlist) obj).size()))
                return false;
            for (int i = 0; i < songsCount; i++) {
                if (!(songs[i].equals(((Playlist) obj).songs[i])))
                    return false;
            }
        }
        return true;
    }

    /**
     * This method return the size of the song records
     * 
     * @return
     *         The number of SongRecords in this Playlist.
     */
    public int size() {
        return songsCount;
    }

    /**
     * 
     * @param song
     *                 The new SongRecord object to add to this Playlist
     * @param position
     *                 The position in the playlist where the song will be inserted
     * @throws FullPlaylistException
     *                                  if there is no more room inside of the
     *                                  Playlist to store the new SongRecord object.
     * @throws IllegalArgumentException
     *                                  if that position is not within the valid
     *                                  range.
     */
    public void addSong(SongRecord song, int position) throws FullPlaylistException {
        if (songsCount == CAPACITY) {
            throw new FullPlaylistException();
        }
        if (position >= 1 && position <= songsCount + 1) {
            position -= 1;
            if (songs[position] == null) {
                songs[position] = song;

                songsCount += 1;
            } else {
                SongRecord tmp = songs[songs.length - 1];
                for (int i = songs.length - 1; i > position; i--) {
                    songs[i] = songs[i - 1];
                }
                songs[position] = tmp;
                songs[position] = song;
                songsCount += 1;
            }
        } else {
            throw new IllegalArgumentException("Invalid position for adding the new song.");
        }

    }

    /**
     * This method removes the position in the playlist where the song will be
     * removed from.
     * 
     * @param position
     *                 The position of the PlayList
     * @throws Exception
     *                                  if there is no song to remove
     * @throws IllegalArgumentException
     *                                  if position is not within the valid range.
     */
    public void removeSong(int position) throws IllegalArgumentException {
        if (position > songsCount) {
            throw new IllegalArgumentException(String.format("No song at position %d to remove.", position));
        }
        if (position >= 1 && position <= songsCount + 1) {
            position -= 1;
            songs[position] = null;
            SongRecord tmp = songs[position];
            for (int i = position + 1; i < songs.length - 1; i++) {
                songs[i - 1] = songs[i];
            }
            songs[songs.length - 1] = tmp;
            songsCount -= 1;
            System.out.printf("Song Removed at position %s\n", position + 1);
        } else {
            throw new IllegalArgumentException("Position is not within the valid range.");
        }
    }

    /**
     * This method gets the SongRecord at the given position in this Playlist
     * object.
     * 
     * @param position
     *                 position of the SongRecord to retrieve
     * @return
     *         The SongRecord at the specified position in this Playlist object.on
     *         if position is not within the valid range.
     */
    public SongRecord getSong(int position) throws IllegalArgumentException {
        SongRecord res;
        if (position >= 1 && position <= songsCount + 1) {
            position -= 1;
            res = songs[position];
        } else {
            throw new IllegalArgumentException("Position is not within the valid range.");
        }
        return res;
    }

    /**
     * This method prints a neatly formatted table of each SongRecord in the
     * Playlist on its own line with its position number as shown in the sample
     * output.
     */
    public void printAllSongs() {
        System.out.printf("Playlist: %s\n", name);
        System.out.printf("%-10s%-16s%-16s%s\n", "Song#", "Title", "Artist", "Length");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < songs.length; i++) {
            if (songs[i] != null) {
                System.out.printf("%-10s%s\n", i + 1, songs[i].toString());

            } else
                break;
        }
    }

    /**
     * This method generates a new Playlist containing all SongRecords in the
     * original Playlist performed by the specified artist.
     * 
     * @param originalList
     *                     the original Playlist
     * @param artist
     *                     the name of the artist
     * @return
     */
    public static Playlist getSongsByArtist(Playlist originalList, String artist) {
        Playlist playlist = new Playlist();
        int count = 1;
        if (originalList == null || artist == null)
            return null;
        for (int i = 0; i < originalList.size(); i++) {
            if (originalList.getSong(i + 1) != null) {
                if (artist.equals(originalList.getSong(i + 1).getArtist())) {
                    try {
                        playlist.addSong(originalList.getSong(i + 1), count);
                        count += 1;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (FullPlaylistException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else
                break;
        }
        return playlist;
    }

    /**
     * This method Gets the String representation of this Playlist object,
     * which is a neatly formatted table of each SongRecord in the Playlist on its
     * own line with its position number as shown in the sample output.
     * 
     * @return
     *         The String representation of this Playlist object.
     */
    public String toString() {
        String str = "";
        str += String.format("%-10s%-16s%-16s%s\n", "Song#", "Title", "Artist", "Length");
        str += ("------------------------------------------------");
        for (int i = 0; i < songs.length; i++) {
            if (songs[i] != null) {
                str += String.format("%-10s%s\n", i + 1, songs[i].toString());

            } else
                break;
        }
        return str;
    }
}
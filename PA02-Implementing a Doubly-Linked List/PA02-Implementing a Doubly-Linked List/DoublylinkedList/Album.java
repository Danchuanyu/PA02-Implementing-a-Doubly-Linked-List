public class Album implements Comparable<Album> {
    private int id;
    private String[] artists;
    private String title;
    private int numSongs;

    public Album(int id, String[] strings, String title, int numSongs) {
        this.id = id;
        this.artists = strings;
        this.title = title;
        this.numSongs = numSongs;
    }

    public int getId() {
        return id;
    }

    public String[] getArtists() {
        return artists;
    }

    public String getTitle() {
        return title;
    }

    public int getNumSongs() {
        return numSongs;
    }

    public String toString() {
        return "ID: " + id + " -- " + "[" + String.join(", ", artists) + "]" + " -- " + numSongs + " songs";
    }

    public int compareTo(Album other) {
        return Integer.compare(numSongs, other.numSongs);
    }

    public Album getNext() {
        return null;
    }
}

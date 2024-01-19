import java.util.Random;
import java.util.Scanner;

class Song {
    String title;
    String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Artist: " + artist;
    }
}

class Node {
    Song data;
    Node next;
    Node prev;

    public Node(Song data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

class Playlist {
    Node head;
    Node tail;
    Node currentSong;

    public Playlist() {
        this.head = null;
        this.tail = null;
        this.currentSong = null;
    }

    public void addSong(String title, String artist) {
        Song newSong = new Song(title, artist);
        Node newNode = new Node(newSong);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void play() {
        if (head == null) {
            System.out.println("Playlist is empty. Add songs first.");
        } else {
            currentSong = head;
            System.out.println("Playing: " + currentSong.data);
        }
    }

    public void skip() {
        if (currentSong == null || currentSong.next == null) {
            System.out.println("No more songs to skip. Playlist completed.");
        } else {
            currentSong = currentSong.next;
            System.out.println("Playing next: " + currentSong.data);
        }
    }

    public void displayPlaylist() {
        if (head == null) {
            System.out.println("Playlist is empty.");
        } else {
            Node current = head;
            while (current != null) {
                System.out.println(current.data);
                current = current.next;
            }
        }
    }

    public void shuffle() {
        if (head != null) {
            Song[] songsArray = toArray();

            Random rand = new Random();
            for (int i = songsArray.length - 1; i > 0; i--) {
                int j = rand.nextInt(i + 1);
                Song temp = songsArray[i];
                songsArray[i] = songsArray[j];
                songsArray[j] = temp;
            }

            head = null;
            tail = null;
            for (Song song : songsArray) {
                addSong(song.title, song.artist);
            }

            currentSong = head;
            System.out.println("Playlist shuffled.");
        } else {
            System.out.println("Cannot shuffle an empty playlist.");
        }
    }

    private Song[] toArray() {
        if (head == null) {
            return new Song[0];
        }

        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }

        Song[] array = new Song[size];
        current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }

        return array;
    }
}

public class MusicPlayer {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();
        Scanner scanner = new Scanner(System.in);

        // Adding songs to the playlist
        playlist.addSong("Song1", "Artist1");
        playlist.addSong("Song2", "Artist2");
        playlist.addSong("Song3", "Artist3");

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Play");
            System.out.println("2. Skip");
            System.out.println("3. Display Playlist");
            System.out.println("4. Shuffle Playlist");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    playlist.play();
                    break;
                case 2:
                    playlist.skip();
                    break;
                case 3:
                    playlist.displayPlaylist();
                    break;
                case 4:
                    playlist.shuffle();
                    break;
                case 5:
                    System.out.println("Exiting Music Player. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 5);

        scanner.close();
    }
}

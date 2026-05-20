import java.util.Scanner;

class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
}

class Playlist {
    private Song[] songs;
    private int count;
    private int maxLimit = 50;

    public Playlist() {
        songs = new Song[maxLimit];
        count = 0;
    }

    public void addSong(Song s) {
        if (count < maxLimit) {
            songs[count] = s;
            count++;
            System.out.println("Song added successfully!");
        } else {
            System.out.println("Playlist is full! Cannot add more songs.");
        }
    }

    public void displayPlaylist() {
        if (count == 0) {
            System.out.println("The playlist is empty.");
            return;
        }
        System.out.println("\nPlaylist:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + songs[i].getTitle() + " - " + songs[i].getArtist());
        }
    }

    public void shufflePlaylist() {
        if (count <= 1) {
            System.out.println("Not enough songs to shuffle.");
            return;
        }

        long seed = System.currentTimeMillis();
        
        for (int i = count - 1; i > 0; i--) {
            seed = (seed * 1103515245 + 12345) & 0x7fffffff;
            int j = (int) (seed % (i + 1));
            
            Song temp = songs[i];
            songs[i] = songs[j];
            songs[j] = temp;
        }
        System.out.println("\nShuffled Playlist:");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Playlist myPlaylist = new Playlist();
        int choice;

        do {
            System.out.println("\n1. Add Song");
            System.out.println("2. Display Playlist");
            System.out.println("3. Shuffle Playlist");
            System.out.println("4. Exit");
            System.out.print("Enter Choice: ");
            
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Song Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Artist: ");
                    String artist = sc.nextLine();
                    
                    Song s = new Song(title, artist);
                    myPlaylist.addSong(s);
                    break;

                case 2:
                    myPlaylist.displayPlaylist();
                    break;

                case 3:
                    myPlaylist.shufflePlaylist();
                    myPlaylist.displayPlaylist();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Scanner;

public class Main {


    static boolean loop = true;
    static Scanner sc = new Scanner(System.in);
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");

    public static void main(String[] args) {

        while (loop) {
            menu();
        }
    }

    private static void menu() {
        System.out.println("\n=================================");
        System.out.println("              Menu                 ");
        System.out.println("===================================");
        System.out.println("1. Add Singer");
        System.out.println("2. Add Song");
        System.out.println("3. Add Existing Song To Existing Singer");
        System.out.println("4. Add Add New Song To Existing Singer");
        System.out.println("5. Add Add Existing Song To Existing Singer");
        System.out.println("6. Delete Singer");
        System.out.println("7. Delete Song");
        System.out.println("8. Add New Singer And Two New Songs Connected To The Song");
        System.out.println("9. Find Song by Id");
        System.out.println("10. Show All Singers");
        System.out.println("11. Show All Songs");
        System.out.println("12. Update Singer name");
        System.out.println("0. Exit");
        System.out.print("\n Make a Choice: ");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {

            case 1:
                addSinger();
                break;
            case 2:
                addSong();
                break;
            case 3:
                addExistingSongToExistingSinger();
                break;
            case 4:
                addNewSongToExistingSinger();
                break;
            case 5:
                addExistingSongToExistingSinger();
                break;
            case 6:
                deleteSingerById();
                break;
            case 7:
                deleteSongByID();
                break;
            case 8:
                addNewSingerAndTwoNewSongsConnectedToTheSong();
                break;
            case 9:
                findSongById();
            case 10:
                showAllSingers();
                break;
            case 11:
                showAllSongs();
                break;
            case 12:
                updateSingerName();
                break;
            case 0:
                loop = false;
                System.out.println("Program Terminates");
                break;
            default:
                System.out.println("ERROR");
        }
    }

    private static void addNewSingerAndTwoNewSongsConnectedToTheSong() {
        EntityManager em = emf.createEntityManager();


        Singer s = new Singer("Shakira");

        Song s1 = new Song("f1");
        Song s2 = new Song("f2");

        s.addSong(s1);
        s.addSong(s2);

        em.getTransaction().begin();
        em.persist(s);
        em.persist(s1);
        em.persist(s2);
        em.getTransaction().commit();
        em.close();
    }


    public static void addSinger() {
        EntityManager em = emf.createEntityManager();

        System.out.print("Name of Singer: ");
        String name = sc.nextLine();

        Singer singer = new Singer(name);

        em.getTransaction().begin();
        em.persist(singer);
        em.getTransaction().commit();
        em.close();
    }


    private static void addSong() {
        EntityManager em = emf.createEntityManager();

        System.out.print("Song name: ");
        String songName = sc.nextLine();

        Song song = new Song(songName);

        em.getTransaction().begin();
        em.persist(song);
        em.getTransaction().commit();
        em.close();
    }


    private static void addExistingSongToExistingSinger() {
        showAllSingers();
        System.out.print("Singer id: ");
        int singerId = sc.nextInt();
        sc.nextLine();

        showAllSongs();
        System.out.print("Song id: ");
        int songId = sc.nextInt();
        sc.nextLine();

        EntityManager em = emf.createEntityManager();

        Singer singer = em.find(Singer.class, singerId);
        Song song = em.find(Song.class, songId);

        em.getTransaction().begin();
        singer.addSong(song);
        em.getTransaction().commit();
        em.close();


    }


    private static void addNewSongToExistingSinger() {
        System.out.print("Song name: ");
        String name = sc.nextLine();

        Song song = new Song(name);

        showAllSingers();

        System.out.print("Id of Singer: ");
        int id = sc.nextInt();
        sc.nextLine();

        EntityManager em = emf.createEntityManager();
        Singer singer = em.find(Singer.class, id);

        em.getTransaction().begin();
        singer.addSong(song);
        em.getTransaction().commit();
        em.close();


    }


    public static void updateSingerName() {
        showAllSingers();

        System.out.print("Singer id: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();


        EntityManager em = emf.createEntityManager();

        Singer singer = em.find(Singer.class, id);

        em.getTransaction().begin();
        singer.setName(name);

        em.getTransaction().commit();
        em.close();
    }


    private static void deleteSingerById() {
        EntityManager em = emf.createEntityManager();

        System.out.println("Singer id: ");
        int id = sc.nextInt();
        sc.nextLine();

        Singer s = em.find(Singer.class, id);

        em.getTransaction().begin();
        em.remove(s);
        em.getTransaction().commit();
        em.close();


    }


    private static void deleteSongByID() {

        EntityManager em = emf.createEntityManager();

        System.out.print("Song id: ");
        int id = sc.nextInt();
        sc.nextLine();

        Song s = em.find(Song.class, id);

        em.getTransaction().begin();
        em.remove(s);
        em.getTransaction().commit();
        em.close();
    }


    private static void findSongById() {

        EntityManager em = emf.createEntityManager();

        System.out.print("Song Id: ");
        int id = sc.nextInt();
        sc.nextLine();


        Query q = em.createQuery("SELECT s FROM Song s WHERE s.id=:id");
        q.setParameter("id", id);
        q.getResultList().forEach(System.out::println);

        em.close();


    }


    private static void showAllSingers() {

        EntityManager em = emf.createEntityManager();

        em.createQuery("SELECT s FROM Singer s", Singer.class)
                .getResultList()
                .forEach(System.out::println);

        em.close();

    }


    private static void showAllSongs() {
        EntityManager em = emf.createEntityManager();

        em.createQuery("SELECT s FROM Song s", Song.class)
                .getResultList()
                .forEach(System.out::println);

        em.close();
    }
}

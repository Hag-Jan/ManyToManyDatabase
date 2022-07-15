package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Song {
    @Id
    @GeneratedValue
    private int id;

    private String nameOfSong;


    @ManyToMany
    List<Singer> singers = new ArrayList<>();

    public Song(String nameOfSong, List<Singer> singers) {
        this.nameOfSong = nameOfSong;
        this.singers = singers;
    }



    public void addSong(Song song){




    }


    public Song(String songName, String typeOfSong) {
    }

    public String getNameOfSong() {
        return nameOfSong;
    }

    public void setNameOfSong(String nameOfSong) {
        this.nameOfSong = nameOfSong;
    }

    public List<Singer> getSingers() {
        return singers;
    }

    public void setSingers(List<Singer> singers) {
        this.singers = singers;
    }

    public Song(String nameOfSong) {
        this.nameOfSong = nameOfSong;
    }

    public Song() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", nameOfSong='" + nameOfSong + '\'' +
                ", singers=" + singers +
                '}';
    }
}

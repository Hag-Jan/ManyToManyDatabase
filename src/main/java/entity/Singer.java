package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Singer {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany
    private List<Song> songs = new ArrayList<>();

    public Singer() {

    }



    public void addSong(Song song) {
        songs.add(song);
        song.getSingers().add(this);
    }

    public void removeSong(Song song) {
        songs.remove(song);
        song.getSingers().remove(this);
    }

    public Singer(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Singer(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", songs=" + songs +
                '}';
    }
}

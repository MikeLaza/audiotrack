package audiotrack.bean;

import java.io.Serializable;

public class Playlist implements Entity<Integer>,Serializable {
    private int id;
    private String namePlaylist;

    public Playlist() {
    }



    public Playlist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }

    @Override
    public Integer getId() {
        return id;
    }
}

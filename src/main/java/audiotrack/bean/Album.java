package audiotrack.bean;



public class Album implements Entity<Integer>{
    private  int id;
    private int idSinger;
    private  String nameAlbum;

    public Album() {
    }

    public Album(int idSinger, String nameAlbum) {
        this.idSinger = idSinger;
        this.nameAlbum = nameAlbum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSinger() {
        return idSinger;
    }

    public void setIdSinger(int idSinger) {
        this.idSinger = idSinger;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }

    @Override
    public Integer getId() {
        return id;
    }






}

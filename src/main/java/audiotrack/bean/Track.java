package audiotrack.bean;

public class Track implements Entity<Integer>{
    private int id;
    private int idAlbum;
    private String nameTrack;
    private int duration;
    private double price;
    private String nameAlbum;
    private int imageNumber;

    public Track() {
    }

    public Track(int id, int idAlbum, String nameTrack, int duration, double price) {
        this.id = id;
        this.idAlbum = idAlbum;
        this.nameTrack = nameTrack;
        this.duration = duration;
        this.price = price;
    }

    public Track(int idAlbum, String nameTrack, int duration, double price) {
        this.idAlbum = idAlbum;
        this.nameTrack = nameTrack;
        this.duration = duration;
        this.price = price;
    }




    public Track(int id, int idAlbum, String nameTrack, int duration, double price, String nameAlbum, int imageNumber) {
        this.id = id;
        this.idAlbum = idAlbum;
        this.nameTrack = nameTrack;
        this.duration = duration;
        this.price = price;
        this.nameAlbum = nameAlbum;
        this.imageNumber = imageNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNameTrack() {
        return nameTrack;
    }

    public void setNameTrack(String nameTrack) {
        this.nameTrack = nameTrack;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNameAlbum() {
        return nameAlbum;
    }

    public void setNameAlbum(String nameAlbum) {
        this.nameAlbum = nameAlbum;
    }


    public int getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    @Override
    public Integer getId() {
        return id;
    }


}

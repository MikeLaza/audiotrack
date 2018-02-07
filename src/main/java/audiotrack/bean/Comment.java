package audiotrack.bean;

public class Comment implements Entity<Integer> {
    private int id;
    private int userId;
    private int trackId;
    private String text;
    private String userLogin;


    public Comment(int userId, int trackId, String text) {
        this.userId = userId;
        this.trackId = trackId;
        this.text = text;
    }

    public Comment(int id, int userId, int trackId, String text, String userLoing) {
        this.id = id;
        this.userId = userId;
        this.trackId = trackId;
        this.text = text;
        this.userLogin = userLoing;
    }

    public Comment() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public Integer getId() {
        return id;
    }


}

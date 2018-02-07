package audiotrack.bean;

public class Order  implements Entity<Integer> {
    private int id;
    private int userID;


    public Order() {
    }

    public Order(int userID) {

        this.userID = userID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public Integer getId() {
        return id;
    }



}


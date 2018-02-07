package audiotrack.bean;

public class User implements Entity<Integer> {
    private int id;
    private String loginUser;
    private String password;
    private String email;
    private String locale;
    private UserEnum userEnum;
    private Double balance;
    private int discount;

    public User() {
    }

    public User(String loginUser, String password, String email, String locale, UserEnum userEnum, Double balance, int discount) {
        this.loginUser = loginUser;
        this.password = password;
        this.email = email;
        this.locale = locale;
        this.userEnum = userEnum;
        this.balance = balance;
        this.discount = discount;
    }

    public User(String loginUser, String password) {
        this.loginUser = loginUser;
        this.password = password;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserEnum getUserEnum() {
        return userEnum;
    }

    public void setUserEnum(UserEnum userEnum) {
        this.userEnum = userEnum;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public Integer getId() {
        return id;
    }


}

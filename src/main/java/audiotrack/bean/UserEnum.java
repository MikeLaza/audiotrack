package audiotrack.bean;

public enum UserEnum {
    ADMIN("admin"),
    CLIENT("client");

    private String value;

    UserEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

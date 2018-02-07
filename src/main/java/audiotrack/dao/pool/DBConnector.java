package audiotrack.dao.pool;


import java.util.ResourceBundle;

public final class DBConnector {
    private static final DBConnector instance = new DBConnector();
    private ResourceBundle bundle = ResourceBundle.getBundle("dao.db");

    private DBConnector() {
    }

    public static DBConnector getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}

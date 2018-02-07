package audiotrack.dao.exception;

public class ConnectionException extends DAOException {

    public ConnectionException() {
        super();
    }

    public ConnectionException(String msg) {
        super(msg);
    }

    public ConnectionException(Exception e) {
        super(e);
    }

    public ConnectionException(String msg, Exception e) {
        super(msg, e);
    }
}

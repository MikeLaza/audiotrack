package audiotrack.dao.pool;


import audiotrack.dao.exception.ConnectionException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static ConnectionPool instance;


    private BlockingQueue<Connection> connections;
    private BlockingQueue<Connection> freeConnections;
    private static Lock lock=new ReentrantLock();
    private static AtomicBoolean isInitialized =new AtomicBoolean(false);
    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;
    public static final String DRIVER_NAME ="com.mysql.jdbc.Driver";
    private static Logger logger = Logger.getLogger(ConnectionPool.class);

    private ConnectionPool() throws ConnectionException {
        try {
            DBConnector dbConnector = DBConnector.getInstance();

            final String dbDriver = ConfigureDB.DRIVER;
            this.driverName = dbConnector.getValue(dbDriver);

            final String dbUser = ConfigureDB.USER;
            this.user = dbConnector.getValue(dbUser);

            final String dbUrl = ConfigureDB.URL;
            this.url = dbConnector.getValue(dbUrl);

            final String dbPassword = ConfigureDB.PASSWORD;
            this.password = dbConnector.getValue(dbPassword);

            final String poolSize = ConfigureDB.POOL_SIZE;
            final String value = dbConnector.getValue(poolSize);
            this.poolSize = Integer.parseInt(value);

            Class.forName(DRIVER_NAME);
        } catch (NumberFormatException e) {
            throw new ConnectionException(e);

        }  catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        connections = new ArrayBlockingQueue<Connection>(poolSize);
        freeConnections = new ArrayBlockingQueue<Connection>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            connections.add(getConnection());
        }
    }
    private Connection getConnection()throws ConnectionException {
        Connection connection ;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new ConnectionException("Cannot get connection",e);
        }
        return connection;
    }
    public  Connection getFreeConnection() throws ConnectionException {

        lock.lock();
        try{
            Connection connection ;
            final int size = connections.size();
            if (size == 0) {
                connection = getConnection();
            } else {
                try {
                    connection = (Connection) connections.take();
                } catch (InterruptedException e) {
                    throw new ConnectionException("Cannot get free connection",e);
                }

            }
            freeConnections.add(connection);
            return connection;

        }finally {
            lock.unlock();
        }


    }

    private  void putBackConnection(Connection connection) throws NullPointerException {

        lock.lock();
        try{
            if (connection != null) {
                freeConnections.remove(connection);
                connections.add(connection);
            } else {
                throw new NullPointerException();
            }

        }finally {
            lock.unlock();
        }

    }
    public static ConnectionPool getInstance() throws ConnectionException {

        lock.lock();
        try{
            if (!isInitialized.get()) {
                 instance = new ConnectionPool();
                isInitialized.set(true);
            }
            return instance;
        }finally {
            lock.unlock();
        }


    }


    public void closeConnection(Connection con, Statement st, ResultSet resultSet)  {
        try {
            this.putBackConnection(con);
        } catch (NullPointerException e) {
            logger.info(e.getMessage());
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.info(e.getMessage());
            }
        }
    }
    public void closeConnection(Connection con, Statement st) {
        this.closeConnection(con, st, null);
    }
}

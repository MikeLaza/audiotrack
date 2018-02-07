package audiotrack.dao;

import audiotrack.bean.Entity;
import audiotrack.dao.exception.ConnectionException;
import audiotrack.dao.exception.DAOException;
import audiotrack.dao.pool.ConnectionPool;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T extends Entity<PK>,PK extends Serializable> implements Dao<T,PK> {

    public AbstractDAO() {
    }

    protected abstract String getQueryForAdd();
    protected abstract String getQueryForDelete();
    protected abstract String getQueryForSelect();
    protected abstract String getQueryForSelectPaging();
    protected abstract String getQueryForUpdate();
    protected abstract String getWhereQuery();
    protected abstract String getQueryForCountRecords();

    protected abstract List<T> makeEntities(ResultSet rs) throws DAOException;
    protected abstract void prepareStatementForAdding(PreparedStatement statement, T entity) throws DAOException;
    protected abstract void prepareStatementForUpdating(PreparedStatement statement, T entity) throws DAOException;


    @Override
    public T getById(PK key) throws DAOException {

        ConnectionPool connectionPool = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try{
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getFreeConnection();

            String query = getQueryForSelect() + getWhereQuery();
            preparedStatement = connection.prepareStatement(query);

            Integer keyInt = (Integer) key;
            preparedStatement.setInt(1, keyInt);

            ResultSet rs = preparedStatement.executeQuery();

            List<T> list;
            list = makeEntities(rs);


            if (list == null || list.isEmpty() ) {
                return null;
            }else {
                Entity entity=list.get(0);
                return (T) entity;
            }

        } catch (SQLException e) {
            throw new DAOException("Error while getting record by Primary Key "+e);
        } finally {
            closeConnection(connectionPool, preparedStatement, connection);
        }

    }

    protected void closeConnection(ConnectionPool connectionPool, PreparedStatement preparedStatement, Connection connection) {
        if (connectionPool != null) {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public List<T> getAllEntities() throws DAOException {
        List<T> list;
        String query = getQueryForSelect();
        ConnectionPool connectionPool = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try{

            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getFreeConnection();
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            list = makeEntities(rs);
            return list;

        } catch (SQLException e) {
            throw new DAOException("Error while getting all records from table "+e);
        } finally {
            closeConnection(connectionPool, preparedStatement, connection);
        }

    }


    @Override
    public List<T> getAllEntitiesPaging(int startIndex, int totalCount) throws DAOException {
        List<T> list;
        String query = getQueryForSelectPaging();
        ConnectionPool connectionPool = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try{

            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getFreeConnection();
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,startIndex);
            preparedStatement.setInt(2,totalCount);

            ResultSet rs = preparedStatement.executeQuery();

            list = makeEntities(rs);
            return list;

        } catch (SQLException e) {
            throw new DAOException("Error while getting all records from table "+e);
        } finally {
            closeConnection(connectionPool, preparedStatement, connection);
        }
    }

    @Override
    public int getCountOfRecords() throws DAOException {
        String query = getQueryForCountRecords();
        ConnectionPool connectionPool = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try{

            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getFreeConnection();
            preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            int countRecords=0;
            while (rs.next()) {
                countRecords=rs.getInt(1);
            }
            return countRecords;

        } catch (SQLException e) {
            throw new DAOException("Error while getting count of  records from table "+e);
        } finally {
            closeConnection(connectionPool, preparedStatement, connection);
        }

    }

    @Override
    public void addEntity(T entity) throws DAOException {
        ConnectionPool connectionPool = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try{

            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getFreeConnection();

            String query = getQueryForAdd();

            preparedStatement = connection.prepareStatement(query);
            prepareStatementForAdding(preparedStatement, entity);
            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new DAOException("Error while adding ", e);
        } catch (ConnectionException e) {
            throw new DAOException( e);
        } finally {
            closeConnection(connectionPool, preparedStatement, connection);
        }
    }

    @Override
    public void update(T entity) throws  DAOException {

        ConnectionPool connectionPool = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try{

            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getFreeConnection();

            String query=getQueryForUpdate();

            preparedStatement = connection.prepareStatement(query);
            prepareStatementForUpdating(preparedStatement,entity);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new DAOException("Error while updateting"+e);
        } finally {
            closeConnection(connectionPool, preparedStatement, connection);
        }

    }

    @Override
    public void delete(Integer id) throws  DAOException {
        ConnectionPool connectionPool = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try{

            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getFreeConnection();
            String query=getQueryForDelete();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Error while deleting"+e);
        } finally {
            closeConnection(connectionPool, preparedStatement, connection);
        }

    }

    @Override
    public boolean isExistEntity(PK id) throws  DAOException {
        ConnectionPool connectionPool = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        boolean isExist=false;

        try{
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getFreeConnection();

            String query = getQueryForSelect() + getWhereQuery();
            preparedStatement = connection.prepareStatement(query);

            final Integer keyInt = (Integer) id;
            preparedStatement.setInt(1, keyInt);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                isExist=true;
            }

            return isExist;
        } catch (SQLException e) {
            throw new DAOException("Error while getting record by id "+e);
        } finally {
            closeConnection(connectionPool, preparedStatement, connection);
        }

    }
}

package audiotrack.dao.impl;


import audiotrack.bean.User;
import audiotrack.bean.UserEnum;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.dao.AbstractDAO;
import audiotrack.dao.exception.ConnectionException;
import audiotrack.dao.exception.DAOException;
import audiotrack.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<User,Integer> {

    private static final String SING_IN = "SELECT user_id, login, password, email, locale, is_admin, balance, discount FROM audio_tracks_order.user WHERE login=? AND password=?";
    private static final String SIGN_UP = "INSERT INTO audio_tracks_order.user (login, password, email, locale,is_admin, balance, discount) VALUES (?,?,?,?,?,?,?);";
    private static final String GET_USER_BY_LOGIN = "SELECT user_id, login, password, email, locale, is_admin, balance, discount FROM audio_tracks_order.user WHERE login=?";


    private final static String GET_ALL_USERS = "SELECT user_id, login, password, email, locale, is_admin, balance, discount FROM audio_tracks_order.user";
    private final static String GET_ALL_USERS_PAGING = "select * from audio_tracks_order.user limit ?,? ";
    private final static String GET_COUNT_RECORDS = "select count(*) from (SELECT user_id, login, password, email, locale, is_admin, balance, discount FROM audio_tracks_order.user) as a ";
    private static final String DELETE_USER = "DELETE FROM audio_tracks_order.user WHERE user_id=?";
    private static final String UPDATE_USER = "UPDATE audio_tracks_order.user SET login=?, password=?, email=?, locale=?, is_admin=?, balance=?, discount=? WHERE user_id=?";
    private static final String UPDATE_LOCALE = "UPDATE audio_tracks_order.user SET locale=? WHERE login=?";
    private static final String WHERE_CONDITION = " WHERE user_id = ?";


    public User getUserByLogin(String login) throws DAOException {
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        User user;
        try{

            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getFreeConnection();
            statement=connection.prepareStatement(GET_USER_BY_LOGIN);
            statement.setString(1,login);
            ResultSet resultSet= statement.executeQuery();
            resultSet.next();
            user= makeUser(resultSet);



        } catch (ConnectionException | SQLException e) {
            throw new DAOException("Cannot get user by login",e);
        } finally {
            if (connectionPool != null) {
                connectionPool.closeConnection(connection, statement);
            }
        }
        return user;

    }



    @Override
    public String getQueryForSelect() {
        return GET_ALL_USERS;
    }

    @Override
    protected String getQueryForSelectPaging() {
        return GET_ALL_USERS_PAGING;
    }

    @Override
    public String getQueryForAdd() {
        return SIGN_UP;
    }

    @Override
    public String getQueryForUpdate() {
        return UPDATE_USER;
    }

    @Override
    protected String getWhereQuery() {
        return WHERE_CONDITION;
    }

    @Override
    protected String getQueryForCountRecords() {
        return GET_COUNT_RECORDS;
    }

    @Override
    public String getQueryForDelete() {
        return DELETE_USER;
    }


    @Override
    protected List<User> makeEntities(ResultSet rs) throws DAOException {
        List<User> result = new ArrayList<>();
        try {
            while (rs.next()) {
                makeEntity(rs, result);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot make entities for user",e);
        }
        return result;
    }

    private void makeEntity(ResultSet rs, List<User> result) throws SQLException {
        User user= makeUser(rs);
        result.add(user);
    }

    @Override
    protected void prepareStatementForAdding(PreparedStatement statement, User entity) throws DAOException {
        try {
            fillStatement(statement, entity);
        } catch (SQLException e) {
            throw new DAOException("Cannot prepare statement for adding user",e);
        }

    }

    private void fillStatement(PreparedStatement statement, User entity) throws SQLException {
        String loginUser = entity.getLoginUser();
        statement.setString(1, loginUser);
        String password = entity.getPassword();
        statement.setString(2, password);
        String email = entity.getEmail();
        statement.setString(3, email);
        String locale = entity.getLocale();
        statement.setString(4, locale);

        final UserEnum userEnum = entity.getUserEnum();
        boolean isAdmin= isAdminClient(userEnum);

        statement.setBoolean(5, isAdmin);
        Double balance = entity.getBalance();
        statement.setDouble(6, balance);
        int discount = entity.getDiscount();
        statement.setInt(7, discount);
    }

    private boolean isAdminClient(UserEnum userEnum) {
        String userEnumValue = userEnum.getValue();
        if(userEnumValue.equals(UserEnum.ADMIN)){
            return true;
        }
        return false;
    }

    @Override
    protected void prepareStatementForUpdating(PreparedStatement statement, User entity) throws DAOException {
        try {
            fillStatement(statement, entity);
            final Integer id = entity.getId();
            statement.setInt(8, id);
        } catch (SQLException e) {
            throw new DAOException("Cannot prepare statement for updating user",e);
        }
    }

    public User signIn(User user) throws DAOException{

        ConnectionPool connectionPool=null;
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet;
        try {
            connectionPool= ConnectionPool.getInstance();
            connection=connectionPool.getFreeConnection();
            preparedStatement = connection.prepareStatement(SING_IN);
            String login = user.getLoginUser();
            preparedStatement.setString(1, login);
            String password = user.getPassword();
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = makeUser(resultSet);

            return user;
        }catch (SQLException e){
            throw new DAOException("Cannot sing in user" );
        }finally {
            closeConnection(connectionPool,preparedStatement,connection);
        }

    }

    private User makeUser(ResultSet resultSet) throws SQLException {
        User user =new User();
        final int userId = resultSet.getInt(ParameterEnum.USER_ID.toString());
        user.setId(userId);

        final String login = resultSet.getString(ParameterEnum.LOGIN.toString());
        user.setLoginUser(login);

        final String pas = resultSet.getString(ParameterEnum.PASSWORD.toString());
        user.setPassword(pas);

        final String email = resultSet.getString(ParameterEnum.EMAIL.toString());
        user.setEmail(email);

        final String locale = resultSet.getString(ParameterEnum.LOCALE.toString());
        user.setLocale(locale);

        final int isAdmin=resultSet.getInt(ParameterEnum.IS_ADMIN.toString());
        UserEnum userEnum=getUserEnum(isAdmin);
        user.setUserEnum(userEnum);
        final double balance = resultSet.getDouble(ParameterEnum.BALANCE.toString());
        user.setBalance(balance);
        final int discount = resultSet.getInt(ParameterEnum.DISCOUNT.toString());
        user.setDiscount(discount);
        return user;
    }

    private UserEnum getUserEnum(int isAdmin ) {
        if(isAdmin!=0){
            return UserEnum.ADMIN;

        }
        return UserEnum.CLIENT;
    }

    public void updateLocale(String login, String locale) throws DAOException{
        ConnectionPool connectionPool = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getFreeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_LOCALE);
            preparedStatement.setString(1, locale);
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();
        } catch (ConnectionException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException("Cannot update locale",e);
        } finally {
            closeConnection(connectionPool,preparedStatement,connection);
        }
    }


}

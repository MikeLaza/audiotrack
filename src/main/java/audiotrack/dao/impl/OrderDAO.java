package audiotrack.dao.impl;

import audiotrack.bean.Order;
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

public class OrderDAO extends AbstractDAO<Order,Integer> {


    private final static String GET_ALL_ORDERS = "SELECT order_id, user_id FROM audio_tracks_order.order";
    private final static String GET_ALL_ORDERS_PAGING = "select * from audio_tracks_order.order limit ?,? ";
    private static final String ADD_ORDER = "INSERT INTO audio_tracks_order.order ( user_id) VALUES (?);";
    public static final String ADD_ORDER_M2M="INSERT INTO audio_tracks_order.order_m2m_track (`order`, `track`) VALUES (?, ?);";

    private static final String DELETE_ORDER = "DELETE FROM audio_tracks_order.order WHERE order_id=? ";
    private static final String UPDATE_ORDER = "UPDATE audio_tracks_order.order SET user_id=? WHERE order_id=?";
    public static final String LAST_ADDED_ID="SELECT order_id FROM audio_tracks_order.order ORDER BY order_id desc limit 1;";
    private static final String WHERE_CONDITION = " WHERE order_id = ?";

    public void handleOrder(int trackId)  {
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try{

        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getFreeConnection();
        statement=connection.prepareStatement(LAST_ADDED_ID);
        ResultSet resultSet= statement.executeQuery();
        resultSet.next();
        int orderId=resultSet.getInt(ParameterEnum.ORDER_ID.toString());

        statement = connection.prepareStatement(ADD_ORDER_M2M);

        statement.setInt(1, orderId);
        statement.setInt(2, trackId);
        statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connectionPool,statement,connection);
         }

    }

    @Override
    public String getQueryForSelect() {
        return GET_ALL_ORDERS;
    }

    @Override
    protected String getQueryForSelectPaging() {
        return GET_ALL_ORDERS_PAGING;
    }

    @Override
    public String getQueryForAdd() {
        return ADD_ORDER;
    }

    @Override
    public String getQueryForUpdate() {
        return UPDATE_ORDER;
    }

    @Override
    protected String getWhereQuery() {
        return WHERE_CONDITION;
    }

    @Override
    protected String getQueryForCountRecords() {
        return null;
    }

    @Override
    public String getQueryForDelete() {
        return DELETE_ORDER;
    }


    @Override
    protected List<Order> makeEntities(ResultSet rs) throws DAOException {

        List<Order> orders=new ArrayList<Order>();

        try {
            while (rs.next()) {
                makeEntity(rs, orders);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot make entities for order",e);
        }



        return orders;
    }

    private void makeEntity(ResultSet rs, List<Order> orders) throws SQLException {
        Order order = makeOrder(rs);
        orders.add(order);
    }

    private Order makeOrder(ResultSet rs) throws SQLException {

        Order order= new Order();

        final int orderId = rs.getInt(ParameterEnum.ORDER_ID.toString());
        order.setId(orderId);
        final int userId = rs.getInt(ParameterEnum.USER_ID.toString());
        order.setUserID(userId);
        return order;
    }

    @Override
    protected void prepareStatementForAdding(PreparedStatement statement, Order entity) throws DAOException {
        try {
            fillStatement(statement,entity);
        } catch (SQLException e) {
            throw new DAOException("Cannot prepare statement for updating order",e);
        }

    }

    @Override
    protected void prepareStatementForUpdating(PreparedStatement statement, Order entity) throws DAOException {
        try {
            fillStatement(statement,entity);
            Integer id = entity.getId();
            statement.setInt(2, id);
        } catch (SQLException e) {
            throw new DAOException("Cannot prepare statement for updating order",e);
        }
    }


    private void fillStatement(PreparedStatement statement, Order entity) throws SQLException {
        int userID = entity.getUserID();
        statement.setInt(1, userID);

    }
}

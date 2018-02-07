package audiotrack.dao.impl;

import audiotrack.bean.Comment;
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

public class CommentDAO extends AbstractDAO<Comment,Integer> {

    private final static String GET_ALL_COMMENTS = "SELECT comment.comment_id, comment.track, comment.user_id, comment.text FROM audio_tracks_order.comment";
    private final static String GET_ALL_COMMENTS_PAGING = "select * from audio_tracks_order.comment limit ?,? ";
    private static final String ADD_COMMENT = "INSERT INTO audio_tracks_order.comment (track, user_id, text) VALUES (?, ?,?);";
    private static final String DELETE_COMMENT = "DELETE FROM audio_tracks_order.comment WHERE comment_id=?";
    private static final String UPDATE_COMMENT = "UPDATE audio_tracks_order.comment SET track=?, user_id=?, text=? WHERE comment_id=?";
    public static final String GET_ALL_COMMENTS_TRACK ="select c.comment_id, c.user_id, c.track, c.text,  u.login from (select cc.comment_id, cc.user_id, cc.track, cc.text from audio_tracks_order.comment as cc where track=?) as c inner join audio_tracks_order.user as u on c.user_id=u.user_id";
    private static final String WHERE_CONDITION = " WHERE comment_id = ?";

    public List<Comment> getAllCommentsTrack (int trackId) throws DAOException {
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        List<Comment> comments=null;
        try{

            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getFreeConnection();
            statement=connection.prepareStatement(GET_ALL_COMMENTS_TRACK);
            statement.setInt(1,trackId);
            ResultSet resultSet= statement.executeQuery();
            comments= makeEntities(resultSet);

        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (DAOException | SQLException e) {
            throw new DAOException("Cannot get all comments of the track",e);
        }  finally {
            closeConnection(connectionPool,statement,connection);

        }
        return comments;
    }


    @Override
    public String getQueryForSelect() {
        return GET_ALL_COMMENTS;
    }

    @Override
    protected String getQueryForSelectPaging() {
        return GET_ALL_COMMENTS_PAGING;
    }

    @Override
    public String getQueryForAdd() {
        return ADD_COMMENT;
    }

    @Override
    public String getQueryForUpdate() {
        return UPDATE_COMMENT;
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
        return DELETE_COMMENT;
    }



    @Override
    protected List<Comment> makeEntities(ResultSet rs) throws DAOException {

        List<Comment> comments=new ArrayList<Comment>();

        try {
            while (rs.next()) {
                makeEntity(rs, comments);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot make entities COMMENTS",e);
        }

        return  comments;
    }

    private void makeEntity(ResultSet rs, List<Comment> comments) throws SQLException {
        Comment comment = makeComment(rs);
        comments.add(comment);
    }

    private Comment makeComment(ResultSet rs) throws SQLException {

        Comment comment=new Comment();

        final int commentId = rs.getInt(ParameterEnum.COMMENT_ID.toString());
        comment.setId(commentId);
        final int track = rs.getInt(ParameterEnum.TRACK.toString());
        comment.setTrackId(track);
        final int user_id = rs.getInt(ParameterEnum.USER_ID.toString());
        comment.setUserId(user_id);
        final String text = rs.getString(ParameterEnum.TEXT.toString());
        comment.setText(text);

        String login = rs.getString(ParameterEnum.LOGIN.toString());
        comment.setUserLogin(login);

        return comment;
    }

    @Override
    protected void prepareStatementForAdding(PreparedStatement statement, Comment entity) throws DAOException {
        try {
            fillStatement(statement, entity);
        } catch (SQLException e) {
            throw new DAOException("Cannot prepare statement for adding COMMENT",e);
        }
    }

    @Override
    protected void prepareStatementForUpdating(PreparedStatement statement, Comment entity) throws DAOException {
        try {
            fillStatement(statement, entity);
            final Integer id = entity.getId();
            statement.setInt(4, id);
        } catch (SQLException e) {
            throw new DAOException("Cannot prepare statement for updating COMMENT",e);
        }
    }

    private void fillStatement(PreparedStatement statement, Comment entity) throws SQLException {
        int trackId = entity.getTrackId();
        statement.setInt(1, trackId);
        int userId = entity.getUserId();
        statement.setInt(2, userId);
        String text = entity.getText();
        statement.setString(3, text);

    }
}


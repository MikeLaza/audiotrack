package audiotrack.dao.impl;

import audiotrack.bean.Track;
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

public class TrackDAO extends AbstractDAO<Track, Integer> {

    private final static String GET_ALL_TRACKS = "SELECT o.track_id, o.album_id, o.name_track, o.duration, o.price, u.name_album, u.img from  audio_tracks_order.track as o inner join audio_tracks_order.album as u on o.album_id=u.album_id";
    private final static String GET_ALL_TRACKS_PAGING = "(SELECT o.track_id, o.album_id, o.name_track, o.duration, o.price, u.name_album, u.img from  audio_tracks_order.track as o  inner join audio_tracks_order.album as u on o.album_id=u.album_id  order by track_id) limit ?,?";
    private final static String GET_COUNT_OF_RECORD = "select count(*) from (SELECT o.track_id, o.album_id, o.name_track, o.duration, o.price, u.name_album from  audio_tracks_order.track as o inner join audio_tracks_order.album as u on o.album_id=u.album_id) as f";
    private final static String GET_COUNT_OF_CLIENT_TRACKS = "select count(*) from (SELECT r.track_id, r.album_id, r.name_track, r.duration, r.price, t.name_album, t.img from  (select track_id, album_id, name_track, duration, price \n" +
            "                                   from (SELECT r.track_id, r.album_id, r.name_track, r.duration, r.price, t.name_album from  audio_tracks_order.track as r inner join audio_tracks_order.album as t on r.album_id= t.album_id) as ooo join \n" +
            "            (select oo.track from audio_tracks_order.order_m2m_track as oo join\n" +
            "\t\t\t\t\t\t\t\t(SELECT o.order_id FROM audio_tracks_order.order AS o  \n" +
            "                                  INNER JOIN audio_tracks_order.user AS u ON o.user_id=u.user_id WHERE u.login=?)\n" +
            "                                    as aa on oo.order=aa.order_id) as aaa on ooo.track_id=aaa.track) as r inner join audio_tracks_order.album as t on r.album_id=t.album_id) as g";
    private static final String ADD_TRACK = "INSERT INTO audio_tracks_order.track (album_id, name_track, duration , price ) VALUES (?,?,?,?)";
    private static final String DELETE_TRACK = "DELETE FROM audio_tracks_order.track WHERE track_id=?";
    private static final String UPDATE_TRACK = "UPDATE audio_tracks_order.track SET album_id=?, name_track=? , duration=? , price=? WHERE track_id=?";

    public static final String  GET_CLIENT_TRACKS="(SELECT r.track_id, r.album_id, r.name_track, r.duration, r.price, t.name_album, t.img from  (select track_id, album_id, name_track, duration, price \n" +
        "                                   from (SELECT r.track_id, r.album_id, r.name_track, r.duration, r.price, t.name_album from  audio_tracks_order.track as r inner join audio_tracks_order.album as t on r.album_id= t.album_id) as ooo join \n" +
        "            (select oo.track from audio_tracks_order.order_m2m_track as oo join\n" +
        "(SELECT o.order_id FROM audio_tracks_order.order AS o  \n" +
        "                                  INNER JOIN audio_tracks_order.user AS u ON o.user_id=u.user_id WHERE u.login=?)\n" +
        "                                    as aa on oo.order=aa.order_id) as aaa on ooo.track_id=aaa.track) as r inner join audio_tracks_order.album as t on r.album_id=t.album_id) limit ?,?";
    private static final String WHERE_CONDITION = " WHERE track_id= ?";


    public List<Track> getClientTracksByLogin(String login,int startIndex,int totalCount){
        ConnectionPool connectionPool = null;
        PreparedStatement statement = null;
        Connection connection = null;
        List<Track> tracks=null;
        try{

            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getFreeConnection();
            statement=connection.prepareStatement(GET_CLIENT_TRACKS);
            statement.setString(1,login);
            statement.setInt(2,startIndex);
            statement.setInt(3,totalCount);
            ResultSet resultSet= statement.executeQuery();

            tracks= makeEntities(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connectionPool,statement,connection);
        }
        return tracks;
    }


    public int getCountOfRecordsUserTracks(String login) throws DAOException {

        ConnectionPool connectionPool = null;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try{

            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getFreeConnection();
            preparedStatement = connection.prepareStatement(GET_COUNT_OF_CLIENT_TRACKS);
            preparedStatement.setString(1,login);
            ResultSet rs = preparedStatement.executeQuery();

            int countRecords=0;
            while (rs.next()) {
                countRecords=rs.getInt(1);
            }
            return countRecords;

        } catch (SQLException e) {
            throw new DAOException("Error while getting count of  user tracks from table "+e);
        } finally {
            closeConnection(connectionPool, preparedStatement, connection);
        }
    }


    @Override
    public String getQueryForSelect() {
        return GET_ALL_TRACKS;
    }

    @Override
    protected String getQueryForSelectPaging() {
        return GET_ALL_TRACKS_PAGING;
    }

    @Override
    public String getQueryForAdd() {
        return ADD_TRACK;
    }

    @Override
    public String getQueryForUpdate() {
        return UPDATE_TRACK;
    }

    @Override
    protected String getWhereQuery() {
        return WHERE_CONDITION;
    }

    @Override
    protected String getQueryForCountRecords() {
        return GET_COUNT_OF_RECORD;
    }

    @Override
    public String getQueryForDelete() {
        return DELETE_TRACK;
    }

    @Override
    protected List<Track> makeEntities(ResultSet rs) throws DAOException {
        List<Track> tracks=new ArrayList<Track>();

        try {
            while (rs.next()) {
                makeEntity(rs, tracks);
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

        return  tracks;
    }

    private void makeEntity(ResultSet rs, List<Track> tracks) throws SQLException {
        Track track = makeTrack(rs);
        tracks.add(track);
    }

    private Track makeTrack(ResultSet rs) throws SQLException {
        Track track=new Track();
        int trackId = rs.getInt(ParameterEnum.TRACK_ID.toString());
        track.setId(trackId);
        int albumId = rs.getInt(ParameterEnum.ALBUM_ID.toString());
        track.setIdAlbum(albumId);
        int duration = rs.getInt(ParameterEnum.DURATION.toString());
        track.setDuration(duration);
        double price = rs.getDouble(ParameterEnum.PRICE.toString());
        track.setPrice(price);
        String nameTrack = rs.getString(ParameterEnum.NAME_TRACK.toString());
        track.setNameTrack(nameTrack);
        String nameAlbum = rs.getString(ParameterEnum.NAME_ALBUM.toString());
        track.setNameAlbum(nameAlbum);
        int img = rs.getInt(ParameterEnum.IMG.toString());
        track.setImageNumber(img);
        return track;
    }

    @Override
    protected void prepareStatementForAdding(PreparedStatement statement, Track entity) throws DAOException {
        try {
            fillStatement(statement,entity);
        } catch (SQLException e) {
            throw new DAOException("Cannot prepare statement for adding track",e);
        }
    }

    @Override
    protected void prepareStatementForUpdating(PreparedStatement statement, Track entity) throws DAOException {
        try {
            fillStatement(statement,entity);
            statement.setInt(5,entity.getId());
        } catch (SQLException e) {
            throw new DAOException("Cannot prepare statement for updating track",e);
        }
    }

    private void fillStatement(PreparedStatement statement, Track entity) throws SQLException {

        int idAlbum = entity.getIdAlbum();
        statement.setInt(1, idAlbum);
        String nameTrack = entity.getNameTrack();
        statement.setString(2, nameTrack);
        int duration = entity.getDuration();
        statement.setInt(3, duration);
        double price = entity.getPrice();
        statement.setDouble(4, price);



    }
}

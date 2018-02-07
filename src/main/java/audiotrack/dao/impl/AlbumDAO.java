package audiotrack.dao.impl;

import audiotrack.bean.Album;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.dao.AbstractDAO;
import audiotrack.dao.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO extends AbstractDAO<Album,Integer> {

    private final static String GET_ALL_ALBUMS = "SELECT album.album_id, album.singer_id, album.name_album" + " FROM audio_tracks_order.album";
    private final static String GET_ALL_ALBUMS_PAGING = "select * from audio_tracks_order.album limit ?,? ";
    public static final String DELETE_ALBUM="DELETE FROM audio_tracks_order.album WHERE album_id=? ";
    public static final String ADD_ALBUM="INSERT INTO audio_tracks_order.album ( singer_id, name_album) VALUES ( ?, ?)";
    private static final String UPDATE_ALBUM = "UPDATE audio_tracks_order.album SET singer_id=?, name_album=?  WHERE album_id=?";

    private static final String WHERE_CONDITION = " WHERE album_id = ?";

    @Override
    public String getQueryForSelect() {
        return GET_ALL_ALBUMS;
    }

    @Override
    protected String getQueryForSelectPaging() {
        return GET_ALL_ALBUMS_PAGING;
    }

    @Override
    public String getQueryForAdd() {
        return ADD_ALBUM;
    }

    @Override
    public String getQueryForUpdate() {
        return UPDATE_ALBUM;
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
        return DELETE_ALBUM;
    }



    @Override
    protected List<Album> makeEntities(ResultSet rs) throws DAOException {
        List<Album> albums=new ArrayList<Album>();

        try {
            while (rs.next()) {
                makeEntity(rs, albums);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot make entities ALBUM",e);
        }

        return  albums;
    }

    private void makeEntity(ResultSet rs, List<Album> albums) throws SQLException {
        Album album = makeAlbum(rs);
        albums.add(album);
    }

    private Album makeAlbum(ResultSet rs) throws SQLException {
        Album album=new Album();

        int album_id = rs.getInt(ParameterEnum.ALBUM_ID.toString());
        album.setId(album_id);
        int singer_id = rs.getInt(ParameterEnum.SINGER_ID.toString());
        album.setIdSinger(singer_id);
        String nameAlbum = rs.getString(ParameterEnum.NAME_ALBUM.toString());
        album.setNameAlbum(nameAlbum);
        return album;
    }

    @Override
    protected void prepareStatementForAdding(PreparedStatement statement, Album entity) throws DAOException {
        try {
            fillStatement(statement, entity);
        } catch (SQLException e) {
            throw new DAOException("Cannot prepare statement for adding ALBUM ",e);
        }
    }

    private void fillStatement(PreparedStatement statement, Album entity) throws SQLException {
        int idSinger = entity.getIdSinger();
        statement.setInt(1, idSinger);
        String nameAlbum = entity.getNameAlbum();
        statement.setString(2, nameAlbum);

    }

    @Override
    protected void prepareStatementForUpdating(PreparedStatement statement, Album entity) throws DAOException {
        try {
            prepareStatement(statement, entity);
        } catch (SQLException e) {
            throw new DAOException("Cannot prepare statement for updating ALBUM",e);
        }

    }

    private void prepareStatement(PreparedStatement statement, Album entity) throws SQLException {
        fillStatement(statement, entity);
        Integer id = entity.getId();
        statement.setInt(3, id);
    }

}

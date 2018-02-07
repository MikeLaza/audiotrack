package audiotrack.dao.impl;


import audiotrack.bean.Playlist;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.dao.AbstractDAO;
import audiotrack.dao.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO extends AbstractDAO<Playlist, Integer> {

    private final static String GET_ALL_PLAYLISTS = "SELECT playlist_id, name_playlist FROM audio_tracks_order.playlist";
    private final static String GET_ALL_PLAYLISTS_PAGING = "select * from audio_tracks_order.playlist limit ?,? ";
    private static final String ADD_PLAYLIST = "INSERT INTO audio_tracks_order.playlist ( name_playlist) VALUES (?);";
    private static final String DELETE_PLAYLIST = "DELETE FROM audio_tracks_order.playlist WHERE playlist_id=?";
    private static final String UPDATE_PLAYLIST = "UPDATE audio_tracks_order.playlist SET name_playlist=? WHERE playlist_id=?";
    private static final String WHERE_CONDITION = " WHERE playlist_id = ?";
    @Override
    public String getQueryForSelect() {
        return GET_ALL_PLAYLISTS;
    }

    @Override
    protected String getQueryForSelectPaging() {
        return GET_ALL_PLAYLISTS_PAGING;
    }

    @Override
    public String getQueryForAdd() {
        return ADD_PLAYLIST;
    }

    @Override
    public String getQueryForUpdate() {
        return UPDATE_PLAYLIST;
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
        return DELETE_PLAYLIST;
    }

    @Override
    protected List<Playlist> makeEntities(ResultSet rs) throws DAOException {
        List<Playlist> playlists=new ArrayList<Playlist>();

        try {
            while (rs.next()) {
                makeEntity(rs, playlists);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot make entities for playlist",e);
        }

        return playlists;
    }

    private void makeEntity(ResultSet rs, List<Playlist> playlists) throws SQLException {
        Playlist playlist = makePlaylist(rs);
        playlists.add(playlist);
    }

    private Playlist makePlaylist(ResultSet rs) throws SQLException {
        Playlist playlist= new Playlist();

        final int playlistId = rs.getInt(ParameterEnum.PLAYLIST_ID.toString());
        playlist.setId(playlistId);
        final String namePlaylist = rs.getString(ParameterEnum.NAME_PLAYLIST.toString());
        playlist.setNamePlaylist(namePlaylist);

        return playlist;
    }

    @Override
    protected void prepareStatementForAdding(PreparedStatement statement, Playlist entity) throws DAOException {
        try {
            fillStatement(statement,entity);
        } catch (SQLException e) {
            throw new DAOException("Cannot prepare statement for adding playlist",e);
        }
    }

    @Override
    protected void prepareStatementForUpdating(PreparedStatement statement, Playlist entity) throws DAOException {
        try {
            fillStatement(statement,entity);
            Integer id = entity.getId();
            statement.setInt(2, id);
        } catch (SQLException e) {
            throw new DAOException("Cannot prepare statement for updating playlist",e);
        }
    }


    private void fillStatement(PreparedStatement statement, Playlist entity) throws SQLException {
        String namePlaylist = entity.getNamePlaylist();
        statement.setString(1, namePlaylist);

    }
}

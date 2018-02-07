package audiotrack.dao.impl;

import audiotrack.bean.Singer;
import audiotrack.controller.command.impl.util.ParameterEnum;
import audiotrack.dao.AbstractDAO;
import audiotrack.dao.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class SingerDAO extends AbstractDAO<Singer,Integer> {


    private final static String GET_ALL_SINGERS = "SELECT singer_id, first_name, last_name FROM audio_tracks_order.singer";
    private final static String GET_ALL_SINGERS_PAGING = "select * from audio_tracks_order.singer limit ?,? ";
    private static final String ADD_SINGER = "INSERT INTO audio_tracks_order.singer (first_name, last_name) VALUES (?,?);";
    private static final String DELETE_SINGER = "DELETE FROM audio_tracks_order.singer WHERE singer_id=?";
    private static final String UPDATE_SINGER = "UPDATE audio_tracks_order.singer SET first_name=?, last_name=? WHERE singer_id=?";
    private static final String WHERE_CONDITION = " WHERE singer_id = ?";


    @Override
    public String getQueryForSelect() {
        return GET_ALL_SINGERS;
    }

    @Override
    protected String getQueryForSelectPaging() {
        return GET_ALL_SINGERS_PAGING;
    }

    @Override
    public String getQueryForAdd() {
        return ADD_SINGER;
    }

    @Override
    public String getQueryForUpdate() {
        return UPDATE_SINGER;
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
        return DELETE_SINGER;
    }


    @Override
    protected List<Singer> makeEntities(ResultSet rs) throws DAOException {
        List<Singer> singers=new ArrayList<Singer>();

        try {
            while (rs.next()) {
                makeEntity(rs, singers);
            }
        } catch (SQLException e) {
            throw new DAOException("Cannot make entities for singers",e);
        }

        return  singers;
    }

    private void makeEntity(ResultSet rs, List<Singer> singers) throws SQLException {
        Singer singer = makeSinger(rs);
        singers.add(singer);
    }

    private Singer makeSinger(ResultSet rs) throws SQLException {

        Singer singer =new Singer();
        int singerId = rs.getInt(ParameterEnum.SINGER_ID.toString());
        singer.setId(singerId);
        String firstName = rs.getString(ParameterEnum.FIRST_NAME.toString());
        singer.setFirstName(firstName);
        String lastName = rs.getString(ParameterEnum.LAST_NAME.toString());
        singer.setLastName(lastName);
        return singer;
    }

    @Override
    protected void prepareStatementForAdding(PreparedStatement statement, Singer entity) throws DAOException {
        try {
            fillStatement(statement,entity);
        } catch (SQLException e) {
            throw new DAOException("Cannot prepare statement for adding singer",e);
        }
    }

    @Override
    protected void prepareStatementForUpdating(PreparedStatement statement, Singer entity) throws DAOException {
        try {
            fillStatement(statement,entity);
            Integer id = entity.getId();
            statement.setInt(3, id);
        } catch (SQLException e) {
            throw new DAOException("Cannot prepare statement for updating singer",e);
        }
    }

    private void fillStatement(PreparedStatement statement, Singer entity) throws SQLException {
        String firstName = entity.getFirstName();
        statement.setString(1, firstName);
        String lastName = entity.getLastName();
        statement.setString(2, lastName);


    }
}

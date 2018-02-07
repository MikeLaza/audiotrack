package audiotrack.service.impl;

import audiotrack.bean.Album;
import audiotrack.bean.Track;
import audiotrack.dao.exception.DAOException;
import audiotrack.dao.factory.DaoFactory;
import audiotrack.dao.impl.AlbumDAO;
import audiotrack.dao.impl.TrackDAO;
import audiotrack.service.TrackService;
import audiotrack.service.exception.ServiceException;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class TrackServiceImpl implements TrackService {
    private static final TrackServiceImpl instance = new TrackServiceImpl();
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger logger = Logger.getLogger(TrackServiceImpl.class);


    private TrackServiceImpl(){

    }
    public static TrackServiceImpl getInstance() {
        return instance;
    }
    @Override
    public boolean addTrack(int albumId,String nameTrack,int duration,double price) throws ServiceException {
        boolean isValid=true;
        try {
            AlbumDAO albumDAO=daoFactory.getAlbumDAO();
            boolean isExistAlbum=albumDAO.isExistEntity(albumId);
            boolean isValidDuration=isValidDuration(duration);
            boolean isValidPrice=isValidPrice(price);

            if(isExistAlbum && isValidDuration && isValidPrice){
                add(albumId, nameTrack, duration, price);

            }else {
                isValid=false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isValid;
    }



    private boolean isValidDuration(int duration){
        boolean isValid=true;
        if(duration<=0){
            isValid=false;
        }
        return isValid;
    }
    private boolean isValidPrice(double price){
        boolean isValid=true;
        if(price<=0){
            isValid=false;
        }
        return isValid;
    }

    private void add(int albumId,String nameTrack,int duration,double price) throws DAOException {
        TrackDAO trackDAO=daoFactory.getTrackDAO();
        Track track = new Track(albumId,nameTrack,duration,price);
        trackDAO.addEntity(track);
    }

    @Override
    public List<Track> getAllTracks() throws ServiceException {
        List<Track> tracks;
        try {
            TrackDAO trackDAO = daoFactory.getTrackDAO();
            tracks = trackDAO.getAllEntities();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return tracks;
    }

    @Override
    public List<Track> getAllTracksPaging(int startIndex, int totalCount) throws ServiceException {
        List<Track> tracks;
        try {
            TrackDAO trackDAO = daoFactory.getTrackDAO();
            tracks = trackDAO.getAllEntitiesPaging(startIndex,totalCount);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return tracks;
    }

    @Override
    public int getCountTracks() throws ServiceException {
        int count;
        try {
            TrackDAO trackDAO = daoFactory.getTrackDAO();
            count = trackDAO.getCountOfRecords();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return count;
    }

    @Override
    public int getCountUserTracks(String login) throws ServiceException {
        int count;
        try {
            TrackDAO trackDAO = daoFactory.getTrackDAO();
            count = trackDAO.getCountOfRecordsUserTracks(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return count;
    }

    @Override
    public void deleteTrack(Integer id) throws ServiceException {
        try {
            daoFactory.getTrackDAO().delete(id);
        }  catch (DAOException e) {
            throw new ServiceException(e);
        }

    }



    @Override
    public boolean updateTrack(int id, int albumId, String nameTrack, int duration, double price, String nameAlbum) throws ServiceException {
        boolean isValid=true;
        try {
            boolean isValidDuration=isValidDuration(duration);
            boolean isValidPrice=isValidPrice(price);

            if( isValidDuration && isValidPrice){
                update(id, albumId, nameTrack, duration, price, nameAlbum);
            }else {
                isValid=false;
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }  catch (SQLException e) {
            throw new ServiceException(e);
        }

        return isValid;
    }

    private void update(int id, int albumId, String nameTrack, int duration, double price, String nameAlbum) throws DAOException, SQLException {

        TrackDAO trackDAO=daoFactory.getTrackDAO();
        AlbumDAO albumDAO=daoFactory.getAlbumDAO();
        Album album= albumDAO.getById(albumId);
        album.setNameAlbum(nameAlbum);
        albumDAO.update(album);

        Track track = new Track(id,albumId,nameTrack,duration,price);
        trackDAO.update(track);
    }

    @Override
    public Track getTrackById(Integer id) throws ServiceException {
        Track track;
        try {
            track=daoFactory.getTrackDAO().getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return track;

    }

    @Override
    public List<Track> getClientTracksByLogin(String login,int startIndex,int totalCount)  throws ServiceException {
        final TrackDAO trackDAO = daoFactory.getTrackDAO();
        List<Track> tracks= trackDAO.getClientTracksByLogin(login,startIndex,totalCount);
        return tracks;
    }
}

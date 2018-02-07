package audiotrack.service;

import audiotrack.bean.Track;
import audiotrack.service.exception.ServiceException;

import java.util.List;

public interface TrackService {
    boolean addTrack(int albumId,String nameTrack,int duration,double price) throws ServiceException;
    List<Track> getAllTracks() throws ServiceException;
    List<Track> getAllTracksPaging(int startIndex, int totalCount) throws ServiceException;
    int getCountTracks() throws ServiceException;
    int getCountUserTracks(String login) throws ServiceException;
    void deleteTrack(Integer id)throws ServiceException;
    boolean updateTrack(int id, int albumId,String nameTrack,int duration,double price, String nameAlbum) throws ServiceException;
    Track getTrackById(Integer id) throws ServiceException;
    List<Track> getClientTracksByLogin(String login,int startIndex,int totalCount) throws ServiceException;
}

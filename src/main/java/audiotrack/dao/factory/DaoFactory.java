package audiotrack.dao.factory;

import audiotrack.dao.impl.*;

public class DaoFactory {
    private static DaoFactory instance=new DaoFactory();

    private AlbumDAO albumDAO=new AlbumDAO();
    private CommentDAO commentDAO= new CommentDAO();
    private OrderDAO orderDAO= new OrderDAO();
    private PlaylistDAO playlistDAO= new PlaylistDAO();
    private SingerDAO singerDAO= new SingerDAO();
    private TrackDAO trackDAO= new TrackDAO();
    private UserDAO userDAO= new UserDAO();

    private DaoFactory() {

    }

    public static DaoFactory getInstance() {
        return instance;
    }

    public AlbumDAO getAlbumDAO(){
        return albumDAO;
    }
    public CommentDAO getCommentDAO(){
        return commentDAO;
    }
    public OrderDAO getOrderDAO(){
        return orderDAO;
    }

    public PlaylistDAO getPlaylistDAO(){
        return playlistDAO;
    }
    public SingerDAO getSingerDAO(){
        return singerDAO;

    }
    public TrackDAO getTrackDAO(){
        return trackDAO;
    }

    public UserDAO getUserDAO(){
        return userDAO;
    }


}
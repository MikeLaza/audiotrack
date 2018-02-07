package audiotrack.service.factory;


import audiotrack.service.impl.*;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserServiceImpl userService = UserServiceImpl.getInstance();
    private final TrackServiceImpl trackService= TrackServiceImpl.getInstance();
    private final AlbumServiceImpl albumService= AlbumServiceImpl.getInstance();
    private final PlaylistServiceImpl playlistService= PlaylistServiceImpl.getInstance();
    private final CommentServiceImpl commentService= CommentServiceImpl.getInstance();
    private final OrderServiceImpl orderService= OrderServiceImpl.getInstance();

    private ServiceFactory() {

    }

    public AlbumServiceImpl getAlbumService() {
        return albumService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public TrackServiceImpl getTrackService() {
        return trackService;
    }

    public PlaylistServiceImpl getPlaylistService() {
        return playlistService;
    }

    public CommentServiceImpl getCommentService() {
        return commentService;
    }

    public OrderServiceImpl getOrderService() {
        return orderService;
    }
}


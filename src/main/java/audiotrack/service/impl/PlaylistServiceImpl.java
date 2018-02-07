package audiotrack.service.impl;

import audiotrack.bean.Playlist;
import audiotrack.dao.exception.DAOException;
import audiotrack.dao.factory.DaoFactory;
import audiotrack.dao.impl.PlaylistDAO;
import audiotrack.service.PlaylistService;
import audiotrack.service.exception.ServiceException;

public class PlaylistServiceImpl implements PlaylistService{
    private DaoFactory daoFactory = DaoFactory.getInstance();
    private  static PlaylistServiceImpl instance= new PlaylistServiceImpl();

    public static PlaylistServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void addPlaylist(String namePlaylist) throws ServiceException {
        try {
            add(namePlaylist);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private void add(String namePlaylist) throws DAOException {
        PlaylistDAO playlistDAO= daoFactory.getPlaylistDAO();
        Playlist playlist= new Playlist(namePlaylist);
        playlistDAO.addEntity(playlist);
    }
}

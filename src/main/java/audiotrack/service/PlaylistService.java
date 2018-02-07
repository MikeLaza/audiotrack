package audiotrack.service;

import audiotrack.service.exception.ServiceException;

public interface PlaylistService {
    void addPlaylist(String namePlaylist) throws ServiceException;
}

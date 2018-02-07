package audiotrack.service;

import audiotrack.bean.Track;
import audiotrack.bean.User;
import audiotrack.service.exception.ServiceException;

public interface OrderService  {
    void addOrder(User user, Track track) throws ServiceException;
}

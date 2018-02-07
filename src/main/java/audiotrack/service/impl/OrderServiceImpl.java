package audiotrack.service.impl;

import audiotrack.bean.Order;
import audiotrack.bean.Track;
import audiotrack.bean.User;
import audiotrack.dao.exception.DAOException;
import audiotrack.dao.factory.DaoFactory;
import audiotrack.dao.impl.OrderDAO;
import audiotrack.dao.impl.UserDAO;
import audiotrack.service.OrderService;
import audiotrack.service.exception.ServiceException;

public class OrderServiceImpl  implements OrderService{
    private final static OrderServiceImpl instance= new OrderServiceImpl();
    private DaoFactory daoFactory = DaoFactory.getInstance();


    @Override
    public void addOrder(User user, Track track) throws ServiceException {
        Integer userId = user.getId();
        Order order=new Order(userId);
        int trackId= track.getId();
        OrderDAO orderDAO= daoFactory.getOrderDAO();
        try {
            orderDAO.addEntity(order);
            orderDAO.handleOrder(trackId);
            UserDAO userDAO=daoFactory.getUserDAO();
            userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }


    public static OrderServiceImpl getInstance() {
        return instance;
    }
}

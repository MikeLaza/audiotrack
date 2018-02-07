package audiotrack.dao;

import audiotrack.dao.exception.DAOException;

import java.io.Serializable;
import java.util.List;

public interface Dao<T,PK extends Serializable> {
    void addEntity(T object) throws DAOException;
    T getById(PK key) throws DAOException;
    void update(T object) throws DAOException;
    void delete(Integer id) throws DAOException;
    List<T> getAllEntities() throws DAOException;
    List<T> getAllEntitiesPaging(int startIndex, int totalCount) throws DAOException;
    int getCountOfRecords() throws DAOException;
    boolean isExistEntity(PK id)throws DAOException;
}

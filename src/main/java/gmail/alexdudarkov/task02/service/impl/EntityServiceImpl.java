package gmail.alexdudarkov.task02.service.impl;


import gmail.alexdudarkov.task02.dao.DAOFactory;
import gmail.alexdudarkov.task02.dao.EntityDAO;
import gmail.alexdudarkov.task02.dao.exception.DAOException;
import gmail.alexdudarkov.task02.dao.model.Entity;
import gmail.alexdudarkov.task02.service.EntityService;
import gmail.alexdudarkov.task02.service.exception.ServiceException;

public class EntityServiceImpl implements EntityService {

    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final EntityDAO entityDAO = daoFactory.getEntityDAO();


    @Override
    public Entity getEntity() throws ServiceException {
        try {
            return entityDAO.getEntity();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

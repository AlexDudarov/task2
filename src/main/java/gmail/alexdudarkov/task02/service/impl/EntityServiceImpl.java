package gmail.alexdudarkov.task02.service.impl;


import gmail.alexdudarkov.task02.dao.DAOFactory;
import gmail.alexdudarkov.task02.dao.EntityDAO;
import gmail.alexdudarkov.task02.dao.model.Entity;
import gmail.alexdudarkov.task02.service.EntityService;

public class EntityServiceImpl implements EntityService{

    private final DAOFactory daoFactory = DAOFactory.getInstance();
    private final EntityDAO entityDAO = daoFactory.getEntityDAO();


    @Override
    public Entity getEntity() {
        return entityDAO.getEntity();
    }
}

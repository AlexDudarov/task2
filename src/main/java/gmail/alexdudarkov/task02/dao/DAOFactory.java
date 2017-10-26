package gmail.alexdudarkov.task02.dao;


import gmail.alexdudarkov.task02.dao.impl.EntityDAOImpl;

public final class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private final EntityDAO entityDAO = new EntityDAOImpl();

    private DAOFactory() {
    }

    public EntityDAO getEntityDAO() {
        return entityDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}

package gmail.alexdudarkov.task02.dao;

import gmail.alexdudarkov.task02.dao.exception.DAOException;
import gmail.alexdudarkov.task02.dao.model.Entity;

public interface EntityDAO {

    Entity getEntity() throws DAOException;

}

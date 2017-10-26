package gmail.alexdudarkov.task02.service;

import gmail.alexdudarkov.task02.dao.model.Entity;
import gmail.alexdudarkov.task02.service.exception.ServiceException;

public interface EntityService{

    Entity getEntity() throws ServiceException;

}

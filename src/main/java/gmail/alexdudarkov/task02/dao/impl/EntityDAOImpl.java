package gmail.alexdudarkov.task02.dao.impl;

import gmail.alexdudarkov.task02.dao.EntityDAO;
import gmail.alexdudarkov.task02.dao.exception.DAOException;
import gmail.alexdudarkov.task02.dao.model.Entity;
import gmail.alexdudarkov.task02.dao.util.MetaEntity;
import gmail.alexdudarkov.task02.dao.util.Builder;
import gmail.alexdudarkov.task02.dao.util.Parser;
import gmail.alexdudarkov.task02.dao.util.StackGenerator;

import java.util.List;

public class EntityDAOImpl implements EntityDAO {

    @Override
    public Entity getEntity() throws DAOException {

        Parser parser = new Parser();
        StackGenerator stackGenerator = new StackGenerator();
        List<MetaEntity> metaEntities = stackGenerator.createStack(parser.printFile());
        Builder builder = new Builder();

        return builder.build(metaEntities);
    }
}

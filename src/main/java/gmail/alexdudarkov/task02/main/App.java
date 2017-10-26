package gmail.alexdudarkov.task02.main;

import gmail.alexdudarkov.task02.service.EntityService;
import gmail.alexdudarkov.task02.service.ServiceFactory;
import gmail.alexdudarkov.task02.service.exception.ServiceException;

public class App {

   private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
   private static final EntityService entityService= serviceFactory.getEntityService();

    public static void main( String[] args )
    {
        Formatter formatter = new Formatter();
        try {
            System.out.println( formatter.format(entityService.getEntity()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }


    }



}

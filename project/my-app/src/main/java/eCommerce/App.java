package eCommerce;

import eCommerce.models.Admin;
import eCommerce.models.Customer;
import eCommerce.models.base.User;
import eCommerce.storage.XmlDb;
import eCommerce.ui.ConsoleUIContext;
import eCommerce.ui.InputHelper;
import eCommerce.ui.MainUIStrategy;
import eCommerce.ui.admin.AdminUIStrategy;
import eCommerce.ui.customer.CustomerUIStrategy;

public class App
{
    public static void main( String[] args )
    {
        ConsoleUIContext uiContext = new ConsoleUIContext();
        InputHelper inputHelper = new InputHelper();

        //MemoryDb db = new MemoryDb();
        XmlDb db = new XmlDb();
        try {
            db.initialize();
        } catch(Exception ex) {
            System.exit(0);
        }


        while(true) {
            MainUIStrategy mainUiStrategy = new MainUIStrategy(inputHelper, db);
            uiContext.setConsoleStrategy(mainUiStrategy);
            uiContext.runApp();

            User currentUser = mainUiStrategy.getCurrentUser();
            if(currentUser instanceof Customer) {
                uiContext.setConsoleStrategy(new CustomerUIStrategy(inputHelper, db, currentUser));
            } else if (currentUser instanceof Admin) {
                uiContext.setConsoleStrategy(new AdminUIStrategy(inputHelper, db, currentUser));
            } else {
                throw new IllegalArgumentException("User type is not supported");
            }

            uiContext.runApp();
        }
    }
}

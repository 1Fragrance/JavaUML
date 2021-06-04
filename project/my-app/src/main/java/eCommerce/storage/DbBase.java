package eCommerce.storage;

import eCommerce.storage.repositories.interfaces.IDb;
import eCommerce.storage.repositories.interfaces.*;

public abstract class DbBase implements IDb {
    protected IAdminRepository admins;
    protected IAddressRepository addresses;
    protected IBillRepository bills;
    protected ICustomerRepository customers;
    protected IItemRepository items;
    protected IOrderRepository orders;
    protected IProductRepository products;
    protected IShoppingCartRepository shoppingCards;


    public IAdminRepository getAdmins() {
        return admins;
    }

    public IAddressRepository getAddresses() {
        return addresses;
    }

    public IBillRepository getBills() {
        return bills;
    }

    public ICustomerRepository getCustomers() {
        return customers;
    }

    public IItemRepository getItems() {
        return items;
    }

    public IOrderRepository getOrders() {
        return orders;
    }

    public IProductRepository getProducts() {
        return products;
    }

    public IShoppingCartRepository getShoppingCards() {
        return shoppingCards;
    }
}

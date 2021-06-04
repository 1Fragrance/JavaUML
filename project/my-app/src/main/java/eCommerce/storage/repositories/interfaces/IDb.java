package eCommerce.storage.repositories.interfaces;

import eCommerce.storage.repositories.interfaces.*;
import org.xml.sax.SAXException;

import java.io.IOException;

public interface IDb {
    public IAdminRepository getAdmins();
    public IAddressRepository getAddresses();
    public IBillRepository getBills();
    public ICustomerRepository getCustomers();
    public IItemRepository getItems();
    public IOrderRepository getOrders();
    public IProductRepository getProducts();
    public IShoppingCartRepository getShoppingCards();

    public void initialize() throws IOException, SAXException;
}

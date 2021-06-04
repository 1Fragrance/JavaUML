package eCommerce.storage;

import eCommerce.Constants;
import eCommerce.enums.CustomerStatus;
import eCommerce.models.*;
import eCommerce.storage.repositories.memory.*;

public class MemoryDb extends DbBase {

    public MemoryDb()
    {
        admins = new AdminRepository();
        customers = new CustomerRepository();
        addresses = new AddressRepository();
        shoppingCards = new ShoppingCartRepository();
        products = new ProductRepository();

        bills = new BillRepository();
        items = new ItemRepository();
        orders = new OrderRepository();
    }

    public void initialize()
    {
        Admin admin = new Admin(Constants.Users.SUPER_ADMIN_ID,"Super", "Admin", "SuperAdmin@gmail.com", "admin", "admin", "+375293225566");
        admins.insert(admin);

        Customer customer1 = new Customer("John", "Dory", "johnDory@gmail.com", "johnDory", "123", "+375293221122", CustomerStatus.active);
        Customer customer2 = new Customer("Ivan", "Dorn", "ivanDorn@gmail.com", "ivanDorm", "123", "+375293222233", CustomerStatus.active);
        Customer customer3 = new Customer("Stas", "Galchinskiy", "stazeg@gmail.com", "stazeg", "123", "+375293223344", CustomerStatus.blocked);
        customers.insert(customer1);
        customers.insert(customer2);
        customers.insert(customer3);

        Address address1 = new Address(customer1.getId(), "1", "Minsk", "Belarus", "1", "Rokosovskogo");
        Address address2 = new Address(customer1.getId(), "2", "Minsk", "Belarus", "2", "Shpilevskogo");
        Address address3 = new Address(customer2.getId(), "3", "Minsk", "Belarus", "3", "Gashkevicha");
        Address address4 = new Address(customer3.getId(), "4", "Minsk", "Belarus", "4", "Bogdanovicha");
        addresses.insert(address1);
        addresses.insert(address2);
        addresses.insert(address3);
        addresses.insert(address4);

        ShoppingCart cart1 = new ShoppingCart(customer1.getId());
        shoppingCards.insert(cart1);
        shoppingCards.insert(new ShoppingCart(customer2.getId()));
        shoppingCards.insert(new ShoppingCart(customer3.getId()));

        Product product1 = new Product("IPhone 11", "Мобильный телефон от Apple", "Apple", 1700);
        Product product2 = new Product("Xiaomi Mi 11", "Мобильный телефон от Xiaomi", "Xiaomi", 940);
        Product product3 = new Product("Samsung Galaxy A52", "Мобильный телефон от Samsung", "Samsung", 860);
        Product product4 = new Product("Apple iPhone 12 Pro", "Дорогой мобильный телефон от Apple", "Apple", 2880);
        Product product5 = new Product("Huawei P40", "Мобильный телефон от Huawei", "Huawei", 600);
        products.insert(product1);
        products.insert(product2);
        products.insert(product3);
        products.insert(product4);
        products.insert(product5);

        // NOTE: Closed order
        /*Order order1 = new Order(new Date(), 2640, null, admin.getId(), address1.getId(), OrderStatus.closed, customer1.getId());
        Bill bill1 = new Bill(new Date(), 500, order1.getId());
        order1.setBillId(bill1.getId());
        Item item1 = new Item(1, product1.getId(), null, order1.getId());
        Item item2 = new Item(2, product2.getId(), null, order1.getId());
        orders.insert(order1);
        bills.insert(bill1);
        items.insert(item1);
        items.insert(item2);

        // NOTE: Open order
        Order order2 = new Order(new Date(), 3000, null, admin.getId(), address1.getId(), OrderStatus.created, customer1.getId());
        Item item3 = new Item(3, product3.getId(), null, order2.getId());
        orders.insert(order2);
        items.insert(item3);


        // NOTE: Cart items
        Item item4 = new Item(4, product4.getId(), cart1.getId(), null);
        Item item5 = new Item(5, product5.getId(), cart1.getId(), null);
        items.insert(item4);
        items.insert(item5);*/
    }
}

package eCommerce.ui;

import eCommerce.interfaces.IConsoleUIStrategy;
import eCommerce.storage.repositories.interfaces.IDb;
import eCommerce.models.*;
import eCommerce.models.base.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class ConsoleUIStrategyBase implements IConsoleUIStrategy {
    protected InputHelper inputHelper;
    protected IDb db;
    protected User currentUser;

    public ConsoleUIStrategyBase(InputHelper inputHelper, IDb db) {
        this.inputHelper = inputHelper;
        this.db = db;
    }

    public ConsoleUIStrategyBase(InputHelper inputHelper, IDb db, User currentUser) {
        this.inputHelper = inputHelper;
        this.db = db;
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    // NOTE: It shouldn't be there. If a logic will extend I would move it to separate base class.
    protected void printOrders(List<Order> orders) {

        for (int i = 1; i <= orders.size(); i++) {
            Order order = orders.get(i - 1);
            System.out.println(i + ". " + order.getString());

            Optional<Admin> responsibleAdmin = db.getAdmins().getById(order.getResponsibleAdminId());
            responsibleAdmin.ifPresent(admin -> System.out.println("Ответственный специалист: " + admin.getString()));

            Optional<Address> address = db.getAddresses().getById(order.getAddressId());
            address.ifPresent(value -> System.out.println("Адрес: " + value.getString()));

            Optional<Bill> bill = db.getBills().getById(order.getBillId());
            if(bill.isPresent())  {
                System.out.println("Данные об оплате: " + bill.get().getString());
            } else {
                System.out.println("Заказ не оплачен");
            };

            List<Item> orderItems = db.getItems().getByOrderId(order.getId());
            System.out.println("Заказанные товары:");
            for(Item item : orderItems) {
                Optional<Product> product = db.getProducts().getById(item.getProductId());
                product.ifPresent(value -> System.out.println(item.getStringWithProduct(value)));
            }
        }
    }
}

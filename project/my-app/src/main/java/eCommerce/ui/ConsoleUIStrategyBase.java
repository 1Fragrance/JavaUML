package eCommerce.ui;

import eCommerce.interfaces.IConsoleUIStrategy;
import eCommerce.models.*;
import eCommerce.models.base.User;
import eCommerce.storage.MemoryDb;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class ConsoleUIStrategyBase implements IConsoleUIStrategy {
    protected InputHelper inputHelper;
    protected MemoryDb db;
    protected User currentUser;

    public ConsoleUIStrategyBase(InputHelper inputHelper, MemoryDb db) {
        this.inputHelper = inputHelper;
        this.db = db;
    }

    public ConsoleUIStrategyBase(InputHelper inputHelper, MemoryDb db, User currentUser) {
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

            Optional<Admin> responsibleAdmin = db.getAdmins().stream().filter(x -> x.getId() == order.getResponsibleAdminId()).findFirst();
            responsibleAdmin.ifPresent(admin -> System.out.println("Ответственный специалист: " + admin.getString()));

            Optional<Address> address = db.getAddresses().stream().filter(x -> x.getId() == order.getAddressId()).findFirst();
            address.ifPresent(value -> System.out.println("Адрес: " + value.getString()));

            Optional<Bill> bill = db.getBills().stream().filter(x -> x.getId() == order.getBillId()).findFirst();
            if(bill.isPresent())  {
                System.out.println("Данные об оплате: " + bill.get().getString());
            } else {
                System.out.println("Заказ не оплачен");
            };

            List<Item> orderItems = db.getItems().stream().filter(x -> x.getOrderId() == order.getId()).collect(Collectors.toList());
            System.out.println("Заказанные товары:");
            for(Item item : orderItems) {
                Optional<Product> product = db.getProducts().stream().filter(x -> x.getId() == item.getProductId()).findFirst();
                product.ifPresent(value -> System.out.println(item.getStringWithProduct(value)));
            }
        }
    }
}

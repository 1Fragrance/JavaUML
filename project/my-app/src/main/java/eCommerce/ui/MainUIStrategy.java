package eCommerce.ui;

import eCommerce.Constants;
import eCommerce.enums.CustomerStatus;
import eCommerce.models.Admin;
import eCommerce.models.Customer;
import eCommerce.models.ShoppingCart;
import eCommerce.models.base.User;
import eCommerce.storage.MemoryDb;

import java.util.Optional;

public class MainUIStrategy extends ConsoleUIStrategyBase {

    public MainUIStrategy(InputHelper inputHelper, MemoryDb db) {
        super(inputHelper, db);
    }

    @Override
    public void printUI()
    {
        while (true) {
            System.out.println("\nМеню входа:");
            System.out.println("1. Войти в систему как пользователь");
            System.out.println("2. Войти в систему как администратор");
            System.out.println("3. Регистрация");
            System.out.println("0. Выход");

            int choice = inputHelper.readInt(0, 3);

            User user = null;
            switch (choice) {
                case Constants.Navigation.LOGIN_AS_USER_CHOICE: {
                    user = handleLoginAsUser();
                    break;
                }
                case Constants.Navigation.LOGIN_AS_ADMIN_CHOICE: {
                    user = handleLoginAsAdmin();
                    break;
                }
                case Constants.Navigation.REGISTER_CHOICE: {
                    user = handleRegistration();
                    break;
                }
                case Constants.Navigation.EXIT_CHOICE: {
                    System.exit(0);
                }
            }

            if(user != null) {
                currentUser = user;
                return;
            }
        }
    }

    private Customer handleLoginAsUser() {

        System.out.println("\nВход в систему");
        System.out.println("Логин:");
        String login = inputHelper.readString();
        System.out.println("Пароль:");
        String password = inputHelper.readString();

        Optional<Customer> customer = db.getCustomers().stream().filter(x -> x.getLogin().equals(login) && x.getPassword().equals(password)).findFirst();
        if(customer.isPresent()) {
            return customer.get();
        }

        System.out.println("Неправильный логин или пароль");
        return null;
    }

    // todo: template method?
    private Admin handleLoginAsAdmin() {
        System.out.println("\nВход в систему");
        System.out.println("Логин:");
        String login = inputHelper.readString();
        System.out.println("Пароль:");
        String password = inputHelper.readString();

        Optional<Admin> admin = db.getAdmins().stream().filter(x -> x.getLogin().equals(login) && x.getPassword().equals(password)).findFirst();
        if(admin.isPresent()) {
            return admin.get();
        }

        System.out.println("\nНеправильный логин или пароль");
        return null;
    }

    private Customer handleRegistration() {
        System.out.println("\nРегистрация");
        System.out.println("Введите логин:");
        String login = inputHelper.readString();
        if(db.getCustomers().stream().anyMatch(x -> x.getLogin().equals(login))) {
            System.out.println("Такой логин уже существует");;
        };

        System.out.println("Введите пароль:");
        String password = inputHelper.readString();

        System.out.println("Введите имя:");
        String firstName = inputHelper.readString();

        System.out.println("Введите фамилию:");
        String lastName = inputHelper.readString();

        // TODO: regex validation
        System.out.println("Введите email:");
        String email = inputHelper.readString();

        // TODO: regex validation
        System.out.println("Введите телефон:");
        String phone = inputHelper.readString();


        Customer newCustomer = new Customer(firstName, lastName, email, login, password, phone, CustomerStatus.active);
        db.getShoppingCards().add(new ShoppingCart(newCustomer.getId()));
        db.getCustomers().add(newCustomer);

        return newCustomer;
    }
}


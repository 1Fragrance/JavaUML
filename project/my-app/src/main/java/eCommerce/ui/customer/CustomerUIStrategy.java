package eCommerce.ui.customer;

import eCommerce.Constants;
import eCommerce.enums.OrderStatus;
import eCommerce.models.*;
import eCommerce.models.base.User;
import eCommerce.storage.MemoryDb;
import eCommerce.ui.ConsoleUIStrategyBase;
import eCommerce.ui.InputHelper;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static eCommerce.Constants.Navigation.*;

public class CustomerUIStrategy extends ConsoleUIStrategyBase {

    private ShoppingCart currentUserCart ;

    public CustomerUIStrategy(InputHelper inputHelper, MemoryDb db, User currentUser) {
        super(inputHelper, db, currentUser);
    }

    private void CheckIsCartExist() {
        Optional<ShoppingCart> cart = db.getShoppingCards().stream().filter(x -> x.getCustomerId() == currentUser.getId()).findFirst();
        if(!cart.isPresent()) {
            currentUserCart = new ShoppingCart(currentUser.getId());
            db.getShoppingCards().add(currentUserCart);
        } else {
            currentUserCart = cart.get();
        }
    }

    private void CheckIsCartHasItems() {
        if (currentUserCart.getHasItems()) {
            System.out.println("\nВ корзине уже есть добавленные продукты:");
            List<Item> orderItems = db.getItems().stream().filter(x -> x.getCartId() == currentUserCart.getId()).collect(Collectors.toList());
            for (Item item : orderItems) {
                Optional<Product> product = db.getProducts().stream().filter(x -> x.getId() == item.getProductId()).findFirst();
                product.ifPresent(value -> System.out.println(item.getStringWithProduct(value)));
            }
            System.out.println("1. Продолжить покупку");
            System.out.println("2. Очистить корзину");
            int cartEmptyChoice = inputHelper.readInt(CUSTOMER_NOT_EMPTY_CART, CUSTOMER_EMPTY_CART);
            if(cartEmptyChoice == CUSTOMER_EMPTY_CART) {
                List<Item> cartItems = db.getItems().stream().filter(x -> x.getCartId() == currentUserCart.getId()).collect(Collectors.toList());
                db.getItems().removeAll(cartItems);
                currentUserCart.setHasItems(false);

                System.out.println("Корзина была очищена");
                inputHelper.pressAnyKeyToContinue();
            }
        }
    }

    private Address AddNewAddress() {
        System.out.println("Введите страну:");
        String country = inputHelper.readString();

        System.out.println("Введите город:");
        String city = inputHelper.readString();

        System.out.println("Введите улицу:");
        String street = inputHelper.readString();

        System.out.println("Введите номер дома:");
        String houseNumber = inputHelper.readString();

        System.out.println("Введите квартиру");
        String apartmentsNumber = inputHelper.readString();

        Address orderAddress = new Address(currentUser.getId(), apartmentsNumber, city, country, houseNumber, street);
        db.getAddresses().add(orderAddress);

        return orderAddress;
    }

    private void HandleConfirmOrder() {
        if (currentUserCart.getHasItems()) {
            System.out.println("1. Использовать уже существующий адрес");
            System.out.println("2. Добавить новый");

            Address orderAddress = null;
            int addressInput = inputHelper.readInt(CUSTOMER_EXIST_ADDRESS, CUSTOMER_NEW_ADDRESS);
            if(addressInput == CUSTOMER_EXIST_ADDRESS) {
                List<Address> userAddresses = db.getAddresses().stream().filter(x -> x.getUserId() == currentUser.getId()).collect(Collectors.toList());

                if(userAddresses.size() == 0) {
                    System.out.println("У Вас нету добавленных адресов, перенаправление на страницу добавления адреса");
                    orderAddress = AddNewAddress();
                } else {
                    for(int i = 1; i <= userAddresses.size(); i++) {
                        Address address = userAddresses.get(i - 1);
                        System.out.println(i + ". " + address.getString());
                    }

                    System.out.println("Введите номер адреса");
                    int existingAddressIndex = inputHelper.readInt(1, userAddresses.size());
                    orderAddress = userAddresses.get(existingAddressIndex - 1);
                }
            } else if(addressInput == CUSTOMER_NEW_ADDRESS) {
                orderAddress = AddNewAddress();
            }

            List<Item> orderItems = db.getItems().stream().filter(x -> x.getCartId() == currentUserCart.getId()).collect(Collectors.toList());
            double orderSum = 0;
            for (Item item : orderItems) {
                Optional<Product> product = db.getProducts().stream().filter(x -> x.getId() == item.getProductId()).findFirst();
                if(!product.isPresent()) {
                    continue;
                }
                orderSum = orderSum + item.getQuantity() * product.get().getPrice();
            }

            Order newOrder = new Order(new Date(), orderSum, null, Constants.Users.SUPER_ADMIN_ID, orderAddress.getId(), OrderStatus.created, currentUser.getId());
            db.getOrders().add(newOrder);

            for(Item item : orderItems) {
                item.setCartId(null);
                item.setOrderId(newOrder.getId());
            }
            currentUserCart.setHasItems(false);

            System.out.println("\nЗаказ был успешно добавлен в систему. Ждите звонок оператора. Сумма заказа: " + orderSum);
            return;
        } else {
            System.out.println("\nВ корзине нету добавленных продуктов");
        }
    }

    private void AddItemToCart(int selectedIndex) {
        Product selectedProduct = db.getProducts().get(selectedIndex - 1);

        System.out.println("Введите количество: ");
        int countInput = inputHelper.readInt(null, null);

        Item newItem = new Item(countInput, selectedProduct.getId(), currentUserCart.getId(), null);
        db.getItems().add(newItem);
        currentUserCart.setHasItems(true);
        System.out.println("Товар был добавлен в корзину");
    }

    private void HandleShowProducts() {
        CheckIsCartHasItems();
        List<Product> products = db.getProducts();
        for (int i = 1; i <= products.size(); i++) {
            Product product = products.get(i - 1);
            System.out.println(i + ". " + product.getString());
        }
        while (true) {
        System.out.println("\nВведите номер товара для того, чтобы добавить в корзину");
        System.out.println("100. Подтвердить заказ");
        System.out.println("0.  Назад");
            int addToCartChoice = inputHelper.readInt(0, CUSTOMER_CONFIRM_ORDER_CHOICE);
            if (addToCartChoice == CUSTOMER_CONFIRM_ORDER_CHOICE) {
                HandleConfirmOrder();
                break;
            } else if (addToCartChoice == EXIT_CHOICE) {
                break;
            } else {
                AddItemToCart(addToCartChoice);
            }
        }
    }

    private void HandleShowOrderHistory() {
        List<Order> userOrders = db.getOrders().stream().filter(x -> x.getAuthorId() == currentUser.getId()).collect(Collectors.toList());
        printOrders(userOrders);

        inputHelper.pressAnyKeyToContinue();
    }

    @Override
    public void printUI() {

        CheckIsCartExist();
        boolean exitFlag = false;
        while(!exitFlag) {
            System.out.println("\nДобро пожаловать в интернет магазин техники:");
            System.out.println("1. Просмотр каталога");
            System.out.println("2. Просмотр своих заказов");
            System.out.println("0. Выход из системы");

            int choice = inputHelper.readInt(0, 2);
            InputHelper.clearScreen();
            switch (choice) {
                case CUSTOMER_SHOW_PRODUCTS_CHOICE: {
                    HandleShowProducts();
                    break;
                }

                case CUSTOMER_SHOW_ORDER_HISTORY_CHOICE: {
                    HandleShowOrderHistory();
                    break;
                }

                case Constants.Navigation.EXIT_CHOICE: {
                    exitFlag = true;
                    break;
                }
            }
        }
    }
}

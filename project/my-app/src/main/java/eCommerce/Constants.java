package eCommerce;

import java.util.UUID;

public class Constants {
    public static class Navigation
    {
        public final static int EXIT_CHOICE = 0;

        public final static int LOGIN_AS_USER_CHOICE = 1;
        public final static int LOGIN_AS_ADMIN_CHOICE = 2;
        public final static int REGISTER_CHOICE = 3;

        public final static int ADMIN_USER_MANAGEMENT_CHOICE = 1;
        public final static int ADMIN_ORDERS_MANAGEMENT_CHOICE = 2;

        public final static int ADMIN_ORDERS_MANAGEMENT_CLOSE = 1;
        public final static int ADMIN_ORDERS_MANAGEMENT_CANCEL = 2;

        public final static int CUSTOMER_SHOW_PRODUCTS_CHOICE = 1;
        public final static int CUSTOMER_SHOW_ORDER_HISTORY_CHOICE = 2;

        public final static int CUSTOMER_CONFIRM_ORDER_CHOICE = 100;

        public final static int CUSTOMER_EMPTY_CART = 2;
        public final static int CUSTOMER_NOT_EMPTY_CART = 1;

        public final static int CUSTOMER_NEW_ADDRESS = 2;
        public final static int CUSTOMER_EXIST_ADDRESS = 1;
    }

    public static class Users
    {
        public final static UUID SUPER_ADMIN_ID = UUID.randomUUID();
    }
}

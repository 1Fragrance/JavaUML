package eCommerce.storage.repositories.interfaces;

import eCommerce.models.Customer;

import java.util.Optional;

public interface ICustomerRepository extends IRepository<Customer>{
    public Optional<Customer> getByLoginAndPassword(String login, String password);
    public boolean isLoginExist(String login);
}

package eCommerce.storage.repositories.memory;

import eCommerce.models.Customer;
import eCommerce.storage.repositories.interfaces.ICustomerRepository;

import java.util.Optional;

public class CustomerRepository extends MemoryRepositoryBase<Customer> implements ICustomerRepository {
    @Override
    public Optional<Customer> getByLoginAndPassword(String login, String password) {
        return dbSet.stream().filter(x -> x.getLogin().equals(login) && x.getPassword().equals(password)).findFirst();
    }

    @Override
    public boolean isLoginExist(String login) {
        return dbSet.stream().anyMatch(x -> x.getLogin().equals(login));
    }
}

package eCommerce.storage.repositories.interfaces;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface IRepository<T> {
    public T insert(T entity);
    public T update(T entity);
    public void delete(T entity);

    public ArrayList<T> getList();
    public Optional<T> getById(UUID id);
}

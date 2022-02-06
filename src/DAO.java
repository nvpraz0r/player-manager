import java.util.List;

/**
 * DAO interface that represents
 * generic object T and
 * structures override
*/
public interface DAO<T> {
    T get(String player);
    List<T> getAll();
    boolean add(T t);
    boolean update(T t);
    boolean delete(T t);
}
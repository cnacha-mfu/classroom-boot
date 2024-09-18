package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer>{
    List<Order> findAll();
    List<Order> findByCustomerId(Integer id);
}

package guru.springframework.spring6reactive.repositories;

import guru.springframework.spring6reactive.domain.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 21/08/2024 - 15:15
 * @since jdk 1.21
 */
public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}

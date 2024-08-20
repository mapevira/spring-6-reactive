package guru.springframework.spring6reactive.repositories;

import guru.springframework.spring6reactive.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 20/08/2024 - 14:22
 * @since jdk 1.21
 */
public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {
}

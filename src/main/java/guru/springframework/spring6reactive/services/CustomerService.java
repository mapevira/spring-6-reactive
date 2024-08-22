package guru.springframework.spring6reactive.services;

import guru.springframework.spring6reactive.model.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 21/08/2024 - 15:10
 * @since jdk 1.21
 */
public interface CustomerService {

    Flux<CustomerDTO> listCustomers();

    Mono<CustomerDTO> getCustomerById(Integer customerId);

    Mono<CustomerDTO> saveNewCustomer(CustomerDTO customerDTO);

    Mono<CustomerDTO> updateCustomer(Integer customerId, CustomerDTO customerDTO);

    Mono<CustomerDTO> patchCustomer(Integer customerId, CustomerDTO customerDTO);

    Mono<Void> deleteCustomerById(Integer customerId);
}

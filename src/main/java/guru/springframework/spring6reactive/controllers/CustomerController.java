package guru.springframework.spring6reactive.controllers;

import guru.springframework.spring6reactive.model.BeerDTO;
import guru.springframework.spring6reactive.model.CustomerDTO;
import guru.springframework.spring6reactive.repositories.CustomerRepository;
import guru.springframework.spring6reactive.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 21/08/2024 - 17:43
 * @since jdk 1.21
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    public static final String CUSTOMER_PATH = "/api/v2/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{customerId}";

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;


    @DeleteMapping(CUSTOMER_PATH_ID)
    Mono<ResponseEntity<Void>> deleteById(@PathVariable final Integer customerId) {
        return customerService.deleteCustomerById(customerId)
                .then(Mono.fromCallable(() -> ResponseEntity.noContent().build()));

    }

    @PatchMapping(CUSTOMER_PATH_ID)
    ResponseEntity<Void> patchExistingCustomer(@PathVariable("customerId") final Integer customerId,
                                           @Validated @RequestBody final CustomerDTO customerDTO) {

        customerService.patchCustomer(customerId, customerDTO).subscribe();

        return ResponseEntity.ok().build();
    }

    @PutMapping(CUSTOMER_PATH_ID)
    ResponseEntity<Void> updateExistingCustomer(@PathVariable("customerId") final Integer customerId,
                                            @Validated @RequestBody final CustomerDTO customerDTO) {

        customerService.updateCustomer(customerId, customerDTO).subscribe();

        return ResponseEntity.ok().build();
    }

    @PostMapping(CUSTOMER_PATH)
    Mono<ResponseEntity<Void>> createNewCustomer(@Validated @RequestBody final CustomerDTO customerDTO) {
        return customerService.saveNewCustomer(customerDTO)
                .map(saveDto -> ResponseEntity.created(UriComponentsBuilder
                                .fromHttpUrl("http://localhost:8080/" + CUSTOMER_PATH
                                        + "/" + saveDto.getId())
                                .build().toUri())
                        .build());
    }

    @GetMapping(CUSTOMER_PATH_ID)
    Mono<CustomerDTO> getCustomerById(@PathVariable("customerId") final Integer customerId) {
        return customerService.getCustomerById(customerId);
    }

    @GetMapping(CUSTOMER_PATH)
    Flux<CustomerDTO> listCustomers() {
        log.info("Listing all customers");

        return customerService.listCustomers();
    }

}

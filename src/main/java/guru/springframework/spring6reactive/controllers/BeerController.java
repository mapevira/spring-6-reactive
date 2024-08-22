package guru.springframework.spring6reactive.controllers;

import guru.springframework.spring6reactive.model.BeerDTO;
import guru.springframework.spring6reactive.services.BeerService;
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
 * @version 20/08/2024 - 17:27
 * @since jdk 1.21
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class BeerController {

    public static final String BEER_PATH = "/api/v2/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";

    private final BeerService beerService;

    @DeleteMapping(BEER_PATH_ID)
    Mono<ResponseEntity<Void>> deleteById(@PathVariable final Integer beerId) {
        return beerService.deleteBeerById(beerId)
                .then(Mono.fromCallable(() -> ResponseEntity.noContent().build()));

    }

    @PatchMapping(BEER_PATH_ID)
    ResponseEntity<Void> patchExistingBeer(@PathVariable("beerId") final Integer beerId,
                                           @Validated @RequestBody final BeerDTO beerDTO) {

        beerService.patchBeer(beerId, beerDTO).subscribe();

        return ResponseEntity.noContent().build();
    }

    @PutMapping(BEER_PATH_ID)
    ResponseEntity<Void> updateExistingBeer(@PathVariable("beerId") final Integer beerId,
                                            @Validated @RequestBody final BeerDTO beerDTO) {

        beerService.updateBeer(beerId, beerDTO).subscribe();

        return ResponseEntity.noContent().build();
    }

    @PostMapping(BEER_PATH)
    Mono<ResponseEntity<Void>> createNewBeer(@Validated @RequestBody final BeerDTO beerDTO) {
        return beerService.saveNewBeer(beerDTO)
                .map(saveDto -> ResponseEntity.created(UriComponentsBuilder
                        .fromHttpUrl("http://localhost:8080/" + BEER_PATH
                            + "/" + saveDto.getId())
                        .build().toUri())
                        .build());
    }

    @GetMapping(BEER_PATH_ID)
    Mono<BeerDTO> getBeerById(@PathVariable("beerId") final Integer beerId) {
        return beerService.getBeerById(beerId);
    }

    @GetMapping(BEER_PATH)
    Flux<BeerDTO> listBeers() {
        log.info("Listing all beers");

        return beerService.listBeers();
    }

 }

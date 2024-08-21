package guru.springframework.spring6reactive.controllers;

import guru.springframework.spring6reactive.model.BeerDTO;
import guru.springframework.spring6reactive.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping(BEER_PATH_ID)
    Mono<ResponseEntity<Void>> createNewBeer(final BeerDTO beerDTO) {
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

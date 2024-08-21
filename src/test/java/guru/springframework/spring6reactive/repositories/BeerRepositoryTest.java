package guru.springframework.spring6reactive.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.spring6reactive.config.DatabaseConfig;
import guru.springframework.spring6reactive.domain.Beer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataR2dbcTest
@Import(DatabaseConfig.class)
@Slf4j
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testCreateJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        log.info(objectMapper.writeValueAsString(getTestBeer()));
    }

    @Test
    void saveNewBeer() {
        beerRepository.save(getTestBeer())
                .subscribe(beer -> System.out.println(beer.toString()));
    }

    Beer getTestBeer() {
        return Beer.builder()
                .beerName("Space Dust")
                .beerStyle("IPA")
                .price(BigDecimal.TEN)
                .quantityOnHand(12)
                .upc("123213")
                .build();
    }

}
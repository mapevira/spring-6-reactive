package guru.springframework.spring6reactive.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by jt, Spring Framework Guru.
 *
 * @author architecture - raulp
 * @version 20/08/2024 - 17:30
 * @since jdk 1.21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTO {

    private Integer id;

    @NotBlank
    @Size(min = 3, max = 255)
    private String beerName;

    @NotBlank
    @Size(min = 1, max = 255)
    private String beerStyle;

    @NotBlank
    @Size(max = 25)
    private String upc;

    private Integer quantityOnHand;
    private BigDecimal price;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}

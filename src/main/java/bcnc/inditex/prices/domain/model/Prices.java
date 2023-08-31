package inditex.prices.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Prices {

    // Unique Price Id
    private UUID id;

    // The Inditex Group retailer
    private Integer brandId;

    // Date from of the price application
    private LocalDateTime startDate;

    // Date to of the price application
    private LocalDateTime endDate;

    // Price application Id
    private Integer priceList;

    // Product Id
    private Integer productId;

    // Priority when two or more prices application match same date range
    private Integer priority;

    // Price
    private Float price;

    // Currency
    private String curr;

}

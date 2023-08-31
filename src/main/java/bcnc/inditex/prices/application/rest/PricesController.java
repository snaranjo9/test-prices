package bcnc.inditex.prices.application.rest;

import bcnc.inditex.prices.application.rest.converters.LocalDateTimeConverter;
import bcnc.inditex.prices.application.rest.dto.PricesDto;
import bcnc.inditex.prices.application.rest.mapper.PricesToPricesDtoMapper;
import inditex.prices.domain.services.PricesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class PricesController {

    private final PricesService pricesService;

    private final PricesToPricesDtoMapper pricesToPricesDtoMapper;

    @GetMapping(value = "/prices")
    public ResponseEntity<List<PricesDto>> getPrices(@RequestParam(name = "applicationDate") String applicationDate,
                                                     @RequestParam(name = "productId") Integer productId,
                                                     @RequestParam(name = "brandId") Integer brandId) {
        log.info("PricesController - Get Prices by {} - {} - {}", applicationDate, productId, brandId);
        return ResponseEntity.ok(pricesToPricesDtoMapper.toPricesDto(
                pricesService.getPrices(LocalDateTimeConverter.parseDateTime(applicationDate), productId, brandId)));
    }

}

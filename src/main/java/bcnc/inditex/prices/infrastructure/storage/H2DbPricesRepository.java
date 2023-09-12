package bcnc.inditex.prices.infrastructure.storage;

import bcnc.inditex.prices.domain.model.Prices;
import bcnc.inditex.prices.domain.repositories.PricesRepository;
import bcnc.inditex.prices.infrastructure.storage.mapper.PricesEntityToPricesMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class H2DbPricesRepository implements PricesRepository {

    private final PricesEntityRepository pricesEntityRepository;

    private final PricesEntityToPricesMapper pricesEntityToPricesMapper;

    @Override
    public Prices getPrices(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        log.info("H2DbPricesRepository - Get Prices by {} - {} - {}", applicationDate, productId, brandId);
        return pricesEntityToPricesMapper.toPrices(
                pricesEntityRepository.findTopByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(applicationDate, applicationDate, productId, brandId));
    }
}

package inditex.prices.infrastructure.storage;

import inditex.prices.domain.model.Prices;
import inditex.prices.domain.repositories.PricesRepository;
import inditex.prices.infrastructure.storage.mapper.PricesEntityToPricesMapper;
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
    public List<Prices> getPrices(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        log.info("H2DbPricesRepository - Get Prices by {} - {} - {}", applicationDate, productId, brandId);
        return pricesEntityToPricesMapper.toPrices(
                pricesEntityRepository.findTopByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(applicationDate, applicationDate, productId, brandId));
    }
}

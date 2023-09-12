package bcnc.inditex.prices.domain.services.impl;

import bcnc.inditex.prices.domain.model.Prices;
import bcnc.inditex.prices.domain.repositories.PricesRepository;
import bcnc.inditex.prices.domain.services.PricesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PricesServiceImpl implements PricesService {

    private final PricesRepository pricesRepository;

    public Prices getPrices(LocalDateTime applicationDate, Integer productId, Integer brandId) {
        log.info("PricesServiceImpl - Get Prices by {} - {} - {}", applicationDate, productId, brandId);
        return pricesRepository.getPrices(applicationDate, productId, brandId);
    }

}

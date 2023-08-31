package inditex.prices.domain.repositories;

import inditex.prices.domain.model.Prices;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesRepository {

    List<Prices> getPrices(LocalDateTime applicationDate, Integer productId, Integer brandId);

}

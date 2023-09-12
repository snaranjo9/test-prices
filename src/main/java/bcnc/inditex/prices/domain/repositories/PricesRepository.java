package bcnc.inditex.prices.domain.repositories;

import bcnc.inditex.prices.domain.model.Prices;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesRepository {

    Prices getPrices(LocalDateTime applicationDate, Integer productId, Integer brandId);

}

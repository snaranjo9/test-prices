package inditex.prices.domain.services;

import inditex.prices.domain.model.Prices;

import java.time.LocalDateTime;
import java.util.List;

public interface PricesService {

    List<Prices> getPrices(LocalDateTime applicationDate, Integer productId, Integer brandId);

}

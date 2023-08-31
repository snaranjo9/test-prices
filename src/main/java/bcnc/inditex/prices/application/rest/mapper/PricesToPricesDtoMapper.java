package bcnc.inditex.prices.application.rest.mapper;

import bcnc.inditex.prices.application.rest.dto.PricesDto;
import inditex.prices.domain.model.Prices;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PricesToPricesDtoMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "brandId", source = "brandId")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "priceList", source = "priceList")
    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "priority", source = "priority")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "curr", source = "curr")
    PricesDto toPricesDto(Prices prices);

    default List<PricesDto> toPricesDto(List<Prices> pricesList) {
        return CollectionUtils.isEmpty(pricesList) ? new ArrayList<>():pricesList.stream().map(this::toPricesDto).collect(Collectors.toList());
    }

}

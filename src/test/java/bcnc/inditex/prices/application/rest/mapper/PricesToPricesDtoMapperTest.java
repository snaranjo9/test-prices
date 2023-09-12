package bcnc.inditex.prices.application.rest.mapper;

import bcnc.inditex.prices.application.rest.dto.PricesDto;
import bcnc.inditex.prices.domain.model.Prices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.*;

@ExtendWith(SpringExtension.class)
class PricesToPricesDtoMapperTest {

    private PricesToPricesDtoMapper pricesToPricesDtoMapper;

    @BeforeEach
    void setup() {
        pricesToPricesDtoMapper = new PricesToPricesDtoMapperImpl();
    }

    @Test
    void givenAPrices_whenTryToMapToPricesDto_thenReturnAPricesDto() {

        // arrange
        Prices prices =
                Prices.builder()
                        .id(UUID.fromString("c0acbd79-10bd-4e73-bf9b-82251e74dd4d"))
                        .brandId(1)
                        .startDate(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("2020-06-14T00:00:00", LocalDateTime::from))
                        .endDate(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("2020-12-31T23:59:59", LocalDateTime::from))
                        .priceList(1)
                        .productId(35455)
                        .priority(0)
                        .price(35.5F)
                        .curr("EUR")
                        .build();

        // act
        PricesDto pricesDto = pricesToPricesDtoMapper.toPricesDto(prices);

        // assert
        assertNotNull("Prices should be not null", pricesDto);
        assertEquals("Id should be c0acbd79-10bd-4e73-bf9b-82251e74dd4d",  "c0acbd79-10bd-4e73-bf9b-82251e74dd4d", pricesDto.getId().toString());
        assertEquals("BrandId should be 1",  1, prices.getBrandId());
        assertEquals("StartDate should be 2020-06-14T00:00:00",  "2020-06-14T00:00:00", pricesDto.getStartDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        assertEquals("EndDate should be 2020-12-31T23:59:59",  "2020-12-31T23:59:59", pricesDto.getEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        assertEquals("PriceList should be 1",  1, pricesDto.getPriceList());
        assertEquals("ProductId should be 35455",  35455, pricesDto.getProductId());
        assertEquals("Priority should be 0",  0, pricesDto.getPriority());
        assertEquals("Price should be 35.5",  35.5F, pricesDto.getPrice());
        assertEquals("Curr should be EUR",  "EUR", pricesDto.getCurr());

    }

    @Test
    void givenANullPrices_whenTryToMapToPrices_thenReturnANullPricesDto() {

        // arrange
        Prices prices = null;
                Prices.builder()
                        .id(UUID.fromString("c0acbd79-10bd-4e73-bf9b-82251e74dd4d"))
                        .brandId(1)
                        .startDate(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("2020-06-14T00:00:00", LocalDateTime::from))
                        .endDate(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("2020-12-31T23:59:59", LocalDateTime::from))
                        .priceList(1)
                        .productId(35455)
                        .priority(0)
                        .price(35.5F)
                        .curr("EUR")
                        .build();

        // act
        PricesDto pricesDto = pricesToPricesDtoMapper.toPricesDto(prices);

        // assert
        assertNull("Prices should be null", pricesDto);

    }
}

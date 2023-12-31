package bcnc.inditex.prices.domain.services.impl;

import bcnc.inditex.prices.domain.model.Prices;
import bcnc.inditex.prices.domain.repositories.PricesRepository;
import bcnc.inditex.prices.domain.services.PricesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.*;

@ExtendWith(SpringExtension.class)
class PriceServiceImplTest {

    private PricesService pricesService;

    private bcnc.inditex.prices.domain.repositories.PricesRepository pricesRepository;

    @BeforeEach
    void setup() {
        pricesRepository = mock(PricesRepository.class);
        pricesService = new PricesServiceImpl(pricesRepository);
    }

    @Test
    void givenApplicationDateAndProductIdAndBrandId_whenFindByThoseFields_ThenReturnAListOfPrices(){
        // arrange
        when(pricesRepository.getPrices(any(), any(), any())).thenReturn(Prices.builder()
                .id(UUID.fromString("c0acbd79-10bd-4e73-bf9b-82251e74dd4d"))
                .brandId(1)
                .startDate(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("2020-06-14T00:00:00", LocalDateTime::from))
                .endDate(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("2020-12-31T23:59:59", LocalDateTime::from))
                .priceList(1)
                .productId(35455)
                .priority(0)
                .price(35.5F)
                .curr("EUR")
                .build());

        // act
        Prices prices = pricesService.getPrices(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("2020-06-14T00:00:00", LocalDateTime::from), 35455, 1);

        // assert
        verify(pricesRepository).getPrices(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse("2020-06-14T00:00:00", LocalDateTime::from), 35455, 1);
        assertNotNull("Price should be not null", prices);

    }


}

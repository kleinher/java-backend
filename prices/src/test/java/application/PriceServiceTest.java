package application;


import com.challenge.prices.application.PriceService;
import com.challenge.prices.domain.models.Price;
import com.challenge.prices.infrastructure.outbound.database.PriceRepositoryAdapter;
import com.challenge.prices.util.dto.PriceDTO;
import com.challenge.prices.util.mapper.PriceMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PriceServiceTest {

    @Mock
    private PriceRepositoryAdapter priceRepositoryAdapter;
    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceService priceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPriceOk() {
        long productId = 35455;
        long brandId = 1;
        Integer priceList = 1;
        LocalDateTime startDate = LocalDateTime.parse("2020-06-14T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse("2020-12-31T23:59:59");
        LocalDateTime testDate = LocalDateTime.parse("2020-06-14T10:00:00");

        Price price = new Price(0L,  brandId, startDate, endDate,priceList,productId, 0,new BigDecimal("35.50"),  "EUR");
        PriceDTO priceDTO = new PriceDTO(productId, brandId,priceList, startDate, endDate, new BigDecimal("35.50"));

        when(priceRepositoryAdapter.getPrice(productId, brandId, testDate)).thenReturn(Optional.of(price));
        when(priceMapper.toDto(price)).thenReturn(priceDTO);

        Optional<PriceDTO> result = priceService.getPrice(productId, brandId, testDate);
        assert(result.isPresent());
        assertEquals(priceDTO.getProductId(), result.get().getProductId());
        assertEquals(priceDTO.getBrandId(), result.get().getBrandId());
        assertEquals(priceDTO.getPriceList(), result.get().getPriceList());
        assertEquals(priceDTO.getStartDate(), result.get().getStartDate());
        assertEquals(priceDTO.getEndDate(), result.get().getEndDate());
        assertEquals(priceDTO.getPrice(), result.get().getPrice());

    }

}

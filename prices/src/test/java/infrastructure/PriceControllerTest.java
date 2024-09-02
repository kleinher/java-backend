package infrastructure;

import com.challenge.prices.domain.PriceService;

import com.challenge.prices.domain.models.PriceDTO;
import com.challenge.prices.infrastructure.PriceController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    private static long productId;
    private static long brandId;
    private static LocalDateTime applicationDate;

    @BeforeAll
    public static void init() {
        productId = 1;
        brandId = 35455;
        applicationDate = LocalDateTime.parse("2020-06-16T21:00:00");

    }

    @Test
    public void getPriceOk(){
        PriceDTO priceDTO = new PriceDTO(productId, brandId, 1, LocalDateTime.parse("2020-06-14T00:00:00"), LocalDateTime.parse("2020-12-31T23:59:59"), new BigDecimal("35.50"));

        when(priceService.getPrice(productId, brandId, applicationDate)).thenReturn(Optional.of(priceDTO));
        ResponseEntity<PriceDTO> result = priceController.getPrice(productId, brandId, applicationDate.toString());

        assertEquals(priceDTO.getProductId(), result.getBody().getProductId());
        assertEquals(priceDTO.getBrandId(), result.getBody().getBrandId());
        assertEquals(priceDTO.getPriceList(), result.getBody().getPriceList());
        assertEquals(priceDTO.getStartDate(), result.getBody().getStartDate());
        assertEquals(priceDTO.getEndDate(), result.getBody().getEndDate());
        assertEquals(priceDTO.getPrice(), result.getBody().getPrice());
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void getPriceNotFound(){
        //TODO
    }

    @Test
    public void getPriceException(){
        //TODO
    }

    @Test
    public void getPriceBadRequest(){
        //TODO
    }


}
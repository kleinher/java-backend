package com.challenge.prices.infrastructure;

import com.challenge.prices.domain.PriceService;
import com.challenge.prices.domain.models.PriceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequiredArgsConstructor

public class PriceController {

    private final PriceService priceService;

    @GetMapping("/api/prices")
    public ResponseEntity<PriceDTO> getPrice(
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId,
            @RequestParam("applicationDate") String applicationDateStr) {

        LocalDateTime applicationDate = LocalDateTime.parse(applicationDateStr);
        Optional<PriceDTO> priceDTO = priceService.getPrice(productId, brandId, applicationDate);

        return priceDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
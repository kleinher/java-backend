package com.challenge.prices.infrastructure.inbound.controllers;

import com.challenge.prices.application.GetPriceService;
import com.challenge.prices.util.dto.PriceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PriceController {

    private final GetPriceService priceService;

    @GetMapping("/api/prices")
    public ResponseEntity<PriceDTO> getPrice(
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId,
            @RequestParam("applicationDate") String applicationDateStr) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime applicationDate = LocalDateTime.parse(applicationDateStr, formatter);

        Optional<PriceDTO> priceDTO = priceService.getPrice(productId, brandId, applicationDate);

        return priceDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
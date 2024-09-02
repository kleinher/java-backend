package com.challenge.prices.application;

import com.challenge.prices.domain.PriceMapper;
import com.challenge.prices.domain.PriceRepository;
import com.challenge.prices.domain.models.Price;
import com.challenge.prices.domain.models.PriceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceService implements com.challenge.prices.domain.PriceService {

    private final PriceRepository getPricePort;
    private final PriceMapper priceMapper;

    @Override
    public Optional<PriceDTO> getPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        Optional<Price> price = getPricePort.getPrice(productId, brandId, applicationDate);

        return price.map(priceMapper::toDto);
    }
}

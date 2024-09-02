package com.challenge.prices.application;

import com.challenge.prices.domain.models.Price;
import com.challenge.prices.domain.port.in.PriceServiceUseCase;
import com.challenge.prices.domain.port.out.GetPricePort;
import com.challenge.prices.util.dto.PriceDTO;
import com.challenge.prices.util.mapper.PriceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceService implements PriceServiceUseCase {

    private final GetPricePort getPricePort;
    private final PriceMapper priceMapper;

    @Override
    public Optional<PriceDTO> getPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        Optional<Price> price = getPricePort.getPrice(productId, brandId, applicationDate);

        return price.map(priceMapper::toDto);
    }
}

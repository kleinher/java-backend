package com.challenge.prices.domain.port.in;

import com.challenge.prices.util.dto.PriceDTO;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetPriceUseCase {
    Optional<PriceDTO> getPrice(Long productId, Long brandId, LocalDateTime applicationDate);
}

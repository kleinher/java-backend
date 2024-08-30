package com.challenge.prices.infrastructure.outbound.database;

import com.challenge.prices.domain.models.Price;
import com.challenge.prices.domain.port.out.GetPricePort;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements GetPricePort {

    private final JPAPriceRepository jpaPriceRepository;

    @Override
    public Optional<Price> getPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return jpaPriceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId, brandId, applicationDate, applicationDate);
    }

}

package com.challenge.prices.infrastructure.repository;

import com.challenge.prices.domain.models.Price;
import com.challenge.prices.domain.PriceRepository;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final JPAPriceRepository jpaPriceRepository;

    @Override
    public Optional<Price> getPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return jpaPriceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId, brandId, applicationDate, applicationDate);
    }

}

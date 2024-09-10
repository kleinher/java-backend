package com.challenge.prices.infrastructure.repository;

import com.challenge.prices.domain.PriceRepository;
import com.challenge.prices.domain.models.Brand;
import com.challenge.prices.domain.models.Prices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final JPAPriceRepository jpaPriceRepository;

    @Override
    public Optional<Prices> getPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return jpaPriceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId, brandId, applicationDate, applicationDate);
    }

}
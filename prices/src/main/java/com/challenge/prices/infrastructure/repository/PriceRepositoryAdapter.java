package com.challenge.prices.infrastructure.repository;

import com.challenge.prices.domain.PriceRepository;
import com.challenge.prices.domain.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepository {

    private final JPAPriceRepository jpaPriceRepository;

    @Override
    public Optional<Product> getPrice(Long productId, Long brandId, LocalDateTime applicationDate) {
        return jpaPriceRepository.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(productId, brandId, applicationDate, applicationDate);
    }

}

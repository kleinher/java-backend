package com.challenge.prices.infrastructure.repository;

import com.challenge.prices.domain.models.Prices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface JPAPriceRepository extends JpaRepository<Prices, Long> {

    Optional<Prices> findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Long productId,
            Long brandId,
            LocalDateTime applicationDate1,
            LocalDateTime applicationDate2);
}
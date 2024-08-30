package com.challenge.prices.infrastructure.outbound.database;

import com.challenge.prices.domain.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface JPAPriceRepository extends JpaRepository<Price, Long> {

    Optional<Price> findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Long productId,
            Long brandId,
            LocalDateTime applicationDate1,
            LocalDateTime applicationDate2);
}

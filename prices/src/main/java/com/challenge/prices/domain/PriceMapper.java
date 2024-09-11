package com.challenge.prices.domain;

import com.challenge.prices.domain.models.Prices;
import com.challenge.prices.infrastructure.controller.PriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "currency", target = "currency")
    PriceDTO toDto(Prices prices);

    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "brandId", target = "brand.id")
    @Mapping(source = "priceList", target = "priceList")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "price", target = "price")
    Prices toEntity(PriceDTO priceDTO);
}
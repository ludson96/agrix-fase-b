package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

public record FertilizerDto(Long id, String name, String brand, String composition) {

  public Fertilizer dtoToEntity() {
    return new Fertilizer(name, brand, composition);
  }

  public static FertilizerDto fromEntityToDto(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }
}

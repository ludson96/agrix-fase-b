package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.Fertilizer;

public record FertilizerDto(String name, String brand, String composition) {

  public Fertilizer dtoToEntity() {
    return new Fertilizer(name, brand, composition);
  }

}

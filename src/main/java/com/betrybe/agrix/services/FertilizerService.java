package com.betrybe.agrix.services;

import com.betrybe.agrix.error.CustomError;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FertilizerService {

  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  public Fertilizer insertFertilizer(Fertilizer fertilizer) {
    return  fertilizerRepository.save(fertilizer);
  }

  public List<Fertilizer> getAllFertilizes() {
    return fertilizerRepository.findAll();
  }

  public Fertilizer getFertilizeById(Long id) throws CustomError {
    Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findById(id);

    if (optionalFertilizer.isEmpty()) {
      throw new CustomError(
          "Fertilizante não encontrado!",
          HttpStatus.NOT_FOUND.value()
      );
    }

    return optionalFertilizer.get();
  }
}

package com.betrybe.agrix.services;

import com.betrybe.agrix.error.CustomError;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CropService {

  private final CropRepository cropRepository;

  private final FertilizerRepository fertilizerRepository;



  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  public List<Crop> findAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * Retorna um Crop pelo seu id.
   *
   * @param id desejado do Crop.
   * @return o crop desejado
   * @throws CustomError retorna uma exceção caso não exista a Farm solicitada.
   */
  public Crop getCropById(Long id) throws CustomError {
    Optional<Crop> optionalCrop = cropRepository.findById(id);

    if (optionalCrop.isEmpty()) {
      throw new CustomError(
          "Plantação não encontrada!",
          HttpStatus.NOT_FOUND.value()
      );
    }

    return optionalCrop.get();
  }

  public List<Crop> searchCrops(LocalDate start, LocalDate end) {
    return cropRepository.findAllByharvestDateBetween(start, end);
  }

  public Boolean associateCropWithFertilizer(Long cropId, Long fertilizerId) throws CustomError {
    Optional<Crop> optionalCrop = cropRepository.findById(cropId);

    if (optionalCrop.isEmpty()) {
      throw new CustomError(
          "Plantação não encontrada!",
          HttpStatus.NOT_FOUND.value()
      );
    }

    Optional<Fertilizer> optionalFertilizer = fertilizerRepository.findById(fertilizerId);

    if (optionalFertilizer.isEmpty()) {
      throw new CustomError(
          "Fertilizante não encontrado!",
          HttpStatus.NOT_FOUND.value()
      );
    }

    return true;
  }
}

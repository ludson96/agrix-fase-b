package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.error.CustomError;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller da entidade Crop.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final FarmService farmService;

  @Autowired
  public CropController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Retorna todos os Crops do banco de dados.
   *
   * @return status http 200 e um List com todos os Crops.
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> allCrops = farmService.findAllCrops();

    List<CropDto> allCropsDto = allCrops.stream().map(crop -> new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId()
    )).collect(Collectors.toList());

    return ResponseEntity.ok().body(allCropsDto);
  }

  /**
   * Retorna um Crop baseado no seu id.
   *
   * @param id do Crop a ser retornado.
   * @return status http 200 e um CropDto enviando apenas seu id.
   * @throws CustomError lança uma exceção caso não exista nenhum Farm referente ao Crop solicitado.
   */
  @GetMapping("{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable(name = "id") Long id)
      throws CustomError {
    Crop cropById = farmService.getCropById(id);

    return ResponseEntity
        .ok()
        .body(CropDto.fromEntityToDto(cropById));
  }
}

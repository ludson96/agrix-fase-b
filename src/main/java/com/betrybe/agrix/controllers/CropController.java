package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.error.CustomError;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.services.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller da entidade Crop.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Retorna todos os Crops do banco de dados.
   *
   * @return status http 200 e um List com todos os Crops.
   */
  @GetMapping
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<Crop> allCrops = cropService.findAllCrops();

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
    Crop cropById = cropService.getCropById(id);

    return ResponseEntity
        .ok()
        .body(CropDto.fromEntityToDto(cropById));
  }

  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> searchCrops(
      @RequestParam LocalDate start,
      @RequestParam LocalDate end
  ) {
    List<Crop> allSearchCrops = cropService.searchCrops(start, end);

    List<CropDto> allSearchCropsDto = allSearchCrops.stream().map(crop -> new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId()
    ))
        .toList();

    return ResponseEntity
        .ok()
        .body(allSearchCropsDto);
  }

  @PostMapping("{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateCropWithFertilizer(
      @PathVariable(name = "cropId") Long cropId,
      @PathVariable(name = "fertilizerId") Long fertilizerId
  ) throws CustomError {
    cropService.associateCropWithFertilizer(cropId, fertilizerId);

      return ResponseEntity
          .status(HttpStatus.CREATED)
          .body("Fertilizante e plantação associados com sucesso!");
  }
}

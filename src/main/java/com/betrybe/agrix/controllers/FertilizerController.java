package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.error.CustomError;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  @PostMapping
  public ResponseEntity<FertilizerDto> insertFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer newFertilizer = fertilizerService.insertFertilizer(fertilizerDto.dtoToEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(FertilizerDto.fromEntityToDto(newFertilizer));
  }

  @GetMapping
  public ResponseEntity<List<FertilizerDto>> getAllFertilizes() {
    List<Fertilizer> allFertilizes = fertilizerService.getAllFertilizes();
    return ResponseEntity.ok(
        allFertilizes.stream()
            .map(crop -> new FertilizerDto(
                crop.getId(),
                crop.getName(),
                crop.getBrand(),
                crop.getComposition()
            )).toList()
    );
  }

  @GetMapping("{id}")
  public ResponseEntity<FertilizerDto> getFertilizerById(@PathVariable(name = "id") Long id)
      throws CustomError {
    Fertilizer fertilizeById = fertilizerService.getFertilizeById(id);

    return ResponseEntity
        .ok()
        .body(FertilizerDto.fromEntityToDto(fertilizeById));
  }
}

package com.betrybe.agrix.models.repositories;

import com.betrybe.agrix.models.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository de Crop.
 */
@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
  List<Crop> findAllByharvestDateBetween(LocalDate start, LocalDate end);

}
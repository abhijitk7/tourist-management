package com.cts.fse.domain;

import com.cts.fse.models.TouristPlaces;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TouristPlacesRepository extends JpaRepository<TouristPlaces, Long> {
}

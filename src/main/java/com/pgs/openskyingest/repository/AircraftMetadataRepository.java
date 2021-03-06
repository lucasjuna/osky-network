package com.pgs.openskyingest.repository;

import com.pgs.openskyingest.model.AircraftMetadata;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AircraftMetadataRepository extends MongoRepository<AircraftMetadata, String> {
    List<AircraftMetadata> findAircraftMetadataByIsTracking(boolean isTracking);

    AircraftMetadata findAircraftMetadataByIcao24(String icao24);

    boolean existsByIcao24(String icao24);

    List<AircraftMetadata> findAircraftMetadataByRegistrationContains(String registration, Pageable pageable);

    @Query(value="{}",fields="{ '_id': 0, 'registration' : 1, 'icao24' : 1}")
    List<String> findAllAircraftTailNumber(Pageable pageable);

    Long deleteAircraftMetadataByIcao24(String icao24);
}

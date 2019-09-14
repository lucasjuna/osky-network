package com.pgs.openskyingest.service;

import com.pgs.openskyingest.model.AircraftFlight;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AircraftFlightService {
    List<AircraftFlight> retrieveAircraftFlightInTime(String tailNumber, Long fromTimestamp, Long toTimestamp);
    Map<String, Set<AircraftFlight>> retrieveAircraftFlightGroupByDate(String tailNumber, Long fromTimestamp, Long toTimestamp);
}

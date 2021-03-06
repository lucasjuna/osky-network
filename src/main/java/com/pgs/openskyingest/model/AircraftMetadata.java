package com.pgs.openskyingest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "aircraftMetadata")
public class AircraftMetadata {
    @Id
    private String id;

    private String registration;
    private String manufacturerName;
    private String manufacturerIcao;
    private String model;
    private String typecode;
    private String serialNumber;
    private String lineNumber;
    private String icaoAircraftClass;
    private String selCal;
    private String operator;
    private String operatorCallsign;
    private String operatorIcao;
    private String operatorIata;
    private String owner;
    private String categoryDescription;
    private String registered;
    private String regUntil;
    private String status;
    private String built;
    private String firstFlightDate;
    private String engines;
    private Boolean modes;
    private Boolean adsb;
    private Boolean acars;
    private Boolean vdl;
    private String notes;
    private String country;
    private String lastSeen;
    private String firstSeen;
    private String icao24;
    private Long timestamp;

    // flag indicate whether we should track this aircarft or not
    private Boolean isTracking = Boolean.FALSE;

    // time of latest position of aircraft
    private Long timePosition;
}

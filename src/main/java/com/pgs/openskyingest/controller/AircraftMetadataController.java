package com.pgs.openskyingest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pgs.openskyingest.model.AircraftMetadata;
import com.pgs.openskyingest.service.ConfigManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "https://opensky-ingest-fe.herokuapp.com"})
@RestController
public class AircraftMetadataController {

    @Autowired
    private ConfigManagmentService configManagmentService;

    @RequestMapping(value = "/aircraft/metadata/all", method = RequestMethod.GET)
    public List<AircraftMetadata> getAllAircraft(@RequestParam(value = "tailNumber", defaultValue = "") String tailNumber) {
        tailNumber = tailNumber.toUpperCase();
        if (StringUtils.isEmpty(tailNumber)) {
            return configManagmentService.retrieveAllAircraft();
        } else {
            return configManagmentService.retrieveAircraftMetadataByRegistration(tailNumber);
        }
    }

    @RequestMapping(value = "/aircraft/metadata", method = RequestMethod.POST)
    public AircraftMetadata addAircraftForWatching(@RequestBody String tailNumber) {
        tailNumber = tailNumber.toUpperCase();
        int result = configManagmentService.insertWatchingAircaftConfig(tailNumber);
        if (result > 0) {
            return configManagmentService.retrieveAircraftMetadataByRegistration(tailNumber).get(0);
        }
        return null;
    }

    @RequestMapping(value = "/aircraft/metadata/tailNumbers", method = RequestMethod.GET)
    public List<String> getAllAircraftTailNumber() {
        List<String> ret = new ArrayList<>();
        String[] jsonRets = configManagmentService.retrieveAllAircraftTailNumber();
        ObjectMapper objectMapper = new ObjectMapper();
        for (String json : jsonRets) {
            try {
                String tailNumber = objectMapper.readTree(json).get("registration").textValue();
                ret.add(tailNumber);
            } catch (Exception e) {
                // do nothing
            }
        }

        return ret;
    }

}

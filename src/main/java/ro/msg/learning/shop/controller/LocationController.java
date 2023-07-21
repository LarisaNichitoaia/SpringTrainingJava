package ro.msg.learning.shop.controller;

import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.service.LocationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Location")
public class LocationController {
    public static final String LOCATION_NOT_FOUND = "Location Not Found";
    @Autowired
    private LocationService locationService;

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable UUID locationId){
        try {
            LocationDto location = locationService.getLocationById(locationId);
            return new ResponseEntity<>(location, HttpStatus.FOUND);
        }
        catch (Exception exception){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, LOCATION_NOT_FOUND, exception);
        }
    }

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations(){
        try {
            List<LocationDto> locations = locationService.getAllLocations();
            return new ResponseEntity<>(locations, HttpStatus.FOUND);
        }
        catch (Exception exception){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, LOCATION_NOT_FOUND, exception);
        }
    }

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@RequestBody @NonNull LocationDto locationDetails) {
        LocationDto locationDto = locationService.createLocation(locationDetails);
        return new ResponseEntity<>(locationDto, HttpStatus.CREATED);
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<LocationDto> putLocation(@PathVariable UUID locationId,
                                                   @RequestBody @NonNull LocationDto updates){
        try{
            LocationDto locationToUpdate = locationService.putLocation(locationId,updates);
            return new ResponseEntity<>(locationToUpdate, HttpStatus.OK);
        }
        catch (Exception exception){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, LOCATION_NOT_FOUND, exception);
        }
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable UUID locationId){
        try {
            locationService.deleteLocationById(locationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception exception){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, LOCATION_NOT_FOUND, exception);
        }
    }

}

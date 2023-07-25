package ro.msg.learning.shop.controller;
import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.mapper.LocationMapper;
import ro.msg.learning.shop.service.LocationService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;
    private final LocationMapper locationMapper;

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable UUID locationId) {
        LocationDto location = locationMapper.toDto(locationService.getLocationById(locationId));
        return new ResponseEntity<>(location, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        List<LocationDto> locations = locationService.getAllLocations().stream().map(locationMapper::toDto).toList();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@RequestBody @NonNull LocationDto locationDetails) {
        Location location = locationMapper.toEntity(locationDetails);
        LocationDto locationDto = locationMapper.toDto(locationService.createLocation(location));
        return new ResponseEntity<>(locationDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<LocationDto> putLocation(@RequestBody @NonNull LocationDto updatesDto) {
        Location updates = locationMapper.toEntity(updatesDto);
        updates.setId(UUID.fromString(updatesDto.getId()));
        LocationDto locationToUpdate = locationMapper.toDto(locationService.putLocation(updates));
        return new ResponseEntity<>(locationToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable UUID locationId) {
        locationService.deleteLocationById(locationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

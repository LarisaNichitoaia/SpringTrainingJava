package ro.msg.learning.shop.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.repository.LocationRepository;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LocationService {

    private static final String NO_LOCATION_IS_FOUND = "No location was found";
    private final LocationRepository locationRepository;

    public Location getLocationById(UUID locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if (location != null) {
            return location;
        }
        throw new EntityNotFoundException(NO_LOCATION_IS_FOUND);
    }

    public List<Location> getAllLocations() {
        List<Location> allLocations = locationRepository.findAll();
        if (!allLocations.isEmpty()) {
            return allLocations;
        }
        throw new EntityNotFoundException(NO_LOCATION_IS_FOUND);
    }

    public Location createLocation(LocationDto locationDto) {
        Location location = Location.builder().name(locationDto.getName())
                .addressCountry(locationDto.getAddressCountry()).addressCity(locationDto.getAddressCity())
                .addressCounty(locationDto.getAddressCounty()).addressStreet(locationDto.getAddressStreet()).build();
        return locationRepository.save(location);
    }

    public Location putLocation(UUID locationId, LocationDto updatesDto) {
        Location locationToUpdate = locationRepository.findById(locationId).orElse(null);
        if (locationToUpdate != null) {
            locationToUpdate.setName(updatesDto.getName());
            locationToUpdate.setAddressCountry(updatesDto.getAddressCountry());
            locationToUpdate.setAddressCity(updatesDto.getAddressCity());
            locationToUpdate.setAddressCounty(updatesDto.getAddressCounty());
            locationToUpdate.setAddressStreet(updatesDto.getAddressStreet());
            return locationRepository.save(locationToUpdate);
        } else throw new EntityNotFoundException(NO_LOCATION_IS_FOUND);
    }

    public void deleteLocationById(UUID locationId) {
        Location locationToDelete = locationRepository.findById(locationId).orElse(null);
        if (locationToDelete != null) {
            locationRepository.deleteById(locationId);
        } else throw new EntityNotFoundException(NO_LOCATION_IS_FOUND);
    }

}

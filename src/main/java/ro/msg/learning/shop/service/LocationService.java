package ro.msg.learning.shop.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.controller.customexceptions.NoSuchObjectException;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.repository.LocationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LocationService {
    private static final String NO_LOCATION_IS_FOUND = "No location was found";
    private final LocationRepository locationRepository;

    public Location getLocationById(UUID locationId) {
        Optional<Location> location = locationRepository.findById(locationId);
        if (location.isEmpty()) {
            throw new NoSuchObjectException(NO_LOCATION_IS_FOUND);
        }
        return location.get();
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location putLocation(Location updates) {
        Optional<Location> locationToUpdate = locationRepository.findById(updates.getId());
        if (locationToUpdate.isPresent()) {
            return locationRepository.save(updates);
        }
        throw new NoSuchObjectException(NO_LOCATION_IS_FOUND);
    }

    public void deleteLocationById(UUID locationId) {
        locationRepository.findById(locationId).ifPresent(locationToDelete -> locationRepository.deleteById(locationId));
    }
}

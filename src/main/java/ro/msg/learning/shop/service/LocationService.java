package ro.msg.learning.shop.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.mapper.LocationMapper;
import ro.msg.learning.shop.repository.LocationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LocationService {

    private static final String NO_LOCATION_IS_FOUND = "No location was found";
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationMapper locationMapper;

    public LocationDto getLocationById(UUID locationId) {
        Location location = locationRepository.findById(locationId).orElse(null);
        if(location!=null){
            return locationMapper.toDto(location);
        }
        throw new EntityNotFoundException(NO_LOCATION_IS_FOUND);
    }

    public List<LocationDto> getAllLocations() {
        List<Location> allLocations = locationRepository.findAll();
        if(!allLocations.isEmpty()){
            List<LocationDto> locationsDto = new ArrayList<>();
            for (Location location: allLocations){
                LocationDto locationDto = locationMapper.toDto(location);
                locationsDto.add(locationDto);
            }
            return locationsDto;
        }
        throw new EntityNotFoundException(NO_LOCATION_IS_FOUND);
    }
    public LocationDto createLocation(LocationDto locationDto) {
        Location location = Location.builder().name(locationDto.getName())
                .addressCountry(locationDto.getAddressCountry()).addressCity(locationDto.getAddressCity())
                .addressCounty(locationDto.getAddressCounty()).addressStreet(locationDto.getAddressStreet()).build();
        return locationMapper.toDto(locationRepository.save(location));
    }

    public LocationDto putLocation(UUID locationId, LocationDto updatesDto) {
        Location locationToUpdate = locationRepository.findById(locationId).orElse(null);
        if(locationToUpdate!=null){
            locationToUpdate.setName(updatesDto.getName());
            locationToUpdate.setAddressCountry(updatesDto.getAddressCountry());
            locationToUpdate.setAddressCity(updatesDto.getAddressCity());
            locationToUpdate.setAddressCounty(updatesDto.getAddressCounty());
            locationToUpdate.setAddressStreet(updatesDto.getAddressStreet());
            return locationMapper.toDto(locationRepository.save(locationToUpdate));
        }
        else throw new EntityNotFoundException(NO_LOCATION_IS_FOUND);
    }

    public void deleteLocationById(UUID locationId) {
        Location locationToDelete = locationRepository.findById(locationId).orElse(null);
        if(locationToDelete!=null){
            locationRepository.deleteById(locationId);
        }
        else throw new EntityNotFoundException(NO_LOCATION_IS_FOUND);
    }

}

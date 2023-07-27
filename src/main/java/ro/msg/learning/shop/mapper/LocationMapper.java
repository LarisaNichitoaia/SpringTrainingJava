package ro.msg.learning.shop.mapper;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.LocationDto;

@Component
public class LocationMapper {
    public LocationDto toDto(Location location) {
        return LocationDto.builder().id(location.getId().toString()).name(location.getName()).addressCountry(location.getAddressCountry())
                .addressCity(location.getAddressCity()).addressCounty(location.getAddressCounty())
                .addressStreet(location.getAddressStreet()).build();
    }

    public Location toEntity(LocationDto locationDto) {
        return Location.builder().name(locationDto.getName())
                .addressCountry(locationDto.getAddressCountry()).addressCity(locationDto.getAddressCity())
                .addressCounty(locationDto.getAddressCounty()).addressStreet(locationDto.getAddressStreet()).build();
    }
}

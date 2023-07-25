package ro.msg.learning.shop.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationDto {
    private String id;
    private String name;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreet;
}

package ro.msg.learning.shop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import ro.msg.learning.shop.domain.primarykeys.EntityKey;

@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "location")
public class Location extends EntityKey {

    private String name;

    @Column(name = "address_country")
    private String addressCountry;

    @Column(name = "address_city")
    private String addressCity;

    @Column(name = "address_county")
    private String addressCounty;

    @Column(name = "address_street")
    private String addressStreet;
}

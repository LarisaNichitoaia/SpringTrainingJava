package ro.msg.learning.shop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import ro.msg.learning.shop.domain.primarykeys.EntityKey;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "customer")
public  class Customer extends EntityKey {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String username;

    private String password;

    private String email;
}

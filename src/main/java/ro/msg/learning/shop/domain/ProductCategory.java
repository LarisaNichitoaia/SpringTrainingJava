package ro.msg.learning.shop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import ro.msg.learning.shop.domain.primarykeys.EntityKey;

@EqualsAndHashCode(callSuper = true)
@Table(name = "product_category")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class ProductCategory extends EntityKey {
    @Column(name = "name", unique = true)
    private String name;

    private String description;
}

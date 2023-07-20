package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.*;
import ro.msg.learning.shop.domain.primarykeys.EntityKey;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "product")
public class Product extends EntityKey {
    private String name;

    private String description;

    private BigDecimal price;

    private Double weight;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    private String supplier;

    @Column(name = "image_url")
    private String imageUrl;
}

package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ro.msg.learning.shop.domain.primarykeys.StockKey;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock")
@IdClass(StockKey.class)
public class Stock {

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private Integer quantity;
}

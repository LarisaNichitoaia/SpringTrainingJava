package ro.msg.learning.shop.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ro.msg.learning.shop.domain.primarykeys.OrderDetailKey;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_detail")
@IdClass(OrderDetailKey.class)
public class OrderDetail {
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "shipped_from")
    private Location shippedFrom;

    private Integer quantity;
}

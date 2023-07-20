package ro.msg.learning.shop.domain.primarykeys;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;
@Data
@NoArgsConstructor
public class OrderDetailKey implements Serializable{
        private UUID orderId;
        private UUID productId;
}

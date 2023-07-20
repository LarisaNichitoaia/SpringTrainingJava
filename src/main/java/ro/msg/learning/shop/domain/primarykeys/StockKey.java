package ro.msg.learning.shop.domain.primarykeys;


import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public class StockKey implements Serializable {
    private UUID productId;
    private UUID locationId;
}

package ro.msg.learning.shop.domain.primarykeys;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockKey implements Serializable {
    private UUID product;
    private UUID location;
}

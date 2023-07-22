package ro.msg.learning.shop.dto;

import lombok.Builder;
import lombok.Data;
import org.antlr.v4.runtime.misc.Pair;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class GetOrderDto {
    private UUID customerId;
    private LocalDateTime createdAt;
    private String addressCountry;
    private String addressCity;
    private String addressCounty;
    private String addressStreet;
    private Pair<UUID, Integer> products;
}

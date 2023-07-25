package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.msg.learning.shop.domain.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query(value = "SELECT DISTINCT ON (s.product_id) s.location_id FROM stock s "
            + "WHERE s.product_id IN (:product) "
            + "AND (s.product_id, s.quantity) IN ( "
            + "    SELECT s2.product_id, MAX(s2.quantity) "
            + "    FROM stock s2 "
            + "    WHERE s2.product_id IN (:product) "
            + "    GROUP BY s2.product_id "
            + ")",
            nativeQuery = true)
    UUID findLocationsWithLargestStockForEachProduct(@Param("product") UUID productId);
}

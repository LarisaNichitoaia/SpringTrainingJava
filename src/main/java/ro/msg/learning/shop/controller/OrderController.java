package ro.msg.learning.shop.controller;
import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.ProvidedOrderDto;
import ro.msg.learning.shop.dto.ViewOrderDto;
import ro.msg.learning.shop.mapper.OrderMapper;
import ro.msg.learning.shop.service.OrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<ViewOrderDto> createOrder(@RequestBody @NonNull ProvidedOrderDto providedOrder) {
        ViewOrderDto viewOrderDto = orderMapper.toDto(orderService.createOrder(providedOrder));
        return new ResponseEntity<>(viewOrderDto, HttpStatus.CREATED);
    }
}

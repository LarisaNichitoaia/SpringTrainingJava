package ro.msg.learning.shop.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.domain.Location;
import ro.msg.learning.shop.dto.LocationDto;
import ro.msg.learning.shop.dto.StockDto;
import ro.msg.learning.shop.mapper.StockMapper;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.UUID;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;


}

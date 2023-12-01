package io.nkh.hibernate.bootstrap;

import io.nkh.hibernate.domain.OrderHeader;
import io.nkh.hibernate.repository.OrderHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BootstrapOrderService {
    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Transactional
    public void readOrderData() {
        OrderHeader orderHeader = orderHeaderRepository.findById(1L).get();

        orderHeader.getOrderLines().forEach(ol -> {
            System.out.println("Description is: " + ol.getProduct().getDescription());

            ol.getProduct().getCategories().forEach(cat -> {
                System.out.println("Category description is: " + cat.getDescription());
            });
        });
    }
}

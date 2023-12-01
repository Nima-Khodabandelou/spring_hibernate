package io.nkh.hibernate.bootstrap;

import io.nkh.hibernate.domain.OrderHeader;
import io.nkh.hibernate.repositories.OrderHeaderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Transactional
    //@Transactional
    @Override
    public void run(String... args) throws Exception {

        OrderHeader orderHeader = orderHeaderRepository.findById(1L).get();

        orderHeader.getOrderLines().forEach(ol -> {
            System.out.println("Description is: " + ol.getProduct().getDescription());

            ol.getProduct().getCategories().forEach(cat -> {
                System.out.println("Category description is: " + cat.getDescription());
            });
        });
    }
}

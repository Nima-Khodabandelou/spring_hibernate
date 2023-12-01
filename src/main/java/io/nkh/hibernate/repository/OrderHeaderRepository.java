package io.nkh.hibernate.repository;

import io.nkh.hibernate.domain.Customer;
import io.nkh.hibernate.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
    List<OrderHeader> findAllByCustomer(Customer customer);
}

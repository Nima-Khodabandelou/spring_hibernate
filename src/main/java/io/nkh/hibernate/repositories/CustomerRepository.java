package io.nkh.hibernate.repositories;

import io.nkh.hibernate.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

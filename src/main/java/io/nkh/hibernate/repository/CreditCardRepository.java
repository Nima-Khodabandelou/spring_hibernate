package io.nkh.hibernate.repository;

import io.nkh.hibernate.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}

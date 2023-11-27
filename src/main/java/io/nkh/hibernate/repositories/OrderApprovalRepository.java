package io.nkh.hibernate.repositories;

import io.nkh.hibernate.domain.OrderApproval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderApprovalRepository extends JpaRepository<OrderApproval, Long> {
}

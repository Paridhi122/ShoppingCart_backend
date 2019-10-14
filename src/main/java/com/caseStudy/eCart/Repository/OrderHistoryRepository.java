package com.caseStudy.eCart.Repository;

import com.caseStudy.eCart.model.OrderHistory;
import com.caseStudy.eCart.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory,Long> {
    List<OrderHistory> findAllByUsers(Users users);
}

package com.lms.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.lms.model.Order;
import org.springframework.stereotype.Repository;
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from Order c where c.booking_id = ?1")
    void deleteByOrderId(String orderId);
}

package com.assessment.coffeeshop.data.repository;

import com.assessment.coffeeshop.data.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Integer> {

}

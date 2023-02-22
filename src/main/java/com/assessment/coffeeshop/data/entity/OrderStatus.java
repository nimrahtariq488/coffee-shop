package com.assessment.coffeeshop.data.entity;

import com.assessment.coffeeshop.constants.DatabaseConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = DatabaseConstants.ORDER_STATUS)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {
    @Id
    @Column(name = "OrderStatusId", length = 50, nullable = false)
    private String orderStatusId;

    @Column(name = "Description", nullable = false, length = 250)
    private String description;

}
